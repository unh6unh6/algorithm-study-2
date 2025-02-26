package boj2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int target1, target2;
    static int m;

    static Node[] nodes;
    static boolean[] visit;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n+1];
        visit = new boolean[n+1];
        nodes[0] = null;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes[x].connect.add(y);
            nodes[y].connect.add(x);
        }
        Node node = nodes[target1];
        visit[target1] = true;
        recursion(node, 0);

        if(min == Integer.MAX_VALUE) {
            min = -1;
        }

        System.out.println(min);
    }
    static void recursion(Node thisNode, int count) {
        if (thisNode.number == target2) {
            min = Integer.min(count, min);
            return;
        }
        for (int nodeNumber : thisNode.connect) {
            if(canGo(nodeNumber)) {
                Node nextNode = nodes[nodeNumber];
                visit[nodeNumber] = true;
                recursion(nextNode, count + 1);
                visit[nodeNumber] = false;
            }
        }
    }

    static boolean canGo(int nodeNumber) {
        return !visit[nodeNumber];
    }
}

class Node {

    int number;
    List<Integer> connect = new ArrayList<>();

    Node(int number) {
        this.number = number;
    }

}
