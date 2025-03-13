package boj1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startNodeNumber = Integer.parseInt(st.nextToken());
            int destinationNodeNumber = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[startNodeNumber].add(destinationNodeNumber, cost);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNum = Integer.parseInt(st.nextToken());
        int endNum = Integer.parseInt(st.nextToken());

        dijkstra(startNum, endNum);
    }

    static void dijkstra(int startNum, int endNum) {
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[startNum] = 0;
        Queue<NodeNumCost> queue = new PriorityQueue<>();
        queue.add(new NodeNumCost(startNum, 0));

        while (!queue.isEmpty()) {
            NodeNumCost pollNodeNumCost = queue.poll();
            int nodeNum = pollNodeNumCost.nodeNumber;
            int cost = pollNodeNumCost.cost;
            if (cost != result[nodeNum]) {
                continue;
            }
            for (NodeNumCost next : nodes[nodeNum].connectNodeNumber) {
                int oldCost = result[next.nodeNumber];
                int newCost = cost + next.cost;
                if (newCost < oldCost) {
                    result[next.nodeNumber] = newCost;
                    queue.add(new NodeNumCost(next.nodeNumber, newCost));
                }
            }
        }

        System.out.println(result[endNum]);
    }
}

class Node {
    List<NodeNumCost> connectNodeNumber = new ArrayList<>();

    void add(int number, int cost) {
        connectNodeNumber.add(new NodeNumCost(number, cost));
    }
}

class NodeNumCost implements Comparable<NodeNumCost> {

    final int nodeNumber, cost;

    NodeNumCost(final int nodeNumber, final int cost) {
        this.nodeNumber = nodeNumber;
        this.cost = cost;
    }

    @Override
    public int compareTo(final NodeNumCost o) {
        return Integer.compare(this.cost, o.cost);
    }
}
