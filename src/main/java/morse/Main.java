package morse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int M = 1000000000 + 100;
    static int[][] bino = new int[201][201];
    static int skip;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        calcBino();
        for (int i = 0; i < N; i++) {
            String[] t = br.readLine().split(" ");
            int n = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            int k = Integer.parseInt(t[2]);

            skip = k - 1;
//            generate(n, m, "");
            System.out.println(kth(n, m, skip));
        }
    }

    private static void generate(int n, int m, String s) {
        if (skip < 0) return;

        if (n == 0 && m == 0) {
            if (skip == 0) System.out.println(s);
            skip--;
            return;
        }

        if (bino[n + m][n] <= skip) {
            skip -= bino[n + m][n];
            return;
        }

        if (n > 0) generate(n - 1, m, s + "-");
        if (m > 0) generate(n, m - 1, s + "o");
    }

    private static String kth(int n, int m, int skip) {
        if (n == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append('o');
            }
            return sb.toString();
        }

        if (skip < bino[n + m - 1][n - 1]) {
            return "-" + kth(n - 1, m, skip);
        }
        return "o" + kth(n, m - 1, skip - bino[n + m - 1][n - 1]);
    }

    private static void calcBino() {
        for (int i = 0; i < 200; i++) {
            bino[i][0] = bino[i][i] = 1;
            for (int j = 1; j < i; j++) {
                bino[i][j] = Math.min(M, bino[i - 1][j - 1] + bino[i - 1][j]);
            }
        }
    }
}
