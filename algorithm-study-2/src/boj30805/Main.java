package boj30805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>(n);
        Map<Integer, List<Integer>> input1 = new HashMap<>(n);
        int sequence = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            set.add(number);
            input1.computeIfAbsent(number, key -> new ArrayList<>()).add(sequence++);
        }

        int m = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> input2 = new HashMap<>(m);
        Queue<Integer> queue = new PriorityQueue<>(Math.min(n, m), Comparator.reverseOrder());
        st = new StringTokenizer(br.readLine());
        sequence = 1;
        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(st.nextToken());
            input2.computeIfAbsent(number, key -> new ArrayList<>()).add(sequence++);
            if (set.contains(number)) {
                queue.add(number);
            }
        }

        if (queue.isEmpty()) {
            System.out.println(0);
            return;
        }

        List<Integer> result = new ArrayList<>(Math.min(n, m));

        int firstNumber = queue.poll();
        result.add(firstNumber);

        int lastIndex1 = input1.get(firstNumber).get(0);
        int lastIndex2 = input2.get(firstNumber).get(0);

        while (!queue.isEmpty()) {
            int nextNumber = queue.poll();

            final int lastIndex1ForLambda = lastIndex1;
            final int lastIndex2ForLambda = lastIndex2;

            int thisIndex1 = input1.get(nextNumber).stream()
                    .filter(thisIndex -> thisIndex > lastIndex1ForLambda)
                    .findFirst()
                    .orElse(-1);

            int thisIndex2 = input2.get(nextNumber).stream()
                    .filter(thisIndex -> thisIndex > lastIndex2ForLambda)
                    .findFirst()
                    .orElse(-1);

            if (thisIndex1 > -1 && thisIndex2 > -1) {
                result.add(nextNumber);
                lastIndex1 = thisIndex1;
                lastIndex2 = thisIndex2;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        result.forEach(value -> sb.append(value).append(" "));
        System.out.println(sb);
    }

}
