package DayEight;

import util.Util;

import java.util.*;

public class PartTwo {
    public long run(String file) {
        Vector<String> lines = Util.readFile(file);
        List<Point> points = lines.stream()
                .map(l -> l.split(","))
                .map(parts -> new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2])))
                .toList();

        // We need a lookup of all points to all other points.
        HashMap<Point, HashMap<Point, Long>> distances = getDistances(points);
        // Sort them by shortest distance first.
        List<Map.Entry<Long, Vector<Point>>> checking = flatten(distances);
        // Our list of circuits.
        Vector<Circuit> circuits = new Vector<>();

        Vector<Point> joiningPoints = new Vector<>();
        for (Map.Entry<Long, Vector<Point>> value : checking) {
            Point p1 = value.getValue().get(0);
            Point p2 = value.getValue().get(1);

            // We've now found the closest pair.
            // Do either exist in a circuit? If not, we make a new circuit!
            Circuit firstCircuit = null;
            Circuit secondCircuit = null;
            for (Circuit circuit : circuits) {
                if (circuit.contains(p1)) {
                    firstCircuit = circuit;
                }
                if (circuit.contains(p2)) {
                    secondCircuit = circuit;
                }
            }

            if (firstCircuit == null && secondCircuit == null) {
                Circuit circuit = new Circuit();
                circuit.addPoint(p1);
                circuit.addPoint(p2);
                circuits.add(circuit);
            } else if (firstCircuit != null && secondCircuit != null) {
                // Combine the circuits!
                if (firstCircuit != secondCircuit) {
                    firstCircuit.addAll(secondCircuit);
                    circuits.remove(secondCircuit);
                }
            } else if (firstCircuit == null) {
                secondCircuit.addPoint(p1);
            } else {
                firstCircuit.addPoint(p2);
            }

            if (circuits.size() == 1 && circuits.getFirst().size() == points.size()) {
                joiningPoints.add(p1);
                joiningPoints.add(p2);
                break;
            }
        }

        return joiningPoints.get(0).x() * joiningPoints.get(1).x();
    }

    private HashMap<Point, HashMap<Point, Long>> getDistances(List<Point> points) {
        HashMap<Point, HashMap<Point, Long>> distances = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                if (!distances.containsKey(p1)) {
                    distances.put(p1, new HashMap<>());
                }
                distances.get(p1).put(p2, p1.distanceTo(p2));
            }
        }

        return distances;
    }

    private List<Map.Entry<Long, Vector<Point>>> flatten(HashMap<Point, HashMap<Point, Long>> dictionary) {

        List<Map.Entry<Long, Vector<Point>>> pairs = new ArrayList<>();

        for (Map.Entry<Point, HashMap<Point, Long>> outer : dictionary.entrySet()) {
            Point p1 = outer.getKey();
            Map<Point, Long> inner = outer.getValue();
            if (inner == null) {
                continue;
            }
            for (Map.Entry<Point, Long> innerEntry : inner.entrySet()) {
                Point p2 = innerEntry.getKey();
                Long value = innerEntry.getValue();
                Vector<Point> v = new Vector<>();
                v.add(p1);
                v.add(p2);
                pairs.add(Map.entry(value, v));
            }
        }

        // sort by Long asc
        pairs.sort(Comparator.comparing(Map.Entry<Long, Vector<Point>>::getKey));

        return pairs;
    }
}
