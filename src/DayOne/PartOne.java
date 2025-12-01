package DayOne;

import util.Util;

import java.util.Vector;

public class PartOne {
    public int run(String file) {
        Vector<String> lines = Util.readFile(file);

        RotationResult accumulator = lines.stream()
            .reduce(new RotationResult(50, 0), (acc, line) -> {
                int x = line.startsWith("L")? anticlockwise(line, acc.value()) : clockwise(line, acc.value());
                if (x == 0) {
                    return new RotationResult(x, acc.zeros() + 1);
                }
                return new RotationResult(x, acc.zeros());
            }, (a,b) -> b);
        return accumulator.zeros();
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
