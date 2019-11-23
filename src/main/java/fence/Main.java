package fence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int C = Integer.parseInt(br.readLine());
            heights = getHeights(br, C);

            int answer = getWide(0, heights.length - 1);
            System.out.println(answer);
        }
    }

    private static int getWide(int left, int right) {
        if (left == right) {
            return heights[left];
        }

        int mid = (left + right) / 2;
        int ret = Math.max(getWide(left, mid), getWide(mid + 1, right));

        int lo = mid, hi = mid + 1;
        int height = Math.min(heights[lo], heights[hi]);

        ret = Math.max(ret, height * 2);
        while (left < lo || hi < right) {
            if (hi < right && (lo == left || heights[lo - 1] < heights[hi + 1])) {
                hi++;
                height = Math.min(height, heights[hi]);
            } else {
                lo--;
                height = Math.min(height, heights[lo]);
            }
            ret = Math.max(ret, (hi - lo + 1) * height);
        }
        return ret;
    }

    public static int[] getHeights(BufferedReader br, int C) throws IOException {
        int[] heights = new int[C];
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        int c = 0;
        while (stk.hasMoreTokens()) {
            heights[c] = Integer.parseInt(stk.nextToken());
            c++;
        }
        return heights;
    }
}
