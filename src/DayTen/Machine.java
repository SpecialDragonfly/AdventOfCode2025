package DayTen;

import java.util.Arrays;
import java.util.Vector;

public class Machine {
    Vector<Boolean> goal = new Vector<>();
    Vector<Boolean> state = new Vector<>();
    Vector<Button> buttons = new Vector<>();
    Vector<Integer> joltage = new Vector<>();

    public Machine(Vector<Boolean> goal, Vector<Button> buttons, Vector<Integer> joltage) {
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

    public Vector<Integer> getJoltage() {
        return this.joltage;
    }

    public boolean checkViable(Vector<Integer> possible) {
        for (int i = 0; i < possible.size() - 1; i++) {
            if (this.joltage.get(i) < possible.get(i)) {
                return false;
            }
        }
        return true;
    }
}
