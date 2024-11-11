package core.query;

import core.model.Achievement;
import core.model.Challenge;
import core.model.CorrectSubmission;
import core.model.Ponderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QueryEngine {

    List<Challenge> allChallenges();

    default List<Ponderer> allPonderers() {
        return ponderersOf(allChallenges());
    }

    default List<Ponderer> ponderersOf(Collection<Challenge> challenges) {
        Map<String, Ponderer> identifiersToPonderers = new HashMap<>();
        for (Challenge challenge : challenges) {
            for (CorrectSubmission correctSubmission : challenge.getCorrectSubmissions()) {
                String identifier = correctSubmission.identifier();
                identifiersToPonderers.computeIfAbsent(identifier, Ponderer::new);
                identifiersToPonderers.get(identifier).addAchievement(new Achievement(challenge, correctSubmission.starCount()));
            }
        }
        return new ArrayList<>(identifiersToPonderers.values());
    }
}
