package DayOne;

import util.Util;

import java.util.Vector;

public class PartTwo {
    public int run(String file) {
        Vector<String> lines = Util.readFile(file);
        RotationResult accumulator = lines.stream()
            .reduce(new RotationResult(50, 0), (acc, line) ->
                line.startsWith("L")? antiClockwise(line, acc) : clockwise(line, acc),
            (a, b) -> b);
        return accumulator.zeros();
    }

    private RotationResult antiClockwise(String line, RotationResult previousResult) {
        int number = Integer.parseInt(line.substring(1));
        int zeros = (number/100) + previousResult.zeros();
        number = number % 100; // Remove whole rotations

        // If we're not going to move after checking, don't move.
        if (number == 0) {
            return new RotationResult(0, zeros);
        }

        int start = previousResult.value();
        start -= number;
        if (start <= 0) {
            // Getting to zero has already been counted when we initially reached 0.
            if (previousResult.value() != 0) {
                zeros += 1;
            }
            if (start < 0) {
                start += 100;
            }
        }
        return new RotationResult(start, zeros);
    }

    private RotationResult clockwise(String line, RotationResult previousResult) {
        int number = Integer.parseInt(line.substring(1));
        int zeros = (number/100) + previousResult.zeros();

        number = number % 100; // Remove whole rotations

        if (number == 0) {
            return new RotationResult(0, zeros);
        }

        int start = previousResult.value();
        start += number;
        if (start >= 100) {
            zeros += 1;
            return new RotationResult(start % 100, zeros);
        }
        return new RotationResult(start, zeros);
    }
}
