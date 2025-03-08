package boj6593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = br.readLine();
            if (input.equals("0 0 0")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(input);
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            char[][][] tower = new char[L][R][C];
            Pos start = setTower(L, R, C, tower);
            process(start, L, R, C, tower);
        }
    }

    static void process(Pos startPos, int L, int R, int C, char[][][] tower) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(startPos);
        int result = -1;
        boolean[][][] visit = new boolean[L][R][C];
        while (!queue.isEmpty()) {
            Pos thisPos = queue.poll();
            if (tower[thisPos.z][thisPos.y][thisPos.x] == 'E') {
                result = thisPos.second;
                break;
            }
            for (int dir = 0; dir < 6; dir++) {
                Pos nextPos = new Pos(thisPos, dir);
                if (canGo(tower, visit, nextPos, L, R, C)) {
                    visit[nextPos.z][nextPos.y][nextPos.x] = true;
                    queue.add(nextPos);
                }
            }
        }
        if (result >= 0) {
            System.out.printf("Escaped in %d minute(s).\n", result);
            return;
        }
        System.out.println("Trapped!");
    }

    static boolean canGo(char[][][] tower, boolean[][][] visit, Pos pos, int L, int R, int C) {
        return (pos.z >= 0 && pos.z < L && pos.y >= 0 && pos.y < R && pos.x >= 0 && pos.x < C)
                && (tower[pos.z][pos.y][pos.x] != '#' && !visit[pos.z][pos.y][pos.x]);
    }

    static Pos setTower(int L, int R, int C, char[][][] tower) throws IOException {
        Pos start = null;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                char[] line = br.readLine().toCharArray();
                for (int k = 0; k < C; k++) {
                    tower[i][j][k] = line[k];
                    if (tower[i][j][k] == 'S') {
                        start = new Pos(i, j, k);
                    }
                }
            }
            br.readLine();
        }
        return start;
    }
}

class Pos {
    int z, y, x;
    int second = 0;

    int[][] offset = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {-1, 0, 0}, {0, -1, 0}, {0, 0, -1}};

    public Pos(final int z, final int y, final int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }

    public Pos(final Pos pos, final int dir) {
        this.x = pos.x + offset[dir][0];
        this.y = pos.y + offset[dir][1];
        this.z = pos.z + offset[dir][2];
        this.second = pos.second + 1;
    }
}
