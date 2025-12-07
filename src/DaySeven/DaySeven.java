package DaySeven;

public class DaySeven {

    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        long answer = partOne.run("./src/DaySeven/input.txt");
        System.out.println("Part 1: " + answer);

        PartTwo partTwo = new PartTwo();
        long p2 = partTwo.run("./src/DaySeven/input.txt");
        System.out.println("Part 2: " + p2);
    }
}
