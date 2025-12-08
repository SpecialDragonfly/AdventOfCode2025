package DayEight;

import java.util.Vector;

public class Circuit {
    Vector<Point> points = new Vector<>();
    public Circuit() {

    }

    public int size() {
        return points.size();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean contains(Point point) {
        return points.contains(point);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            sb.append(p).append(", ");
        }
        return "[" + sb.substring(0, sb.length()-2) + "]";
    }

    public void addAll(Circuit secondCircuit) {
        for (Point p : secondCircuit.points) {
            this.addPoint(p);
        }
    }
}
