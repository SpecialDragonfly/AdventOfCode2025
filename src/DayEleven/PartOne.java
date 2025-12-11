package DayEleven;

import util.Util;

import java.util.*;

public class PartOne {
    public void run() {
        Vector<String> lines = Util.readFile("./src/DayEleven/input.txt");
        Map<String, Node> nodes = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            Vector<String> outputs = new Vector<>(List.of(parts[1].trim().split(" ")));
            nodes.put(parts[0], new Node(parts[0], outputs));
        }

        Queue<String> paths = new PriorityQueue<>();
        paths.add("you");
        int pathCount = 0;
        while (true) {
            String node = paths.poll();
            if (node == null) {
                break;
            }
            if (node.equals("out")) {
                pathCount++;
                continue;
            }
            paths.addAll(nodes.get(node).outputs());
        }
        System.out.println("Path count: " + pathCount);
    }
}
