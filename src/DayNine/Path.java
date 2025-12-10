package DayNine;

import java.util.Collection;
import java.util.Vector;

public record Path(long x1, long x2, long y1, long y2) {
    /*
              | (x1, y1)
(p1.x, p1.y)  |     (p2.x, p1.y)
(p1.x, p2.y)  |     (p2.x, p2.y)
              | (x1, y2) -> as vertical, X will be the same

          (p1.x, p1.y)   (p2.x, p1.y)
(x1, y1) ------------------------------ (x2, y1) -> as horizontal, Y will be the same
          (p1.x, p2.y)   (p2.x, p2.y)
 */
    // With a vertical path, does a horizontal beam cross it?
    private boolean verticalChecks(long boxX, long boxY, long boxX1, long boxY1) {
        // If the beam is actually vertical (and so is the path)
        if (isVertical() && boxX == x1 && boxX1 == x1) {
            if (boxY <= Math.min(y1 + 1, y2 + 1) && boxY1 >= Math.max(y1 - 1, y2 - 1)) {
                return true;
            }
        }

        // We only care if the beam is going _through_ the wall, not the corners.
        if (boxY >= Math.min(y1 + 1, y2 + 1) && boxY <= Math.max(y1 - 1, y2 - 1)) {
            // The path is horizontal, so y1 and y2 are the same
            if (boxX <= x1 && boxX1 >= x1) {
                return true;
            }
        }

        return false;
    }

    // With a horizontal path, does a vertical beam cross it?
    private boolean horizontalChecks(long boxX, long boxY, long boxX1, long boxY1) {
        // If the beam is actually horizontal and so is the path.
        if (isHorizontal() && boxY == y1 && boxY1 == y1) {
            if (boxX <= Math.min(x1 + 1, x2 + 1) && boxX1 >= Math.max(x1 - 1, x2 - 1)) {
                return true;
            }
        }
        // We only care if the beam is going _through_ the wall, not the corners.
        if (boxX >= Math.min(x1 + 1, x2 + 1) && boxX <= Math.max(x1 - 1, x2 - 1)) {
            // The path is horizontal, so y1 and y2 are the same
            if (boxY <= y1 && boxY1 >= y1) {
                return true;
            }
        }

        return false;
    }

    public boolean intersects(long boxX, long boxY, long boxX1, long boxY1) {
        boolean vertical = verticalChecks(boxX, boxY, boxX1, boxY1);
        boolean horizontal = horizontalChecks(boxX, boxY, boxX1, boxY1);
        return vertical || horizontal;
    }

    public boolean isHorizontal() {
        return y1 == y2;
    }

    public boolean isVertical() {
        return x1 == x2;
    }

    public Collection<Point> getAllPoints() {
        Vector<Point> points = new Vector<>();
        if (isHorizontal()) {
            long minX = Math.min(x1, x2);
            long maxX = Math.max(x1, x2);
            for (long x = minX; x <= maxX; x++) {
                points.add(new Point(x, y1, 0));
            }
        } else {
            long minY = Math.min(y1, y2);
            long maxY = Math.max(y1, y2);
            for (long y = minY; y <= maxY; y++) {
                points.add(new Point(x1, y, 0));
            }
        }
        return points;
    }
}
