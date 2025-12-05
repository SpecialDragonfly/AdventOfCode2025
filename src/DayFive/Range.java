package DayFive;

import java.math.BigInteger;

public record Range(BigInteger smallest, BigInteger largest) {
    public boolean isInRange(BigInteger value) {
        return smallest.compareTo(value) <= 0 && largest.compareTo(value) >= 0;
    }

    public BigInteger size() {
        // range {10, 11, 12} is a value of 3, not a value of 2.
        return largest.subtract(smallest).add(BigInteger.ONE);
    }

    public boolean contains(Range currentRange) {
        return smallest.compareTo(currentRange.smallest()) <= 0 && largest.compareTo(currentRange.largest()) >= 0;
    }

    public String toString() {
        return "[" + smallest + ", " + largest + "]";
    }
}
