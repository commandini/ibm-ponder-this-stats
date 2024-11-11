package core.query;

import com.google.common.base.Suppliers;
import core.compiler.CompilerResult;
import core.compiler.SuperCompiler;
import core.model.Challenge;
import core.serde.ChallengeSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static core.data.Constants.CHALLENGES_ROOT_DIRECTORY;
import static core.data.Constants.CURRENT_YEAR;
import static core.data.Constants.IBM_PONDER_THIS_START_YEAR;

public class OnlineQueryEngine implements QueryEngine {
    private static final SuperCompiler SUPER_COMPILER = new SuperCompiler(IBM_PONDER_THIS_START_YEAR, CURRENT_YEAR);
    private static final Supplier<List<CompilerResult>> COMPILER_RESULTS = Suppliers.memoize(SUPER_COMPILER::compile);
    private static final ChallengeSerializer CHALLENGE_SERIALIZER = new ChallengeSerializer();

    private OnlineQueryEngine() {
    }

    private static class SingletonHelper {
        private static final OnlineQueryEngine INSTANCE = new OnlineQueryEngine();
    }

    public static OnlineQueryEngine getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public List<CompilerResult> compilerResults() {
        return new ArrayList<>(COMPILER_RESULTS.get());
    }

    @Override
    public List<Challenge> allChallenges() {
        return compilerResults().stream().map(CompilerResult::challenge).toList();
    }

    public void flushToFileSystem() throws IOException {
        CHALLENGE_SERIALIZER.serialize(allChallenges(), CHALLENGES_ROOT_DIRECTORY);
    }
}
