package wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String pattern = br.readLine();
            int count = Integer.parseInt(br.readLine());
            List<String> matched = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                String name = br.readLine();
                if (match(pattern, name)) {
                    matched.add(name);
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
                if(match(p.substring(pos + 1), n.substring(pos + skip))) {
                    return true;
                }
            }
        }
        return false;
    }
}
