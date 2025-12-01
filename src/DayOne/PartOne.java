package DayOne;

import util.Util;

import java.util.Vector;

public class PartOne {
    public int run(String file) {
        Vector<String> lines = Util.readFile(file);

        SimpleAccumulator accumulator = lines.stream()
                .reduce(new SimpleAccumulator(50, 0), (acc, line) -> {
                    int x = line.startsWith("L")? anticlockwise(line, acc.value()) : clockwise(line, acc.value());
                    if (x == 0) {
                        return new SimpleAccumulator(x, acc.count() + 1);
                    }
                    return new SimpleAccumulator(x, acc.count());
                }, (a,b) -> b);
        return accumulator.count();
    }

    private int anticlockwise(String line, int start) {
        int number = Integer.parseInt(line.substring(1));
        number = number % 100; // Remove whole rotations

        start -= number;
        if (start < 0) {
            start = 100 + start;
        }
        return start;
    }

    private int clockwise(String line, int start) {
        int number = Integer.parseInt(line.substring(1));
        number = number % 100; // Remove whole rotations

        start += number;
        if (start >= 100) {
            start = start % 100;
        }
        return start;
    }
}
