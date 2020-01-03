package numb3rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int N, D, P, Q;
    static int[][] connected;
    static int[] deg;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int C = Integer.parseInt(br.readLine());
            for (int i = 0; i < C; i++) {
                String[] meta = br.readLine().split(" ");
                N = Integer.parseInt(meta[0]);
                D = Integer.parseInt(meta[1]);
                P = Integer.parseInt(meta[2]);
                init(br);

                List<Integer> path = new LinkedList<>();
                path.add(P);

                int T = Integer.parseInt(br.readLine());
                String[] targets = br.readLine().split(" ");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < T; j++) {
                    Q = Integer.parseInt(targets[j]);
                    sb.append(String.format("%.8f ", search(path)));
                }
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double search(List<Integer> path) {
        int lastPath = path.get(path.size() - 1);
        if (path.size() == D + 1) {
            if (lastPath != Q) return 0.0;
            double ret = 1.0;
            for (int i = 0; i < path.size() - 1; i++) {
                ret /= deg[path.get(i)];
            }
            return ret;
        }

        double ret = 0;
        for (int there = 0; there < N; there++) {
            if (connected[lastPath][there] == 1) {
                path.add(there);
                ret += search(path);
                path.remove(path.size() - 1);
            }
        }
        return ret;
    }

    private static void init(BufferedReader br) throws IOException {
        connected = new int[N][N];
        deg = new int[N];
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            int count = 0;
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(nums[j]);
                connected[i][j] = temp;
                if (temp == 1) count++;
            }
            deg[i] = count;
        }
    }
}
