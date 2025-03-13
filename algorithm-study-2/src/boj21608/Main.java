package boj21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    static int n;

    static int[] numbers;
    static int[][] map;
    static int[][] like;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n * n];
        map = new int[n][n];
        like = new int[n * n + 1][4];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                like[numbers[i]][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int number : numbers) {
            setting(number);
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int number = map[i][j];
                int likeCount = 0;
                Set<Integer> likeSet = getLikeSet(number);
                for (int dir = 0; dir < 4; dir++) {
                    int nextY = i + Choice.offset[dir][0];
                    int nextX = j + Choice.offset[dir][1];
                    if (Choice.canGo(nextY, nextX, map) && likeSet.contains(map[nextY][nextX])) {
                        likeCount++;
                    }
                }
                if (likeCount > 0) {
                    sum += (int) Math.pow(10, likeCount - 1);
                }
            }
        }

        System.out.println(sum);
    }

    private static Comparator<Choice> c = Comparator.comparingInt((Choice choice) -> -choice.likeCount)
            .thenComparingInt((Choice choice) -> -choice.emptyCount)
            .thenComparingInt((Choice choice) -> choice.y)
            .thenComparingInt((Choice choice) -> choice.x);

    private static void setting(int number) {
        TreeSet<Choice> choices = new TreeSet<>(c);
        Set<Integer> likeSet = getLikeSet(number);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                choices.add(new Choice(i, j, map, likeSet));
            }
        }
        Choice bestChoice = choices.first();
        map[bestChoice.y][bestChoice.x] = number;
    }

    static Set<Integer> getLikeSet(final int number) {
        return Arrays.stream(like[number])
                .boxed()
                .collect(Collectors.toSet());
    }
}

class Choice {

    int y, x;
    int likeCount = 0, emptyCount = 0;

    Choice(int y, int x, int[][] map, Set<Integer> myLike) {
        this.y = y;
        this.x = x;
        setCount(y, x, map, myLike);
    }

    static int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void setCount(
            final int y, final int x,
            final int[][] map,
            final Set<Integer> myLike
    ) {
        for (int dir = 0; dir < 4; dir++) {
            int nextY = y + offset[dir][0];
            int nextX = x + offset[dir][1];
            if (canGo(nextY, nextX, map)) {
                int targetNumber = map[nextY][nextX];
                if (targetNumber == 0) {
                    emptyCount++;
                    continue;
                }
                if (myLike.contains(targetNumber)) {
                    likeCount++;
                }
            }
        }
    }

    static boolean canGo(final int nextY, final int nextX, final int[][] map) {
        try {
            int temp = map[nextY][nextX];
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

}
