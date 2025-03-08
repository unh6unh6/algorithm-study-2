package boj1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] cost;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        cost = new int[n][3];
        memo = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memo[0][0] = cost[0][0];
        memo[0][1] = cost[0][1];
        memo[0][2] = cost[0][2];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                memo[i][j] = cost[i][j] + Math.min(memo[i-1][(j + 4)%3], memo[i-1][(j + 2)%3]);
            }
        }

        int min = Math.min(memo[n-1][0], memo[n-1][1]);
        min = Math.min(memo[n-1][2], min);

        System.out.println(min);
    }
}

// memo : 해당 번째 집을 해당 색깔로 칠했을 때의 현재까지의 최소 비용
