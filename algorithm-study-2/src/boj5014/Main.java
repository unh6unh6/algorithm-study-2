package boj5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F,S,G,U,D;
    static boolean[] visit;

    static int[] offset;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        visit = new boolean[F+1];
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        offset = new int[]{+U, -D};

        Queue<Trial> queue = new LinkedList<>();
        queue.add(new Trial(0, S));
        visit[S] = true;
        boolean flag = false;
        while(!queue.isEmpty()) {
            Trial thisTrial = queue.poll();
            if(thisTrial.floor == G) {
                System.out.println(thisTrial.tryCycle);
                flag = true;
                break;
            }
            for (int dir = 0; dir < 2; dir++) {
                int nextFloor = thisTrial.floor + offset[dir];
                if(canGo(nextFloor)) {
                    Trial nextTrial = thisTrial.getNextTrial(nextFloor);
                    visit[nextFloor] = true;
                    queue.add(nextTrial);
                }
            }
        }
        if(!flag) {
            System.out.println("use the stairs");
        }
    }
    static boolean canGo(int floor) {
        return (floor >= 1 && floor <= F) && !visit[floor];
    }

}

class Trial {

    int tryCycle;
    int floor;

    Trial(final int tryCycle, final int floor) {
        this.tryCycle = tryCycle;
        this.floor = floor;
    }

    Trial getNextTrial(int floor) {
        return new Trial(this.tryCycle + 1, floor);
    }
}
