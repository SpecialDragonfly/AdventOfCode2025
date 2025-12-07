package DaySeven;

import util.Util;

import java.util.HashSet;
import java.util.Vector;

public class PartOne {
    public long run(String file) {
        Vector<String> lines = Util.readFile(file);

        int startCol = lines.getFirst().indexOf("S");
        int splitCount = 0;
        HashSet<Integer> beams = new HashSet<>();
        beams.add(startCol);

        for (int i = 1; i < lines.size(); i++) {
            // split the line.
            String[] columns = lines.get(i).split("");
            HashSet<Integer> newBeams = new HashSet<>();
            for (int beam : beams) {
                if (columns[beam].equals("^")) {
                    newBeams.add(beam - 1);
                    newBeams.add(beam + 1);
                    splitCount++;
                } else {
                    newBeams.add(beam);
                }
            }
            beams = newBeams;
        }
        return splitCount;
    }
}
