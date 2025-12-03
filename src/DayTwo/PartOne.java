package DayTwo;

import util.Util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public void run(String file) {
        List<String> lines = Util.readSingleLineFileAsCsv(file);
        BigInteger sum = new BigInteger("0");
        for (String line : lines) {
            System.out.println("Line: " + line);
            // Split on a hyphen to get both parts.
            String[] parts = line.split("-");
            BigInteger min = new BigInteger(parts[0]);
            BigInteger max = new BigInteger(parts[1]);

            try {
                Pattern p = Pattern.compile("^(\\d+?)\\1$");
                while (min.compareTo(max) <= 0) {
                    if (min.toString().length() % 2 != 0) {
                        min = getNextInt(min);
                        if (min.compareTo(max) > 0) {
                            // Min was over max, so we can stop here.
                            continue;
                        }
                    }

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

    private static BigInteger getNextInt(BigInteger min) throws Exception {
        // We know min has an odd number of digits. We need an even number of digits.
        // 1 -> 11, 111 -> 1010, 11111 -> 101101
        switch (min.toString().length()) {
            case 1:
                return new BigInteger("11");
            case 3:
                return new BigInteger("1010");
            case 5:
                return new BigInteger("101101");
            case 7:
                return new BigInteger("10011001");
            case 9:
                return new BigInteger("1000110001");
            default:
                throw new Exception("Unexpected length: " + min.toString().length());
        }
    }
}
