package DayEight;

public record Point(long x, long y, long z) {
    public long distanceTo(Point other) {
        return (long) (Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2));
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
