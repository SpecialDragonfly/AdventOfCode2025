package DayFive;

import util.Util;

import java.math.BigInteger;
import java.util.Vector;

public class PartOne {
    public int run(String inputFile) {
        Vector<String> input = Util.readFile(inputFile);
        Vector<Range> ranges = new Vector<>();
        Vector<BigInteger> ingredients = new Vector<>();
        boolean insertRange = true;
        for (String line : input) {
            if (line.trim().isEmpty()) {
                insertRange = false;
                continue;
            }
            if (insertRange) {
                ranges.add(
                    new Range(
                        new BigInteger(line.split("-")[0]),
                        new BigInteger(line.split("-")[1])
                    )
                );
            } else {
                ingredients.add(new BigInteger(line));
            }
        }
        return ingredients.stream().reduce(0, (acc, ingredient) -> {
            if (ranges.stream().anyMatch(range -> range.isInRange(ingredient))) {
                acc += 1;
            }
            return acc;
        }, (a, b) -> b);
    }
}
