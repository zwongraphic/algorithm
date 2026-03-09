package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int nums = Integer.parseInt(st.nextToken());

        int[][] paper = new int[rows][cols];

        for (int num = 0; num < nums; num++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

//            for (int row = rows - 1; row >= 0; row--) {
//                for (int col = 0; col < cols; col++) {
//                    if ((row < rows - y1) && (row >= rows - y2) && (col >= x1) && (col < x2)) {
//                        paper[row][col] = 1;
//                    }
//                }
//            }

            for (int row = rows - y2; row < rows - y1; row++) {
                for (int col = x1; col < x2; col++) {
                    paper[row][col] = 1;
                }
            }
        }

        int squareNum = 0;
        ArrayList<Integer> squareWidth = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (paper[row][col] == 0) {
                    squareNum++;
                    squareWidth.add(dfs(paper, row, col));
                }
            }
        }

        Collections.sort(squareWidth);

        System.out.println(squareNum);
        for (int width : squareWidth) {
            System.out.print(width + " ");
        }
    }

    private static int dfs(int[][] paper, int x, int y) {
        int width = 0;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{x, y});
        paper[x][y] = 1;
        width++;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int currentX = current[0];
            int currentY = current[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                if (nextX < 0 || nextX >= paper.length || nextY < 0 || nextY >= paper[0].length) {
                    continue;
                }

                if (paper[nextX][nextY] == 0) {
                    stack.push(new int[]{nextX, nextY});
                    paper[nextX][nextY] = 1;
                    width++;
                }
            }
        }

        return width;
    }
}
