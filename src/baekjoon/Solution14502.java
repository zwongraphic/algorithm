package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution14502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());

        int[][] map = new int[rows][cols];
        ArrayList<int[]> empty = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < cols; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] == 0) {
                    empty.add(new int[]{row, col});
                }
            }
        }

        int infected = Integer.MAX_VALUE;

        for (int i = 0; i < empty.size(); i++) {
            for (int j = i + 1; j < empty.size(); j++) {
                for (int k = j + 1; k < empty.size(); k++) {
                    int[] first = empty.get(i);
                    int[] second = empty.get(j);
                    int[] third = empty.get(k);

                    map[first[0]][first[1]] = 1;
                    map[second[0]][second[1]] = 1;
                    map[third[0]][third[1]] = 1;

                    infected = Math.min(infected, virus(map));

                    map[first[0]][first[1]] = 0;
                    map[second[0]][second[1]] = 0;
                    map[third[0]][third[1]] = 0;
                }
            }
        }

        System.out.println(empty.size() - 3 - infected);
    }

    private static int virus(int[][] map) {
        int[][] copyMap = new int[map.length][map[0].length];
        for (int row = 0; row < map.length; row++) {
            copyMap[row] = map[row].clone();
        }

        int infected = 0;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == 2) {
                    infected += bfs(copyMap, row, col);
                }
            }
        }

        return infected;
    }

    private static int bfs(int[][] map, int row, int col) {
        int infected = 0;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                    continue;
                }

                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 2;
                    infected++;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return infected;
    }
}
