package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1987 {
    static int rows;
    static int cols;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int maxDist = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        board = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            for (int col = 0; col < cols; col++) {
                board[row][col] = str.charAt(col);
            }
        }

        visited[board[0][0] -'A'] = true;
        dfs(0, 0, 1);

        System.out.println(maxDist);
    }

    static void dfs(int x, int y, int dist) {
        maxDist = Math.max(maxDist, dist);

        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                continue;
            }

            int nextAlpha = board[nextX][nextY] - 'A';
            if (!visited[nextAlpha]) {
                visited[nextAlpha] = true;
                dfs(nextX, nextY, dist + 1);
                visited[nextAlpha] = false;
            }
        }
    }
}
