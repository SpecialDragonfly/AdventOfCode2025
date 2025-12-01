package DayOne;

import util.Util;

import java.util.Vector;

public class PartTwo {
    public int run(String file) {
        Vector<String> lines = Util.readFile(file);
        SimpleAccumulator accumulator = lines.stream()
                .reduce(new SimpleAccumulator(50, 0), (acc, line) -> {
                    RotationResult x = line.startsWith("L")? antiClockwise(line, acc) : clockwise(line, acc);
                    return new SimpleAccumulator(x.result(), x.zeros());
                }, (a,b) -> b);
        return accumulator.count();
    }

    private RotationResult antiClockwise(String line, SimpleAccumulator previousResult) {
        int number = Integer.parseInt(line.substring(1));
        int zeros = (number/100) + previousResult.count();
        number = number % 100; // Remove whole rotations

        // If we're not going to move after checking, don't move.
        if (number == 0) {
            return new RotationResult(zeros, 0);
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

            return new RotationResult(zeros, start);
        }
        return new RotationResult(zeros, start);
    }

    private RotationResult clockwise(String line, SimpleAccumulator previousResult) {
        int number = Integer.parseInt(line.substring(1));
        int zeros = (number/100) + previousResult.count();

        number = number % 100; // Remove whole rotations

        if (number == 0) {
            return new RotationResult(zeros, 0);
        }

        int start = previousResult.value();
        start += number;
        if (start >= 100) {
            zeros += 1;
            return new RotationResult(zeros, start % 100);
        }
        return new RotationResult(zeros, start);
    }
}
