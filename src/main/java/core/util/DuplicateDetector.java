package core.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateDetector {
    public static <T> Set<T> findDuplicates(List<T> list) {
        Set<T> seen = new HashSet<>();
        Set<T> duplicates = new HashSet<>();
        for (T element : list) {
            if (!seen.add(element)) {
                duplicates.add(element);
            }
        }
        return duplicates;
    }
}
