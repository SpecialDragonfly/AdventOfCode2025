package DayEleven;

import util.Util;

import java.util.*;

public class DayEleven {
    private static Map<String, Node> nodes = new HashMap<>();
    public static void main(String[] args) {
        Vector<String> lines = Util.readFile("./src/DayEleven/input.txt");
        for (String line : lines) {
            String[] parts = line.split(":");
            Vector<String> outputs = new Vector<>(List.of(parts[1].trim().split(" ")));
            nodes.put(parts[0], new Node(parts[0], outputs));
        }
        shortenNodes();
        int result = followPath("svr", false, false, 0);
        System.out.println("Result: " + result);
    }

    private static void shortenNodes() {
        HashMap<String, String> nodesWithOnlyOneChild = new HashMap<>();
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            if (entry.getKey().equals("fft") || entry.getKey().equals("dac")) {
                continue;
            }
            if (entry.getValue().outputs().size() == 1) {
                nodesWithOnlyOneChild.put(entry.getKey(), entry.getValue().outputs().getFirst());
            }
        }
        System.out.println("There were " + nodesWithOnlyOneChild.size() + " shortenings");
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            for (Map.Entry<String, String> onlyChild : nodesWithOnlyOneChild.entrySet()) {
                if (node.getValue().outputs().contains(onlyChild.getKey())) {
                    nodes.get(node.getKey()).replaceValue(onlyChild.getKey(), onlyChild.getValue());
                    nodes.remove(node.getKey());
                }
            }
        }
    }

    private static int followPath(String nodeId, boolean seenFFT, boolean seenDAC, int pathLength) {
        if (nodeId.equals("out")) {
            return seenFFT && seenDAC ? 1 : 0;
        }
        int result = 0;

        for (String node : nodes.get(nodeId).outputs()) {
            result += followPath(node, seenFFT || nodeId.equals("fft"), seenDAC || nodeId.equals("dac"), pathLength+1);
        }
        if (result > 0) {
            System.out.println("Path Length:" + pathLength + " = " + result);
        }
        return result;
    }
}
