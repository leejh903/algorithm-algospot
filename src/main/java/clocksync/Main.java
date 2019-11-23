package clocksync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static final int MAX = 9999;
    public static final int CLOCK = 16;
    public static final int SWITCH = 10;
    public static final int[][] LINKS = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int[] clocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            int tmp = dfs(clocks, 0);
            System.out.println(tmp >= MAX ? -1 : tmp);
        }
    }

    private static int dfs(int[] clocks, int nthSwitch) {
        if (nthSwitch == SWITCH) {
            if (isDone(clocks)) return 0;
            return MAX;
        }

        int result = MAX;
        for (int cnt = 0; cnt < 4; cnt++) {
            push(clocks, nthSwitch, cnt);
            result = Math.min(result, cnt + dfs(clocks, nthSwitch + 1));
            push(clocks, nthSwitch, 4 - cnt);
        }

        return result;
    }

    private static void push(int[] clocks, int nthSwitch, int count) {
        for (int clock : LINKS[nthSwitch]) {
            int sum = clocks[clock] + (3 * count);
            if (sum > 12) sum -= 12;
            clocks[clock] = sum;
        }
    }

    private static boolean isDone(int[] clocks) {
        for (int clock : clocks) {
            if (clock != 12) return false;
        }
        return true;
    }
}
