package core.query;

import com.google.common.base.Suppliers;
import core.model.Challenge;
import core.serde.ChallengeDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static core.data.Constants.CHALLENGES_ROOT_DIRECTORY;

public class OfflineQueryEngine implements QueryEngine {
    private static final ChallengeDeserializer CHALLENGE_DESERIALIZER = new ChallengeDeserializer();
    private static final Supplier<List<Challenge>> CHALLENGES =
            Suppliers.memoize(() -> CHALLENGE_DESERIALIZER.deserialize(CHALLENGES_ROOT_DIRECTORY));

    private OfflineQueryEngine() {
    }

    private static class SingletonHelper {
        private static final OfflineQueryEngine INSTANCE = new OfflineQueryEngine();
    }

    public static OfflineQueryEngine getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public List<Challenge> allChallenges() {
        return new ArrayList<>(CHALLENGES.get());
    }
}
