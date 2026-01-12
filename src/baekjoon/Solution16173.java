package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution16173 {
    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isPossible = false;

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            if (map[currentX][currentY] == -1) {
                isPossible = true;
                break;
            }

            for (int i = 0; i < 2; i++) {
                int nextX = currentX + dx[i] * map[currentX][currentY];
                int nextY = currentY + dy[i] * map[currentX][currentY];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                    continue;
                }

                if (map[nextX][nextY] != 0) {
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        if (isPossible) {
            System.out.println("HaruHaru");
        } else {
            System.out.println("Hing");
        }
    }
}
