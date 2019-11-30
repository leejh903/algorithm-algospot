package jlis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    //행렬 a,b의 길이
    private static int m, n;
    //행렬 a,b의 값
    private static int[] a, b;
    private static long[][] cache;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                String tmp = br.readLine();
                n = Integer.parseInt(tmp.split(" ")[0]);
                m = Integer.parseInt(tmp.split(" ")[1]);
                a = new int[n];
                b = new int[m];
                cache = new long[n + 1][m + 1];

                String[] a_tmp = br.readLine().split(" ");
                for (int j = 0; j < a_tmp.length; j++) {
                    a[j] = Integer.parseInt(a_tmp[j]);
                    Arrays.fill(cache[j], -1);
                }
                Arrays.fill(cache[n], -1);

                String[] b_tmp = br.readLine().split(" ");
                for (int j = 0; j < b_tmp.length; j++) {
                    b[j] = Integer.parseInt(b_tmp[j]);
                }

                System.out.println(jlis(-1, -1) - 2);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static long jlis(int a_pos, int b_pos) {
        //이전 탐색값이 있는경우 이전 탐색값 반환
        if (cache[a_pos + 1][b_pos + 1] != -1) {
            return cache[a_pos + 1][b_pos + 1];
        }
        long a_max = a_pos == -1 ? Long.MIN_VALUE : a[a_pos];
        long b_max = b_pos == -1 ? Long.MIN_VALUE : b[b_pos];
        //현재 합친 행렬에서 행렬의 끝값
        long max_val = Math.max(a_max, b_max);

        long ret = 2;
        for (int i = a_pos + 1; i < n; i++) {
            //합친 행렬의 마지막값보다 큰경우 해당값을 포함한 행렬의 길이 계산
            if (max_val < a[i]) {
                ret = Math.max(ret, jlis(i, b_pos) + 1);
            }
        }

        for (int i = b_pos + 1; i < m; i++) {
            //합친 행렬의 마지막값보다 큰경우 해당값을 포함한 행렬의 길이 계산
            if (max_val < b[i]) {
                ret = Math.max(ret, jlis(a_pos, i) + 1);
            }
        }

        cache[a_pos + 1][b_pos + 1] = ret;
        return ret;
    }
}