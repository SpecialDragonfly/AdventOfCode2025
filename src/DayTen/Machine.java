package DayTen;

import java.util.Arrays;
import java.util.Vector;

public class Machine {
    Vector<Boolean> goal = new Vector<>();
    Vector<Boolean> state = new Vector<>();
    Vector<Button> buttons = new Vector<>();
    String joltage = "";

    public Machine(Vector<Boolean> goal, Vector<Button> buttons, String joltage) {
        this.goal = goal;
        this.buttons = buttons;
        this.joltage = joltage;
        for (int i = 0; i < goal.size(); i++) {
            this.state.add(false);
        }
    }

    public Vector<Boolean> getState() {
        return this.state;
    }

    public Vector<Button> getButtons() {
        return this.buttons;
    }

    public Vector<Boolean> getGoal() {
        return this.goal;
    }
}
