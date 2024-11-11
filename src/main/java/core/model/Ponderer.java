package core.model;

import java.util.Set;
import java.util.TreeSet;

public class Ponderer {
    private final String identifier;
    private final Set<Achievement> achievements;

    public Ponderer(String identifier) {
        this.identifier = identifier;
        this.achievements = new TreeSet<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public int getRankingFactor() {
        return -1 * achievements.size();
    }

    public int getTotalStarCount() {
        return achievements.stream().mapToInt(Achievement::starCount).sum();
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    @Override
    public String toString() {
        return identifier;
    }
}
