package core.util;

import com.google.common.collect.Lists;
import de.vandermeer.asciitable.AsciiTable;

import java.util.List;

public class AsciiTableRenderer {
    public static void render(List<String> columnNames, List<Object> rows) {
        if (columnNames == null || columnNames.isEmpty()) {
            return;
        }
        if (rows == null || rows.isEmpty()) {
            return;
        }
        if (rows.size() % columnNames.size() != 0) {
            throw new IllegalArgumentException("The rows don't match the columns!");
        }
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow(columnNames);
        asciiTable.addRule();
        List<List<Object>> partitions = Lists.partition(rows, columnNames.size());
        partitions.forEach(partition -> {
            asciiTable.addRow(partition);
            asciiTable.addRule();
        });
        System.out.println(asciiTable.render());
    }
}
