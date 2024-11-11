package core.validator;

import core.model.Challenge;

import static core.data.Constants.IBM_PONDER_THIS_START_YEAR;
import static core.data.Constants.NEW_LINE;

public class PostScrapingValidator {
    static final int[][] COUNTS_OF_PUBLISHED_CORRECT_SUBMISSIONS = {
            /**
             * Each of the following rows corresponds to a year, starting from {@link core.data.Constants#IBM_PONDER_THIS_START_YEAR}
            */
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 21, 103, 21, 16, 21},
            {14, 1, 100, 12, 24, 38, 19, 1, 64, 55, 88, 0},
            {40, 64, 45, 79, 38, 41, 72, 20, 2, 32, 14, 35},
            {32, 22, 12, 8, 47, 37, 138, 0, 4, 38, 132, 194},
            {40, 8, 24, 6, 41, 31, 0, 83, 24, 58, 13, 12},
            {82, 87, 26, 82, 27, 0, 38, 73, 13, 18, 150, 55},
            {101, 99, 10, 0, 100, 63, 0, 0, 64, 40, 24, 20},
            {29, 38, 63, 19, 82, 93, 18, 41, 50, 22, 163, 52},
            {55, 68, 44, 40, 143, 54, 91, 72, 52, 65, 37, 79},
            {78, 26, 26, 74, 85, 65, 87, 27, 81, 73, 90, 132},
            {52, 8, 76, 11, 120, 48, 64, 40, 61, 182, 94, 31},
            {223, 31, 25, 47, 57, 38, 32, 33, 31, 31, 7, 132},
            {55, 128, 67, 118, 76, 132, 26, 89, 55, 58, 53, 67},
            {114, 90, 56, 51, 128, 157, 57, 108, 53, 86, 118, 247},
            {32, 149, 24, 295, 68, 60, 123, 57, 37, 122, 225, 120},
            {40, 28, 56, 111, 37, 185, 60, 127, 141, 179, 74, 107},
            {69, 38, 27, 54, 140, 54, 79, 30, 100, 55, 108, 21},
            {146, 102, 116, 31, 57, 14, 80, 88, 107, 153, 37, 26},
            {67, 78, 54, 75, 161, 53, 63, 61, 32, 22, 23, 88},
            {29, 87, 85, 163, 64, 98, 191, 116, 64, 27, 33, 51},
            {89, 88, 46, 187, 36, 65, 95, 39, 110, 89, 32, 80},
            {80, 72, 69, 82, 41, 36, 74, 57, 77, 27, 93, 101},
            {119, 78, 98, 91, 45, 103, 70, 48, 35, 73, 49, 61},
            {118, 42, 76, 53, 176, 43, 73, 77, 31, 54, 65, 61},
            {60, 60, 82, 79, 51, 56, 94, 161, 133, 93, 111, 97},
            {150, 101, 186, 126, 53, 123, 62, 86, 18, 0, 0, 0},
    };

    public void validate(Challenge challenge) {
        int row = challenge.getYear() - IBM_PONDER_THIS_START_YEAR;
        int column = challenge.getMonth().ordinal();
        if (challenge.getCorrectSubmissions().size() != COUNTS_OF_PUBLISHED_CORRECT_SUBMISSIONS[row][column]) {
            throw new IllegalStateException(
                    "The detected count of the correct submissions does not match the published count of the correct submissions for the challenge. "
                            + NEW_LINE
                            + "[Challenge] -> " + challenge + NEW_LINE
                            + "[Actual] -> " + challenge.getCorrectSubmissions().size() + " vs [Expected] -> "
                            + COUNTS_OF_PUBLISHED_CORRECT_SUBMISSIONS[row][column] + NEW_LINE);
        }
    }
}
