package DayThree;

import util.Util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class PartOne {
    public void run(String file) {
        Vector<String> lines = Util.readFile(file);
        int sum = 0;
        for (String line : lines) {
            List<Integer> parts = Arrays.stream(line.split("")).map(Integer::parseInt).toList();
            Iterator<Integer> iter = parts.listIterator();
            int max = iter.next();
            int secondMax = -1;
            while (iter.hasNext()) {
                int next = iter.next();
                // Is the current number greater than max. If so, it's the new max, reset 2nd max.
                // If this is the last number, it can't be the new max.
                if (max < next && iter.hasNext()) {
                    max = next;
                    secondMax = -1;
                    continue;
                }
                // If the current number is greater than secondMax, it's the new second max.
                if (secondMax < next) {
                    secondMax = next;
                }
            }
            sum += ((max*10) + secondMax);
        }
        System.out.println("Sum: " + sum);
    }
}
