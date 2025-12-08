package DayEight;

import java.util.*;

public class DayEight {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        System.out.println("Answer: " + partOne.run("./src/DayEight/input.txt", 1000));

        PartTwo partTwo = new PartTwo();
        System.out.println("Answer: " + partTwo.run("./src/DayEight/input.txt"));
    }
}
