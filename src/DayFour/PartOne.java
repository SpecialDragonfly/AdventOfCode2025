package DayFour;

import util.Util;

import java.util.Vector;

public class PartOne {
    public int run(String file) {
        Vector<Vector<String>> array = Util.readFileAsArray(file);
        int moveable = 0;
        for (int row = 0; row < array.size(); row++) {
            for (int col = 0; col < array.get(row).size(); col++) {
                if (array.get(row).get(col).equals("@")) {
                    // Check around it.
                    if (canMove(array, row, col)) {
                        System.out.println("Box can move at row " + row + ", col " + col);
                        moveable++;
                    }
                }
            }
        }
        return moveable;
    }

    private boolean canMove(Vector<Vector<String>> array, int row, int col) {
        int count = 0;
        int previousRow = row - 1;
        if (previousRow < 0) {
            previousRow = 0;
        }
        int nextRow = row + 1;
        if (nextRow == array.size()) {
            nextRow = array.size() - 1;
        }
        int previousCol = col - 1;
        if (previousCol < 0) {
            previousCol = 0;
        }
        int nextCol = col + 1;
        if (nextCol == array.get(row).size()) {
            nextCol = array.get(row).size() - 1;
        }
        for (int subRow = previousRow; subRow <= nextRow; subRow++) {
            for (int subCol = previousCol; subCol <= nextCol; subCol++) {
                if (subRow == row && subCol == col) {
                    // Don't count the center cell.
                    continue;
                }
                if (array.get(subRow).get(subCol).equals("@")) {
                    count++;
                }
            }
        }

        return count < 4;
    }
}
