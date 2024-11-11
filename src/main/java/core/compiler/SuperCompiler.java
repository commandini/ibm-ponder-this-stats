package core.compiler;

import core.data.HtmlPatchProvider;
import core.model.Challenge;
import core.validator.PostScrapingValidator;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static core.data.Constants.COMPILATION_THREAD_POOL_SIZE;
import static core.data.Constants.COMPILATION_TIMEOUT_IN_SECONDS;

public class SuperCompiler {
    private static final Logger LOGGER = Logger.getLogger(SuperCompiler.class.getName());

    private final int startYear;
    private final int endYear;
    private final HtmlPatchProvider htmlPatchProvider;
    private final PostScrapingValidator postScrapingValidator;
    private final ExecutorService executorService;

    public SuperCompiler(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.htmlPatchProvider = new HtmlPatchProvider();
        this.postScrapingValidator = new PostScrapingValidator();
        this.executorService = Executors.newFixedThreadPool(COMPILATION_THREAD_POOL_SIZE);
    }

    public List<CompilerResult> compile() {
        List<CompilerResult> compilerResults = new ArrayList<>();
        List<SingleChallengeCompiler> challengeCompilers = constructChallengeCompilers();
        try {
            for (Future<CompilerResult> future : executorService.invokeAll(challengeCompilers)) {
                compilerResults.add(future.get());
            }
            shutDownExecutor();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.severe("Failed to execute the challenge compilers.");
            Thread.currentThread().interrupt();
        }
        compilerResults.forEach(compilerResult -> postScrapingValidator.validate(compilerResult.challenge()));
        return compilerResults;
    }

    private List<SingleChallengeCompiler> constructChallengeCompilers() {
        List<Challenge> challenges = constructChallenges();
        List<SingleChallengeCompiler> challengeCompilers = new ArrayList<>();
        challenges.forEach(challenge -> challengeCompilers.add(
                new SingleChallengeCompiler(challenge, htmlPatchProvider.retrievePatchFor(challenge))));
        return challengeCompilers;
    }

    private List<Challenge> constructChallenges() {
        List<Challenge> challenges = new ArrayList<>();
        for (Month month : Month.values()) {
            for (int year = startYear; year <= endYear; year++) {
                challenges.add(new Challenge(month, year));
            }
        }
        return challenges;
    }

    private void shutDownExecutor() throws InterruptedException {
        executorService.shutdown();
        if (!executorService.awaitTermination(COMPILATION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }
}
