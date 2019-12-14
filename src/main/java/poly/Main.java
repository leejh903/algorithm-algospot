package poly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MOD = 10000000;
    static int[][] cache;

    private static void initCache() {
        cache = new int[101][100];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            initCache();
            for (int i = 0; i < T; i++) {
                int n = Integer.parseInt(br.readLine());
                int answer = 0;
                for (int j = 1; j <= n; j++) {
                    answer += poly(n, j);
                    answer %= MOD;
                }
                System.out.println(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int poly(int n, int first) {
        if (n == first) return 1;
        if (cache[n][first] != -1) return cache[n][first];

        int ret = 0;
        for (int second = 1; second <= n - first; second++) {
            int add = second + first - 1;
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }
        return cache[n][first] = ret;
    }
}
