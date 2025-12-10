package DayNine;

import util.Util;

import java.util.*;

public class PartOne {
    public long run(String file) {
        Vector<String> lines = Util.readFile(file);
        List<Point> points = lines.stream()
                .map(l -> l.split(","))
                .map(parts -> new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1]), 0))
                .toList();
        HashMap<Point, HashMap<Point, Long>> distances = getAreas(points);
        List<Map.Entry<Long, Vector<Point>>> checking = flatten(distances);

        System.out.println(checking.getLast());
        return checking.getLast().getKey();
    }

    private HashMap<Point, HashMap<Point, Long>> getAreas(List<Point> points) {
        HashMap<Point, HashMap<Point, Long>> areas = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                if (!areas.containsKey(p1)) {
                    areas.put(p1, new HashMap<>());
                }
                areas.get(p1).put(p2, getArea(p1, p2));
            }
        }

        return areas;
    }

    private long getArea(Point p1, Point p2) {
        //  2,5 and 11,1 = 50
        long maxX = p1.x();
        long minX = p1.x();
        long maxY = p1.y();
        long minY = p1.y();
        if (p2.x() > maxX) {
            maxX = p2.x();
        }
        if (p2.x() < minX) {
            minX = p2.x();
        }
        if (p2.y() > maxY) {
            maxY = p2.y();
        }
        if (p2.y() < minY) {
            minY = p2.y();
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    private List<Map.Entry<Long, Vector<Point>>> flatten(HashMap<Point, HashMap<Point, Long>> dictionary) {

        List<Map.Entry<Long, Vector<Point>>> pairs = new ArrayList<>();

        for (Map.Entry<Point, HashMap<Point, Long>> outer : dictionary.entrySet()) {
            Point p1 = outer.getKey();
            Map<Point, Long> inner = outer.getValue();
            if (inner == null) continue;
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
