package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution25418 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dist = new int[1000001];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();

        dist[a] = 0;
        queue.offer(a);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == k) {
                System.out.println(dist[current]);
                break;
            }

            if ((current + 1 <= k) && (dist[current + 1] == -1)) {
                queue.offer(current + 1);
                dist[current + 1] = dist[current] + 1;
            }

            if ((current * 2 <= k) && (dist[current * 2] == -1)) {
                queue.offer(current * 2);
                dist[current * 2] = dist[current] + 1;
            }
        }
    }
}
