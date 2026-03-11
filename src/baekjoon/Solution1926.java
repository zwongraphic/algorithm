package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1926 {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());

        int paper[][] = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < cols; col++) {
                paper[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 0;
        int maxWidth = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (paper[row][col] == 1) {
                    num++;
                    paper[row][col] = 0;
                    int width = bfs(paper, row, col);

                    maxWidth = Math.max(maxWidth, width);
                }
            }
        }

        System.out.println(num);
        System.out.println(maxWidth);
    }

    private static int bfs(int[][] paper, int x, int y) {
        int width = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                if (nextX < 0 || nextX >= paper.length || nextY < 0 || nextY >= paper[0].length) {
                    continue;
                }

                if (paper[nextX][nextY] == 1) {
                    paper[nextX][nextY] = 0;
                    queue.offer(new int[] {nextX, nextY});
                    width++;

                }
            }
        }

        return width;
    }
}