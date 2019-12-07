package quantize;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[] quanArr;
    private static int N, S;
    private static int[][] cache = new int[100][11];
    private static final int INF = 987654321;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int loop = scan.nextInt();

        while (loop-- > 0) {
            N = scan.nextInt();
            S = scan.nextInt();
            quanArr = new int[N];
            for (int i = 0; i < N; i++) {
                quanArr[i] = scan.nextInt();
            }

            //cache init
            for (int[] arr : cache) {
                Arrays.fill(arr, -1);
            }

            Arrays.sort(quanArr);
            System.out.println(quantize(0, S));
        }
    }

    public static int minError(int start, int end) {
        int sum = 0;
        int sqSum = 0;

        for (int i = start; i < end; i++) {
            sum += quanArr[i];
            sqSum += quanArr[i] * quanArr[i];
        }
        int avg = (int) (0.5 + (double) sum / (end - start));

        return sqSum + avg * avg * (end - start) - 2 * avg * sum;
    }

    public static int quantize(int start, int bunchs) {
        //base case
        if (start == N) return 0;
        if (bunchs == 0) return INF;

        //memoization
        if (cache[start][bunchs] != -1) return cache[start][bunchs];

        int ret = INF;
        for (int partSize = 1; start + partSize <= N; partSize++) {
            ret = Math.min(ret, minError(start, start + partSize) + quantize(start + partSize, bunchs - 1));
        }
        cache[start][bunchs] = ret;
        return ret;
    }
}
