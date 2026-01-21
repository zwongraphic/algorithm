package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution31575 {
    public static int[] dx = {1, 0};
    public static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cols = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());

        int[][] city = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < cols; col++) {
                city[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        city[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            if ((currentX == rows - 1) && (currentY == cols - 1)) {
                break;
            }

            for (int i = 0; i < 2; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= rows || nextY >= cols) {
                    continue;
                }

                if (city[nextX][nextY] != 0) {
                    queue.offer(new int[]{nextX, nextY});
                    city[nextX][nextY] = 0;
                }
            }
        }

        if (city[rows - 1][cols - 1] == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
