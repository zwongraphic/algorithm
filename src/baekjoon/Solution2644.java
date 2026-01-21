package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int people = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[people + 1];
        int[] visited = new int[people + 1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int relation = Integer.parseInt(br.readLine());
        for (int i = 0; i < relation; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            graph[num1].add(num2);
            graph[num2].add(num1);
        }

        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                break;
            }

            for (int next : graph[current]) {
                if (visited[next] == 0) {
                    queue.offer(next);
                    visited[next] = visited[current] + 1;
                }
            }
        }

        if (visited[end] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(visited[end]);
        }
    }
}
