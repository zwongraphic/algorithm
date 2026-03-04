package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2178 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] miro = new int[n][m];

        for (int row = 0; row < n; row++) {
            String numString = br.readLine();
            for (int col = 0; col < m; col++) {
                int num = numString.charAt(col) - '0';

                miro[row][col] = num;
            }
        }

        int result = bfs(miro, 0, 0, 1);

        System.out.println(result);
    }

    public static int bfs(int[][] miro, int x, int y, int dist) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, dist});
        miro[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            int currentDist = current[2];

            if ((currentX == miro.length - 1) && (currentY == miro[0].length - 1)) {
                return currentDist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                if (nextX < 0 || nextX >= miro.length || nextY < 0 || nextY >= miro[0].length) {
                    continue;
                }

                if (miro[nextX][nextY] == 1) {
                    queue.offer(new int[]{nextX, nextY, currentDist + 1});
                    miro[nextX][nextY] = 0;
                }
            }
        }

        return 0;
    }
}
