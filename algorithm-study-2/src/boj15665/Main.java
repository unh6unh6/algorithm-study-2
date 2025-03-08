package boj15665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static int m;
    static Integer[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Set<Integer> set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        numbers = set.toArray(new Integer[0]);

        dfs(new int[m], 0);
    }

    static void dfs(int[] pocket, int pocketSize) {
        if (pocketSize == m) {
            printPocket(pocket);
            return;
        }
        for (int number : numbers) {
            pocket[pocketSize++] = number;
            dfs(pocket, pocketSize);
            pocketSize--;
        }
    }

    static void printPocket(int[] pocket) {
        StringBuilder sb = new StringBuilder();
        for (int i : pocket) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
