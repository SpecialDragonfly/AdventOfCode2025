package DaySix;

import java.math.BigInteger;

public class DaySix {
    // Too low: 206225369
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        BigInteger answer = partOne.run("./src/DaySix/input.txt");
        System.out.println("Answer: " + answer);

        PartTwo partTwo = new PartTwo();
        BigInteger p2 = partTwo.run("./src/DaySix/input.txt");
        System.out.println("Answer: " + p2);
    }
}