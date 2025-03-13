package dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solve2 {

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

        Queue<NodeWithCost> queue = new PriorityQueue<>(Comparator.comparingInt(NodeWithCost::cost));
        queue.add(new NodeWithCost(nodes[startNodeNumber], 0));

        while (!queue.isEmpty()) {
            NodeWithCost nodeWithCost = queue.poll();
            Node pollNode = nodeWithCost.node();
            int pollCost = nodeWithCost.cost();
            if (result[pollNode.number] != pollCost) {
                continue;
            }
            for (Node node : pollNode.connect.keySet()) {
                if (result[node.number] > result[pollNode.number] + pollNode.connect.get(node)) {
                    result[node.number] = result[pollNode.number] + pollNode.connect.get(node);
                    queue.offer(new NodeWithCost(node, result[pollNode.number] + pollNode.connect.get(node)));
                }
            }
        }

        return result;
    }

}

record NodeWithCost(Node node, int cost) {

}
