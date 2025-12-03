package DayThree;

import util.Util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class PartTwo {
    public BigInteger run(String file) {
        Vector<String> lines = Util.readFile(file);
        BigInteger sum = new BigInteger("0");
        for (String line : lines) {
            Vector<String> result = new Vector<>();
            int index = -1;
            for (int limit = 11; limit >= 0; limit--) {
                index = this.findMaxNumberIndex(line, index + 1, limit);
                result.add(line.substring(index, index + 1));
            }
            sum = sum.add(new BigInteger(this.concatVector(result)));
        }
        return sum;
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
