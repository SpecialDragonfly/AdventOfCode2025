package DaySix;

import util.Util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class PartOne {
    public BigInteger run(String file) {
        Vector<String> lines = Util.readFile(file);

        Vector<Vector<Integer>> problems = new Vector<>();
        Vector<String> operations = new Vector<>(Arrays.stream(lines.getLast().split("\\s+")).toList());
        for (int i = 0; i < lines.size() - 1; i++) {
            problems.add(
                Arrays.stream(lines.get(i).trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(Vector::new)));
        }

        BigInteger answer = BigInteger.ZERO;
        for (int column = 0; column < operations.size(); column++) {
            if (operations.get(column).equals("*")) {
                answer = answer.add(multiply(getColumn(problems, column)));
            } else {
                answer = answer.add(add(getColumn(problems, column)));
            }
        }

        return answer;
    }

    private BigInteger multiply(Vector<Integer> values) {
        return values.stream().map(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private BigInteger add(Vector<Integer> values) {
        return values.stream().map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private Vector<Integer> getColumn(Vector<Vector<Integer>> problems, int column) {
        return problems.stream()
            .map(p -> p.get(column))
            .collect(Collectors.toCollection(Vector::new));
    }
}
