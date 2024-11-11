package core.model;

public record Achievement(Challenge challenge, int starCount) implements Comparable<Achievement> {
    @Override
    public int compareTo(Achievement other) {
        return challenge.toString().compareTo(other.challenge.toString());
    }
}
