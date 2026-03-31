package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1520 {
    static int rows;
    static int cols;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        map = new int[rows][cols];
        visited = new int[rows][cols];

        PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> b.height - a.height);

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < cols; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                pq.offer(new Pos(row, col, map[row][col]));
            }
        }

        visited[0][0] = 1;
        while (!pq.isEmpty()) {
            Pos pos = pq.poll();
            int row = pos.row;
            int col = pos.col;
            int height = pos.height;

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = row + dx[dir];
                int nextCol = col + dy[dir];

                if (nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols) {
                    continue;
                }

                int nextHeight = map[nextRow][nextCol];
                if (nextHeight < height) {
                    visited[nextRow][nextCol] += visited[row][col];
                }
            }
        }

//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                System.out.print(visited[row][col] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(visited[rows - 1][cols - 1]);
    }
}

class Pos {
    int row;
    int col;
    int height;

    public Pos(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }
}