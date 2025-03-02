package boj11052;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] p = new int[n + 1];
        int[] memo = new int[n + 1];
        String[] input = sc.nextLine().split(" ");
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(input[i - 1]);
        }

        memo[1] = p[1];
        for (int i = 2; i <= n; i++) {
            int max = p[i];
            for (int j = 1; j < i; j++) {
                max = Math.max(max, memo[j] + p[i - j]);
            }
            memo[i] = max;
        }
        System.out.println(memo[n]);
    }
}
