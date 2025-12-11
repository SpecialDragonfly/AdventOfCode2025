package DayEleven;

import util.Util;

import java.util.*;

public class DayEleven {
    private static Map<String, Node> nodes = new HashMap<>();
    public static void main(String[] args) {
        Vector<String> lines = Util.readFile("./src/DayEleven/test2.txt");
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
        int shortening;
        do {
            shortening = 0;
            HashMap<String, Vector<String>> nodesNotFFTOrDAC = new HashMap<>();
            for (Map.Entry<String, Node> entry : nodes.entrySet()) {
                if (entry.getKey().equals("fft") || entry.getKey().equals("dac")) {
                    continue;
                }
                if (!entry.getValue().outputs().contains("fft") && !entry.getValue().outputs().contains("dac")) {
                    nodesNotFFTOrDAC.put(entry.getKey(), entry.getValue().outputs());
                    shortening++;
                }
            }
            System.out.println("There were " + nodesNotFFTOrDAC.size() + " shortenings");
            for (Map.Entry<String, Node> node : nodes.entrySet()) {
                for (Map.Entry<String, Vector<String>> unimportantNodes : nodesNotFFTOrDAC.entrySet()) {
                    nodes.get(node.getKey()).replaceAll(unimportantNodes.getValue(), unimportantNodes.getKey());
                    nodes.remove(node.getKey());
                }
            }
        } while (shortening > 0);
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
