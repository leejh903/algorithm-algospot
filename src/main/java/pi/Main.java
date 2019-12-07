package pi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] cache;
    private static String S;
    private static int MAX = 987654321;

    private static void initCache() {
        int[] temp = new int[100002];
        Arrays.fill(temp, -1);
        cache = temp;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                initCache();
                S = br.readLine();
                System.out.println(memorize(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int memorize(int begin) {
        if (begin == S.length()) return 0;
        if (cache[begin] != -1) return cache[begin];

        int temp = MAX;
        for (int L = 3; L <= 5; L++) {
            if (begin + L <= S.length()) {
                int part = classify(begin, begin + L) + memorize(begin + L);
                temp = Math.min(temp, part);
            }
        }
        return cache[begin] = temp;
    }

    private static int classify(int a, int b) {
        String M = S.substring(a, b);

        char[] copy = new char[M.length()];
        Arrays.fill(copy, M.charAt(0));
        if (Arrays.equals(M.toCharArray(), copy)) return 1;

        boolean progressive = true;
        for (int i = 0; i < M.length() - 1; i++) {
            if (M.charAt(i + 1) - M.charAt(i) != M.charAt(1) - M.charAt(0)) {
                progressive = false;
            }
        }

        if (progressive && Math.abs(M.charAt(1) - M.charAt(0)) == 1) {
            return 2;
        }

        boolean altering = true;
        for (int i = 0; i < M.length(); i++) {
            if (M.charAt(i) != M.charAt(i % 2)) {
                altering = false;
            }
        }

        if (altering) return 4;
        if (progressive) return 5;
        return 10;
    }
}
