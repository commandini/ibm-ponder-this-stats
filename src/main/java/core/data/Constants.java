package core.data;

import java.time.LocalDateTime;

public class Constants {
    public static final int IBM_PONDER_THIS_START_YEAR = 1998;
    public static final int CURRENT_YEAR = LocalDateTime.now().getYear();
    public static final int COMPILATION_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    public static final long COMPILATION_TIMEOUT_IN_SECONDS = 60;
    public static final String STAR = "*";
    public static final String DOUBLE_STAR = STAR + STAR;
    public static final String NEW_LINE = System.lineSeparator();
    public static final String HTML_EXTENSION = ".html";
    public static final String JSON_EXTENSION = ".json";
    public static final String CHALLENGES_ROOT_URL = "https://research.ibm.com/haifa/ponderthis/challenges/";
    public static final String CHALLENGES_ROOT_DIRECTORY = "ibm_ponder_this_challenges";
    public static final String[] XPATH_OPTIONS_OF_CORRECT_SUBMISSIONS = {
            "//*[@id=\"ibm-content-main\"]/div/b/div/p[3]/font/b",
            "//*[@id=\"ibm-content-main\"]/div/b/div/p[4]/font/b",
            "//*[@id=\"ibm-content-main\"]/div/div[2]/p[2]/font/font/b",
            "//*[@id=\"ibm-content-main\"]/div/div[2]/p/font/b",
            "//*[@id=\"ibm-content-main\"]/div/div[2]/p/b/font",
            "//*[@id=\"ibm-content-main\"]/div/div[2]/p/b/font/b/font",
            "//*[@id=\"ibm-research-israel-introduction-container\"]/div/div/p/b/font",
    };
}
