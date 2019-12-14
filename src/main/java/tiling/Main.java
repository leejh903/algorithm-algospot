package tiling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MOD = 1000000007;
    static int[] cache;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            initCache();
            for (int i = 0; i < T; i++) {
                int width = Integer.parseInt(br.readLine());
                System.out.println(tiling(width));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initCache() {
        cache = new int[101];
        Arrays.fill(cache, -1);
    }

    private static int tiling(int width) {
        if (width <= 1) return 1;
        if (cache[width] != -1) return cache[width];
        return cache[width] = (tiling(width - 1) + tiling(width - 2)) % MOD;
    }
}
