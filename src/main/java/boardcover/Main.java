package boardcover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String[][] MAP;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            R = Integer.parseInt(input[0]);
            C = Integer.parseInt(input[1]);

            MAP = new String[R][C];
            for (int j = 0; j < R; j++) {
                String[] w = br.readLine().split("");
                MAP[j] = w;
            }

            System.out.println(dfs());
        }
    }

    static int dfs() {
//        print();
        int r = -1, c = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (MAP[i][j].equals(".")) {
                    r = i;
                    c = j;
                    break;
                }
            }
            if (c != -1) break;
        }
        if (c == -1) return 1;

        int result = 0;
        for (Block block : Block.blocks) {
            if (block.canPutHere(r, c)) {
                block.put(Block.Action.PUT, r, c);
                result += dfs();
                block.put(Block.Action.ROLLBACK, r, c);
            }
        }
        return result;
    }

    private static void print() {
        for (String[] strings : MAP) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println();
    }

    static class Block {
        enum Action {PUT, ROLLBACK}

        public static Block[] blocks = {
                new Block(1), new Block(2), new Block(3), new Block(4)
        };

        private int direction;

        private Block(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return direction;
        }

        public boolean canPutHere(int row, int col) {
            if (isBlocked(row, col)) return false;
            if (this.direction == 1) {
                return isInsideBoard(row + 1, col) && isInsideBoard(row + 1, col + 1)
                        && !isBlocked(row + 1, col) && !isBlocked(row + 1, col + 1);
            }
            if (this.direction == 2) {
                return isInsideBoard(row, col + 1) && isInsideBoard(row + 1, col + 1)
                        &&!isBlocked(row, col + 1) && !isBlocked(row + 1, col + 1);
            }
            if (this.direction == 3) {
                return isInsideBoard(row, col + 1) && isInsideBoard(row + 1, col)
                        && !isBlocked(row, col + 1) && !isBlocked(row + 1, col);
            }
            // direction == 4
            return isInsideBoard(row + 1, col) && isInsideBoard(row + 1, col - 1)
                    && !isBlocked(row + 1, col) && !isBlocked(row + 1, col - 1);
        }

        public void put(Action action, int row, int col) {
            String mark = "#";  // default value is "#"
            if (action == Action.PUT) {
                mark = "#";
            }
            if (action == Action.ROLLBACK) {
                mark = ".";
            }

            MAP[row][col] = mark;
            if (this.direction == 1) {
                MAP[row + 1][col] = mark;
                MAP[row + 1][col + 1] = mark;
            }
            if (this.direction == 2) {
                MAP[row][col + 1] = mark;
                MAP[row + 1][col + 1] = mark;
            }
            if (this.direction == 3) {
                MAP[row][col + 1] = mark;
                MAP[row + 1][col] = mark;
            }
            if (this.direction == 4) {
                MAP[row + 1][col] = mark;
                MAP[row + 1][col - 1] = mark;
            }
        }
    }

    public static boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }

    public static boolean isBlocked(int row, int col) {
        return MAP[row][col].equals("#");
    }
}