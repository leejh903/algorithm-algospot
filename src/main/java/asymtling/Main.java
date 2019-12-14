package asymtling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MOD = 1_000_000_007;
    static int[] cache;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            initCache();
            for (int i = 0; i < T; i++) {
                int width = Integer.parseInt(br.readLine());
                System.out.println(asymmetric(width));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initCache() {
        cache = new int[101];
        Arrays.fill(cache, -1);
    }

    private static int asymmetric(int width) {
        if (width % 2 == 1) {
            return (tiling(width) - tiling(width / 2) + MOD) % MOD;
        }
        int ret = tiling(width);
        ret = (ret - tiling(width / 2 - 1) + MOD) % MOD;
        ret = (ret - tiling(width / 2) + MOD) % MOD;
        return ret;
    }

    private static int tiling(int width) {
        if (width <= 1) return 1;
        if (cache[width] != -1) return cache[width];
        return cache[width] = (tiling(width - 1) + tiling(width - 2)) % MOD;
    }
}
