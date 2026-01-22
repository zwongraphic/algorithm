package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution24444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] visited = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            graph[num1].add(num2);
            graph[num2].add(num1);
        }

        for (int i = 1; i < graph.length; i++) {
            Collections.sort(graph[i]);
        }

        Queue<Integer> queue = new ArrayDeque<>();

        int order = 0;

        queue.offer(start);
        visited[start] = ++order;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (visited[next] == 0) {
                    queue.offer(next);
                    visited[next] = ++order;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < visited.length; i++) {
            sb.append(visited[i]).append("\n");
        }

        System.out.println(sb);
    }
}
