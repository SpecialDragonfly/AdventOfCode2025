package DayFive;

import java.math.BigInteger;

public class DayFive {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        int sum = partOne.run("./src/DayFive/input.txt");
        System.out.println("Part 1: " + sum);

        PartTwo partTwo = new PartTwo();
        BigInteger sumTwo = partTwo.run("./src/DayFive/input.txt");
        System.out.println("Part 2:" + sumTwo);
    }
}
