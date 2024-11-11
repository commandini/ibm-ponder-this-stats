package core.model;

import com.google.common.base.Objects;

public record CorrectSubmission(String identifier, int starCount) implements Comparable<CorrectSubmission> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorrectSubmission correctSubmission = (CorrectSubmission) o;
        return Objects.equal(identifier, correctSubmission.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public int compareTo(CorrectSubmission other) {
        return identifier.compareTo(other.identifier);
    }
}
