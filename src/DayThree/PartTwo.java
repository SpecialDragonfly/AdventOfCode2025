package DayThree;

import util.Util;

import java.math.BigInteger;
import java.util.Vector;

public class PartTwo {

    public BigInteger run(String file) {
        Vector<String> lines = Util.readFile(file);
        return lines.stream().reduce(new BigInteger("0"), (acc, line) -> {
            Vector<String> result = new Vector<>();
            int index = -1;
            for (int limit = 11; limit >= 0; limit--) {
                index = this.findMaxNumberIndex(line, index + 1, limit);
                result.add(line.substring(index, index + 1));
            }
            return acc.add(new BigInteger(this.concatVector(result)));
        }, BigInteger::add);
    }

    public String concatVector(Vector<String> vector) {
        StringBuilder sb = new StringBuilder();
        vector.forEach(sb::append);

        return sb.toString();
    }

    private int findMaxNumberIndex(String number, int start, int limit) {
        int max = Integer.parseInt(number.substring(start, start+1));
        int index = start;
        for (int i = start; i < number.length() - limit; i++) {
            String value = number.substring(i, i+1);
            if (max < Integer.parseInt(value)) {
                max = Integer.parseInt(value);
                index = i;
            }
        }

        return index;
    }
}
