package quadtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int pointer;
    private static String data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            data = br.readLine();
            pointer = 0;
            System.out.println(reverse());
        }
    }

    private static String reverse() {
        if (data.charAt(pointer) != 'x') {
            pointer++;
            return String.valueOf(data.charAt(pointer - 1));
        }

        pointer++;
        String[] divided = new String[4];
        divided[0] = reverse();  // 좌상
        divided[1] = reverse();  // 우상
        divided[2] = reverse();  // 좌하
        divided[3] = reverse();  // 우하
        return 'x' + divided[2] + divided[3] + divided[0] + divided[1];
    }
}
