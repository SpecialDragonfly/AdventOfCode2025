package DayFour;

import util.Util;

import java.util.Vector;

public class DayFour {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        int moveable = partOne.run("./src/DayFour/input.txt");
        System.out.println("Number of boxes that can move: " + moveable);

        PartTwo partTwo = new PartTwo();
        int count = partTwo.run("./src/DayFour/input.txt");
        System.out.println("Number of boxes that can move: " + count);
    }
}
