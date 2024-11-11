package core.compiler;

import core.model.Challenge;
import core.model.CorrectSubmission;
import core.util.DuplicateDetector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static core.data.Constants.DOUBLE_STAR;
import static core.data.Constants.NEW_LINE;
import static core.data.Constants.STAR;
import static core.data.Constants.XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS;

public class SingleChallengeCompiler implements Callable<CompilerResult> {
    private static final Logger LOGGER = Logger.getLogger(SingleChallengeCompiler.class.getName());
    private final Challenge challenge;
    private final Map<String, String> htmlPatch;

    public SingleChallengeCompiler(Challenge challenge, Map<String, String> htmlPatch) {
        this.challenge = challenge;
        this.htmlPatch = htmlPatch;
    }

    public CompilerResult call() {
        long startTime = System.currentTimeMillis();
        Set<CorrectSubmission> duplicateSubmissions = new HashSet<>();
        try {
            Document document = fetchDocument();
            List<String> rawSolvers = extractRawSolvers(document);
            List<CorrectSubmission> correctSubmissions = extractCorrectSubmissions(rawSolvers);
            if (!correctSubmissions.isEmpty()) {
                challenge.addSolvers(correctSubmissions);
                duplicateSubmissions = DuplicateDetector.findDuplicates(correctSubmissions);
            } else {
                LOGGER.warning("No solvers found for the challenge: " + challenge);
            }
        } catch (IOException e) {
            LOGGER.warning("Failed to fetch the solvers of the challenge: " + challenge + NEW_LINE + e.getMessage());
        }
        long runtime = System.currentTimeMillis() - startTime;
        return new CompilerResult(challenge, duplicateSubmissions, runtime);
    }

    private Document fetchDocument() throws IOException {
        Document document = Jsoup.connect(challenge.getUrl()).execute().bufferUp().parse();
        String html = document.html();
        for (Map.Entry<String, String> entry : htmlPatch.entrySet()) {
            html = html.replace(entry.getKey(), entry.getValue());
        }
        return Jsoup.parse(html);
    }

    private List<String> extractRawSolvers(Document document) {
        List<String> result = new ArrayList<>();
        for (String xpathOption : XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS) {
            result.addAll(document.selectXpath(xpathOption).stream()
                    .map(Element::text)
                    .toList());
        }
        return result;
    }

    private List<CorrectSubmission> extractCorrectSubmissions(List<String> rawSolvers) {
        return rawSolvers.stream()
                .filter(rawSolver -> rawSolver != null
                        && !rawSolver.isBlank()
                        && !STAR.equals(rawSolver)
                        && !DOUBLE_STAR.equals(rawSolver))
                .map(this::extractCorrectSubmission)
                .toList();
    }

    private CorrectSubmission extractCorrectSubmission(String rawSolver) {
        String trimmedRawSolver = rawSolver.trim();
        int lastIndexOfStar = trimmedRawSolver.lastIndexOf(STAR);
        String identifier = trimmedRawSolver.substring(lastIndexOfStar + 1);
        int starCount = trimmedRawSolver.substring(0, lastIndexOfStar + 1).length();
        return new CorrectSubmission(identifier, starCount);
    }
}
