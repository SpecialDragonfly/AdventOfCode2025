package DayNine;

import util.Util;

import java.util.*;

public class PartTwo {
    // Too high: 4606709328
    // Too high: 4342221192
    // Too high: 3000463632
    public void run(String file) {
        Vector<String> lines = Util.readFile(file);
        List<Point> points = lines.stream()
                .map(l -> l.split(","))
                .map(parts -> new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1]), 0))
                .toList();

        // Step 1 needs to be make the path.
        Vector<Path> paths = makePaths(points);

        // Step 2 is to make the areas
        HashMap<Point, HashMap<Point, Long>> areas = getAreas(points);
        List<Map.Entry<Long, Vector<Point>>> checking = flatten(areas);
        Set<Point> cache = new HashSet<>();
        Set<Point> knownBad = new HashSet<>();
        Set<Point> pathPoints = new HashSet<>();
        for (Path path : paths) {
            pathPoints.addAll(path.getAllPoints());
        }

        long gridMaxX = points.stream().mapToLong(p -> p.x()).max().orElse(0) + 1;
        long gridMaxY = points.stream().mapToLong(p -> p.y()).max().orElse(0) + 1;

        for (Map.Entry<Long, Vector<Point>> entry : checking) {
            long startTime = System.currentTimeMillis();
            Vector<Point> entryPoints = entry.getValue();
            System.out.println("Looking at rectangle: " + entryPoints + " with size: " + entry.getKey());
            // Area is bounded by points[0] and points[1]
            long minX = Math.min(entryPoints.getFirst().x(), entryPoints.getLast().x());
            long maxX = Math.max(entryPoints.getFirst().x(), entryPoints.getLast().x());
            long minY = Math.min(entryPoints.getFirst().y(), entryPoints.getLast().y());
            long maxY = Math.max(entryPoints.getFirst().y(), entryPoints.getLast().y());

            // Check knownBad range
            if (knownBadInArea(minX, maxX, minY, maxY, knownBad)) {
                System.out.println("\tKnown bad range");
                continue;
            }
            System.out.println("Cache size: " + cache.size() + " KnownBadPoints: " + knownBad.size() + " Points in paths: " + pathPoints.size());
            try {
                for (long x = minX; x <= maxX; x++) {
                    for (long y = minY; y <= maxY; y++) {
                        if (x % 100 == 0 && y % 100 == 0) {
                            System.out.print(new Point(x, y, 0));
                        }
                        if (pathPoints.contains(new Point(x, y, 0))) {
                            // Ignore points on a path. They're already fine.
                            continue;
                        }
                        if (cache.contains(new Point(x, y, 0))) {
                            //System.out.println("\tAlready checked. Known good.");
                            continue;
                        }

                        if (knownBad.contains(new Point(x, y, 0))) {
                            throw new Exception("Out of bounds");
                        }

                        if (intersections(x, gridMaxX, y, gridMaxY, paths)) {
                            knownBad.add(new Point(x, y, 0));
                            //System.out.println("\tNot in bounds");
                            throw new Exception("Not in bounds");
                        } else {
                            cache.add(new Point(x, y, 0));
                        }
                    }
                }
                System.out.println(entry);
                break;
            } catch (Exception ex) {
                long endTime = System.currentTimeMillis();
                System.out.println("Iteration time: " + (endTime - startTime) + " ms");
                // Do nothing, essentially this entry wasn't suitable.
                System.out.println("------");
            }
        }

        System.out.println("done");
    }

    private boolean knownBadInArea(long minX, long maxX, long minY, long maxY, Set<Point> knownBad) {
        for (Point p : knownBad) {
            long x = p.x();
            long y = p.y();
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                return true;
            }
        }
        return false;
    }

    private boolean intersections(long x, long gridMaxX, long y, long gridMaxY, Vector<Path> paths) {
        // We have 2 lines
        // (x, 0) -> (x,y) -> (x, gridMaxY) and (0, y) -> (x,y) -> (gridMaxX, y)
        // How many times does this point intersect going N, S, E, W
        // If it's an odd number at the end of checking all paths, just return.
        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;

        for (Path p : paths) {
            // Go Up
            if (p.intersects(x, 0, x, y - 1)) {
                //System.out.println("\tUp. Intersects with path:" + p);
                up++;
            }
            if (p.intersects(x, y + 1, x, gridMaxY)) {
                //System.out.println("\tDown. Intersects with path:" + p);
                down++;
            }
            if (p.intersects(0, y, x - 1, y)) {
                //System.out.println("\tLeft. Intersects with path:" + p);
                left++;
            }
            if (p.intersects(x + 1, y, gridMaxX, y)) {
                right++;
            }
        }

        //System.out.println("\t("+x+", "+y+") Up: " + up + " Down: " + down + " Left: " + left + " Right: " + right);
        return (up % 2 == 0) || (down % 2 == 0) || (left % 2 == 0) || (right % 2 == 0);
    }

    private Vector<Path> makePaths(List<Point> points) {
        Vector<Path> paths = new Vector<>();
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = (i+1 >= points.size()) ? points.getFirst() : points.get(i+1);
            Path path = new Path(p1.x(), p2.x(), p1.y(), p2.y());
            paths.add(path);
        }

        return paths;
    }

    private HashMap<Point, HashMap<Point, Long>> getAreas(List<Point> points) {
        HashMap<Point, HashMap<Point, Long>> areas = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                // Is it valid to make an area between these points?
                // Step 3 is to make sure none of the areas intersect a path. They can meet it, but can't cross it
                if (!areas.containsKey(p1)) {
                    areas.put(p1, new HashMap<>());
                }
                areas.get(p1).put(p2, getArea(p1, p2));
            }
        }

        return areas;
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
        pairs.sort(Comparator.comparing(e -> e.getKey() * -1));

        return pairs;
    }

    private long getArea(Point p1, Point p2) {
        //  2,5 and 11,1 = 50
        long maxX = Math.max(p1.x(), p2.x());
        long minX = Math.min(p1.x(), p2.x());
        long maxY = Math.max(p1.y(), p2.y());
        long minY = Math.min(p1.y(), p2.y());
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}
