package DayTwo;

import util.Util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public void run(String file) {
        List<String> lines = Util.readSingleLineFileAsCsv(file);
        BigInteger sum = new BigInteger("0");
        for (String line : lines) {
            // Split on a hyphen to get both parts.
            String[] parts = line.split("-");
            BigInteger min = new BigInteger(parts[0]);
            BigInteger max = new BigInteger(parts[1]);

            try {
                Pattern p = Pattern.compile("^(\\d+?)\\1+$");
                while (min.compareTo(max) <= 0) {

                    Matcher m = p.matcher(min.toString());
                    if (m.matches()) {
                        sum = sum.add(min);
                    }
                    min = min.add(BigInteger.ONE);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Sum of numbers was " + sum);
    }
}
