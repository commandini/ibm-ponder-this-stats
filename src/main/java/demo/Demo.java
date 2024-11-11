package demo;

import core.compiler.CompilerResult;
import core.model.Challenge;
import core.model.CorrectSubmission;
import core.model.Ponderer;
import core.query.OfflineQueryEngine;
import core.query.OnlineQueryEngine;
import core.util.AsciiTableRenderer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static core.data.Constants.CURRENT_YEAR;
import static core.data.Constants.IBM_PONDER_THIS_START_YEAR;

public class Demo {
    public static void main(String[] args) throws IOException {
        OnlineQueryEngine onlineQueryEngine = OnlineQueryEngine.getInstance();
        List<CompilerResult> compilerResults = onlineQueryEngine.compilerResults();
        Collections.sort(compilerResults, Comparator.comparing(compilerResult -> compilerResult.challenge().toString()));
        System.out.println("Compiler results count: " + compilerResults.size());
        OptionalDouble averageRuntime = compilerResults.stream()
                .mapToLong(CompilerResult::runtimeInMilliseconds)
                .average();
        if (averageRuntime.isPresent()) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            System.out.println("Average runtime of the compilers is " + decimalFormat.format(averageRuntime.getAsDouble()) + " milliseconds.");
        }

        reportChallengesWithDuplicateSubmissions(compilerResults);
        reportPonderers(onlineQueryEngine.allPonderers(), 10);
        reportCorrectSubmissionsCountPerYear(onlineQueryEngine.allChallenges());

        // This is a must for the correct functioning of the offline query engine.
        onlineQueryEngine.flushToFileSystem();

        OfflineQueryEngine offlineQueryEngine = OfflineQueryEngine.getInstance();
        List<Challenge> challengesOfTheCurrentYear = offlineQueryEngine.allChallenges().stream()
                .filter(challenge -> challenge.getYear() >= CURRENT_YEAR)
                .toList();
        List<Ponderer> ponderersOfTheCurrentYear = offlineQueryEngine.ponderersOf(challengesOfTheCurrentYear);
        reportPonderers(ponderersOfTheCurrentYear, 10);
    }

    public static void reportChallengesWithDuplicateSubmissions(List<CompilerResult> compilerResults) {
        List<Object> rows = new ArrayList<>();
        compilerResults.stream()
                .filter(compilerResult -> !compilerResult.duplicateSubmissions().isEmpty())
                .forEach(compilerResult -> {
                    rows.add(compilerResult.challenge());
                    rows.add(compilerResult.duplicateSubmissions().stream()
                            .map(CorrectSubmission::toString)
                            .collect(Collectors.joining(", ")));
                });
        AsciiTableRenderer.render(List.of("CHALLENGE", "DUPLICATES"), rows);
    }

    public static void reportPonderers(List<Ponderer> ponderers, int size) {
        Collections.sort(ponderers, Comparator.comparing(Ponderer::getRankingFactor).thenComparing(Ponderer::getIdentifier));
        List<Object> rows = new ArrayList<>();
        IntStream.range(0, Math.min(size, ponderers.size())).forEach(index -> {
            Ponderer ponderer = ponderers.get(index);
            rows.add(index + 1);
            rows.add(ponderer);
            rows.add(ponderer.getAchievements().size());
        });
        AsciiTableRenderer.render(List.of("RANK", "PONDERER", "CHALLENGES SOLVED"), rows);
    }

    public static void reportCorrectSubmissionsCountPerYear(List<Challenge> challenges) {
        List<Object> yearData = new ArrayList<>();
        IntStream.range(IBM_PONDER_THIS_START_YEAR, CURRENT_YEAR + 1).forEach(year -> {
            yearData.add(year);
            yearData.add(challenges.stream()
                    .filter(challenge -> challenge.getYear() == year)
                    .flatMap(challenge -> challenge.getCorrectSubmissions().stream())
                    .count());
        });
        AsciiTableRenderer.render(List.of("YEAR", "CORRECT SUBMISSIONS COUNT"), yearData);
    }
}
