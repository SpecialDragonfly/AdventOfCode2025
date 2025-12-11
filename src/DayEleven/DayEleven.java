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
        HashMap<String, PathResult> cache = new HashMap<>();
        int result = followPath(cache, "svr", false, false, 0);
        System.out.println("Result: " + result);
    }

    private static void shortenNodes() {
        HashMap<String, String> nodesWithOnlyOneChild = new HashMap<>();
        for (Map.Entry<String, Node> entry : DayEleven.nodes.entrySet()) {
            if (entry.getKey().equals("fft") || entry.getKey().equals("dac")) {
                continue;
            }
            if (entry.getValue().outputs().size() == 1) {
                nodesWithOnlyOneChild.put(entry.getKey(), entry.getValue().outputs().getFirst());
            }
        }
        System.out.println("There were " + nodesWithOnlyOneChild.size() + " shortenings");
        for (Map.Entry<String, Node> node : DayEleven.nodes.entrySet()) {
            for (Map.Entry<String, String> onlyChild : nodesWithOnlyOneChild.entrySet()) {
                if (node.getValue().outputs().contains(onlyChild.getKey())) {
                    DayEleven.nodes.get(node.getKey()).replaceValue(onlyChild.getKey(), onlyChild.getValue());
                }
            }
        }
    }

    private static PathResult followPath(HashMap<String, PathResult> cache, String nodeId, boolean seenFFT, boolean seenDAC, int pathLength) {
        if (nodeId.equals("out")) {
            return new PathResult(1, seenFFT, seenDAC);
        }
        int result = 0;
        if (seenFFT) {
            if (cache.containsKey(nodeId) && cache.get(nodeId).seenDAC) {
                System.out.println("Using cache");
                return cache.get(nodeId);
            }
        }

        for (String node : nodes.get(nodeId).outputs()) {
            result = followPath(cache, node, seenFFT || nodeId.equals("fft"), seenDAC || nodeId.equals("dac"), pathLength+1);
        }
        if (nodeId.equals("dac")) {
            cache.put(nodeId, result);
        }
        if (nodes.get(nodeId).outputs().contains("dac")) {
            cache.put(nodeId, result);
        }
        if (result > 0) {
            System.out.println("Path Length:" + pathLength + " = " + result);
        }
        return result;
    }

    public record PathResult(int path, boolean seenFFT, boolean seenDAC){};
}
