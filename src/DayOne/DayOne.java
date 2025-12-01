package DayOne;

public class DayOne {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        int res1 = partOne.run("./src/DayOne/input.txt");
        System.out.println("Part One result: " + res1);

        PartTwo partTwo = new PartTwo();
        int res2 = partTwo.run("./src/DayOne/input.txt");
        System.out.println("Part Two result: " + res2);
    }
}