package DaySeven;

import util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PartTwo {
    private Vector<String> lines;
    private Map<Splitter, Long> cache;

    public long run(String file) {
        lines = Util.readFile(file);
        cache = new HashMap<>();
        return split(1, lines.getFirst().indexOf('S'));
    }

    private long split(int row, int col) {
        if (row == lines.size()) {
            return 1;
        }
        if (lines.get(row).charAt(col) == '^') {
            Splitter split = new Splitter(row, col);
            Long cached = cache.get(split);
            if (cached == null) {
                cached = split(row + 1, col - 1) + split(row + 1, col + 1);
                cache.put(split, cached);
            }
            return cached;
        } else {
            return split(row + 1, col);
        }
    }

}
