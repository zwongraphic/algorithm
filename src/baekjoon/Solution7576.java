package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cols = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());

        int[][] box = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < cols; col++) {
                box[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int day = 0;
        int unripe = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (box[row][col] == 1) {
                    queue.offer(new int[]{row, col, day});
                }

                if (box[row][col] == 0) {
                    unripe++;
                }
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            day = current[2];

            for (int dir = 0; dir < 4; dir++) {
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                if (nextX < 0 || nextX >= box.length || nextY < 0 || nextY >= box[0].length) {
                    continue;
                }

                if (box[nextX][nextY] == 0) {
                    box[nextX][nextY] = 1;
                    unripe--;
                    queue.offer(new int[]{nextX, nextY, day + 1});
                }
            }
        }

//        boolean possible = true;
//
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                if (box[row][col] == 0) {
//                    possible = false;
//                    break;
//                }
//            }
//        }
//
//        if (possible) {
//            System.out.println(day);
//        } else {
//            System.out.println(-1);
//        }

        if (unripe == 0) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
    }
}
