package DayTen;

import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTen {
    public static void main(String[] args) {
        Vector<String> lines = Util.readFile("./src/DayTen/input.txt");
        Pattern p = Pattern.compile("\\[(.*?)](.*?)\\{(.*?)}");
        Vector<Machine> machines = new Vector<>();
        for (String line : lines) {
            System.out.println("Line: " + line);
            Matcher m = p.matcher(line);
            if (m.matches()) {
                Vector<Boolean> goal = new Vector<>(Arrays.stream(m.group(1).split("")).map(s -> s.equals("#")).toList());
                Vector<Button> buttons = new Vector<>(Arrays.stream(m.group(2).trim().split(" ")).map(s -> {
                    int[] parts = Arrays.stream(s.substring(1, s.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                    return new Button(parts);
                }).toList());
                String joltage = m.group(3);
                Machine machine = new Machine(goal, buttons, joltage);
                machines.add(machine);
            } else {
                System.out.print("  -> No matches found.\n");
            }
        }

        int sum = 0;
        for (Machine machine : machines) {
            Set<Vector<Boolean>> states = new HashSet<>();
            states.add(machine.getState());
            int iteration = 0;
            while (true) {
                Set<Vector<Boolean>> newStates = new HashSet<>();
                for (Vector<Boolean> state : states) {
                    for (Button button : machine.getButtons()) {
                        newStates.add(button.push((Vector<Boolean>) state.clone()));
                    }
                }
                if (newStates.contains(machine.getGoal())) {
                    iteration++;
                    break;
                }
                states = newStates;
                iteration++;
            }
            sum += iteration;
        }
        System.out.println("Sum: " + sum);
    }
}
