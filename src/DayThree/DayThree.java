package DayThree;

import java.math.BigInteger;

public class DayThree {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        int sum = partOne.run("./src/DayThree/input.txt");
        System.out.println("Part 1: " + sum);

        PartTwo partTwo = new PartTwo();
        BigInteger sumToo = partTwo.run("./src/DayThree/input.txt");
        System.out.println("Part 2: " + sumToo);
    }
}
