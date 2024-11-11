package core.model;

import com.google.common.base.Objects;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static core.data.Constants.CHALLENGES_ROOT_URL;
import static core.data.Constants.HTML_EXTENSION;

public class Challenge implements Comparable<Challenge> {
    private final Month month;
    private final int year;
    private final String url;
    private final Set<CorrectSubmission> correctSubmissions;

    public Challenge(Month month, int year) {
        this.month = month;
        this.year = year;
        this.url = buildUrl(month, year);
        this.correctSubmissions = new LinkedHashSet<>();
    }

    private String buildUrl(Month month, int year) {
        String monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return CHALLENGES_ROOT_URL + monthName + year + HTML_EXTENSION;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getUrl() {
        return url;
    }

    public List<CorrectSubmission> getCorrectSubmissions() {
        return new ArrayList<>(correctSubmissions);
    }

    public void addSolvers(List<CorrectSubmission> correctSubmissions) {
        this.correctSubmissions.addAll(correctSubmissions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return year == challenge.year && month == challenge.month;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(month, year);
    }

    @Override
    public String toString() {
        String monthStr = month.getValue() < 10 ? "0" + month.getValue() : "" + month.getValue();
        return year + "_" + monthStr;
    }

    @Override
    public int compareTo(Challenge other) {
        return toString().compareTo(other.toString());
    }
}
