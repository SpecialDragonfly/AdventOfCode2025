package DaySix;

import util.Util;

import java.math.BigInteger;
import java.util.Vector;

public class PartTwo {

    public BigInteger run(String file) {
        Vector<String> lines = Util.readFile(file);

        Vector<Problem> problems = getProblems(lines);

        return problems.stream().map(Problem::calculate).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private Vector<Problem> getProblems(Vector<String> lines) {
        int max = lines.stream().mapToInt(String::length).max().orElse(0);
        Vector<Problem> problems = new Vector<>();
        Problem problem = new Problem();
        for (int col = 0; col < max; col++) {
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                // Some lines are shorter than our max line length, so we can skip those ones.
                if (line.length() < col + 1) {
                    continue;
                }
                String val = line.substring(col, col + 1);
                if (val.equals("*") || val.equals("+")) {
                    problem.setOperand(val);
                } else {
                    sb.append(val);
                }
            }
            if (sb.toString().trim().isEmpty()) {
                // new problem started
                problems.add(problem);
                problem = new Problem();
            } else {
                problem.addColumn(new Column(sb.toString()));
            }
        }
        problems.add(problem);

        return problems;
    }
}
