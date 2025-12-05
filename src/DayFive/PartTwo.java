package DayFive;

import util.Util;

import java.math.BigInteger;
import java.util.Vector;

public class PartTwo {
    public BigInteger run(String file) {
        Vector<String> input = Util.readFile(file);
        Vector<Range> ranges = this.getRanges(input);

        // Part 2: How many numbers are in all ranges combined?
        // Ranges.
        // We need to deal with non-overlapping ranges.
        // |<-->| |<-->| (no problems)
        // We need to deal with high end overlapping ranges
        // |<--->|
        //     |<--->| (previous has been counted. current is previous.high -> current.high)
        // We need to deal with low end overlapping ranges
        //     |<--->|
        // |<--->| (previous has been counted, current is current.low -> previous.low)
        // We need to deal with ranges that are completely enclosed within other ranges.
        // |<------->|
        //   |<--->| (we deal with this case)
        // We need to deal with the reverse
        //   |<--->|
        // |<------->| (we compare by size, so biggest ranges should already have come up.)

        BigInteger number = ranges.getFirst().size();
        for (int i = 1; i < ranges.size(); i++) {
            Range currentRange = ranges.get(i);
            // check whether this range has already been considered in anything previous.
            boolean contained = false;
            for (int j = 0; j < i; j++) {
                Range previousRange = ranges.get(j);
                // Handle fully enclosed
                if (previousRange.contains(currentRange)) {
                    contained = true;
                    break;
                }
                // Handle low end overlapping
                if (previousRange.isInRange(currentRange.smallest())) {
                    currentRange = new Range(
                        previousRange.largest().add(BigInteger.ONE),
                        currentRange.largest()
                    );
                }
                // Handle high end overlapping
                if (previousRange.isInRange(currentRange.largest())) {
                    currentRange = new Range(
                        currentRange.smallest(),
                        previousRange.smallest().subtract(BigInteger.ONE)
                    );
                }
            }
            if (!contained) {
                number = number.add(currentRange.size());
            }
        }

        return number;
    }

    private Vector<Range> getRanges(Vector<String> input) {
        Vector<Range> ranges = new Vector<>();
        for (String line : input) {
            if (line.trim().isEmpty()) {
                break;
            }
            ranges.add(
                new Range(
                    new BigInteger(line.split("-")[0]),
                    new BigInteger(line.split("-")[1])
                )
            );
        }

        // Sort the ranges by their size
        ranges.sort((a, b) -> b.size().compareTo(a.size()));

        return ranges;
    }
}