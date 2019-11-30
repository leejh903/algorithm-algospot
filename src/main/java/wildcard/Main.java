package wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static String P, N;
    private static int[][] cache = initCache();

    private static int[][] initCache() {
        int[][] temp = new int[101][101];
        for (int[] ints : temp) {
            Arrays.fill(ints, -1);
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            P = br.readLine();
            int count = Integer.parseInt(br.readLine());
            List<String> matched = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                N = br.readLine();
                if (matchMemoized(0, 0)) {
                    matched.add(N);
                }
            }

            Collections.sort(matched);
            for (String s : matched) {
                System.out.println(s);
            }
        }
    }

    private static boolean matchMemoized(int p, int n) {
        int ret = cache[p][n];
        if (ret != -1) return ret != 0;
        while (p < P.length() && n < N.length() && (P.charAt(p) == '?' || P.charAt(p) == N.charAt(n))) {
            return matchMemoized(p + 1, n + 1);
        }

        if (p == P.length()) {
            return n == N.length();
        }

        if (P.charAt(p) == '*') {
            if(matchMemoized(p + 1, n) || (n < N.length() && matchMemoized(p, n + 1))) {
                return true;
            }
        }
        return false;
    }
}
