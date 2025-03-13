package dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Solve1 {

    static Node[] nodes = new Node[6 + 1];

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0] = null;
        nodes[1].connect(Map.of(
                nodes[2], 3,
                nodes[3], 2,
                nodes[4], 5
        ));
        nodes[2].connect(Map.of(
                nodes[3], 2,
                nodes[5], 8
        ));
        nodes[3].connect(Map.of(nodes[4], 2));
        nodes[4].connect(Map.of(nodes[5], 6));
        nodes[5].connect(Map.of(nodes[6], 1));

        int[] result = dijkstra(1);
        for (int i = 1; i < result.length; i++) {
            System.out.println(i + " node : " + result[i]);
        }

    }

    static int[] dijkstra(final int startNodeNumber) {
        int[] result = new int[7];
        Arrays.fill(result, Integer.MAX_VALUE);
        boolean[] isFix = new boolean[7];
        isFix[0] = true;

        result[startNodeNumber] = 0;
        isFix[startNodeNumber] = true;
        Node lastFixed = nodes[startNodeNumber];

        while(!isAllFixed(isFix)) {
            int minimumCost = Integer.MAX_VALUE;
            Node minimumNode = null;

            for(Entry entry : lastFixed.connect.entrySet()) {
                Node node = (Node) entry.getKey();
                int cost = (int) entry.getValue();
                if(isFix[node.number]) {
                    continue;
                }
                result[node.number] = Math.min(result[node.number], result[lastFixed.number] + cost);
                if(minimumCost > result[node.number]) {
                    minimumNode = node;
                    minimumCost = result[node.number];
                }
            }
            if(minimumNode == null) {
                break;
            }
            isFix[minimumNode.number] = true;
            lastFixed = minimumNode;
        }

        return result;
    }

    static boolean isAllFixed(boolean[] isFix) {
        for (boolean fix : isFix) {
            if (!fix) {
                return false;
            }
        }
        return true;
    }
}

class Node {
    int number;

    Map<Node, Integer> connect = new HashMap<>();

    public Node(final int number) {
        this.number = number;
    }

    public void connect(Map<Node, Integer> nodeWithCost) {
        for (Entry entry : nodeWithCost.entrySet()) {
            connect.put((Node) entry.getKey(), (Integer) entry.getValue());
        }
    }
}
