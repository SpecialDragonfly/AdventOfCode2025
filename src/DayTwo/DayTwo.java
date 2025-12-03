package DayTwo;

import java.math.BigInteger;

public class DayTwo {

    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        BigInteger sum = partOne.run("./src/DayTwo/input.txt");
        System.out.println("Sum of numbers was " + sum);

        PartTwo partTwo = new PartTwo();
        BigInteger sumTwo = partTwo.run("./src/DayTwo/input.txt");
        System.out.println("Sum of numbers was " + sumTwo);
    }
}
