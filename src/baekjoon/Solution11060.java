package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution11060 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] miro = new int[n];
        boolean[] visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < miro.length; i++) {
            miro[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        visited[0] = true;

        int result = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int depth = current[1];

            if (position == n - 1) {
                result = depth;
                break;
            }

            for (int i = 1; i <= miro[position]; i++) {
                if (position + i >= n) {
                    continue;
                }

                if (!visited[position + i]) {
                    queue.offer(new int[]{position + i, depth + 1});
                    visited[position + i] = true;
                }
            }
        }

        if (result == -1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
