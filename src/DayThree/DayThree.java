package DayThree;

import java.math.BigInteger;

public class DayThree {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        partOne.run("./src/DayThree/input.txt");

        PartTwo partTwo = new PartTwo();
        BigInteger sum = partTwo.run("./src/DayThree/input.txt");
        System.out.println("Part 2: " + sum);
    }
}
