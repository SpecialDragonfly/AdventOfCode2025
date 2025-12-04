package DayFour;

import util.Util;

import java.util.Vector;

public class PartTwo {
    public int run(String file) {
        Vector<Vector<String>> array = Util.readFileAsArray(file);
        Vector<Coord> moveable = new Vector<>();
        int count = 0;
        do {
            moveable.clear();
            for (int row = 0; row < array.size(); row++) {
                for (int col = 0; col < array.get(row).size(); col++) {
                    if (array.get(row).get(col).equals("@")) {
                        // Check around it.
                        if (canMove(array, row, col)) {
                            moveable.add(new Coord(row, col));
                        }
                    }
                }
            }
            count += moveable.size();
            for (Coord coord : moveable) {
                array.get(coord.row()).set(coord.col(), ".");
            }

        } while (!moveable.isEmpty());
        return count;
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
