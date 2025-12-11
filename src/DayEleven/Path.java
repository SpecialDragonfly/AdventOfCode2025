package DayEleven;

import java.util.Arrays;
import java.util.Vector;

public class Path {
    private Vector<String> history = new Vector<>();
    public Path(String start) {
        history.add(start);
    }
    public Path(Vector<String> history) {
        this.history = history;
    }

    public void add(String node) {
        history.add(node);
    }
    public String getLast() {
        return history.lastElement();
    }

    public boolean wasValid() {
        return history.contains("fft") && history.contains("dac");
    }

    public Path getCopy() {
        Vector<String> newHistory = new Vector<>(this.history);
        return new Path(newHistory);
    }

    public boolean contains(String value) {
        return this.history.contains(value);
    }
}
