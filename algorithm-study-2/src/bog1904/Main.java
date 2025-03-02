package bog1904;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] memo = new int[n+2];
        memo[0] = 1;
        memo[1] = 2;
        for (int i = 2; i < n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
            if(memo[i] >= 15746) {
                memo[i] %= 15746;
            }
        }
        System.out.println(memo[n-1]);
    }
}
