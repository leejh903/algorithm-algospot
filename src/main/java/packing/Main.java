package packing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n, capacity;
    static int[] volume, need;
    static int[][] cache;
    static String[] name;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                String[] meta = br.readLine().split(" ");
                n = Integer.parseInt(meta[0]);
                capacity = Integer.parseInt(meta[1]);

                init();
                for (int j = 0; j < n; j++) {
                    String[] item = br.readLine().split(" ");
                    name[j] = item[0];
                    volume[j] = Integer.parseInt(item[1]);
                    need[j] = Integer.parseInt(item[2]);
                }

                int maxNeed = pack(capacity, 0);
                List<String> picked = new ArrayList<>();
                reconstruct(capacity, 0, picked);

                System.out.printf("%d %d\n", maxNeed, picked.size());
                for (String s : picked) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reconstruct(int capacity, int item, List<String> picked) {
        if (item == n) return;
        if (pack(capacity, item) == pack(capacity, item + 1)) {
            reconstruct(capacity, item + 1, picked);
        } else {
            picked.add(name[item]);
            reconstruct(capacity - volume[item], item + 1, picked);
        }
    }

    private static int pack(int capacity, int item) {
        if (item == n) return 0;
        if (cache[capacity][item] != -1) return cache[capacity][item];
        int ret = pack(capacity, item + 1);
        if (capacity >= volume[item]) {
            ret = Math.max(ret, pack(capacity - volume[item], item + 1) + need[item]);
        }
        return cache[capacity][item] = ret;
    }

    private static void init() {
        volume = new int[100];
        need = new int[100];
        cache = new int[1001][100];
        name = new String[100];

        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
    }
}
