package DayEleven;

import java.util.Vector;

public record Node(String id, Vector<String> outputs) {
    public void replaceValue(String key, String value) {
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i).equals(key)) {
                outputs.set(i, value);
            }
        }
    }
}
