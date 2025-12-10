package DayTen;

import java.util.Vector;

public class Button {
    private final int[] values;

    public Button(int[] values) {
        this.values = values;
    }

    public Vector<Boolean> push(Vector<Boolean> state) {
        for (int index : values) {
            state.set(index, !state.get(index));
        }
        return state;
    }
}
