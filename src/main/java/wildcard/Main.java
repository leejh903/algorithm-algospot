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

    private static boolean match(String p, String n) {
        int pos = 0;
        while (pos < n.length() && pos < p.length()
                && (n.charAt(pos) == p.charAt(pos) || p.charAt(pos) == '?')) {
            pos++;
        }

        if (pos == p.length()) {
            return pos == n.length();
        }

        if (p.charAt(pos) == '*') {
            for (int skip = 0; pos + skip <= n.length(); skip++) {
                if (match(p.substring(pos + 1), n.substring(pos + skip))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean matchMemoized(int p, int n) {
        int ret = cache[p][n];
        if (ret != -1) return ret != 0;
        while (p < P.length() && n < N.length() && (P.charAt(p) == '?' || P.charAt(p) == N.charAt(n))) {
            p++;
            n++;
        }

        if (p == P.length()) {
            return n == N.length();
        }

        if (P.charAt(p) == '*') {
            for (int skip = 0; skip + n <= N.length(); skip++) {
                if (matchMemoized(p + 1, n + skip)) {
                    return true;
                }
            }
        }
        return false;
    }
}
