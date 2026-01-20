package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeNum = Integer.parseInt(br.readLine());
        int edgeNum = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] network = new ArrayList[nodeNum + 1];
        boolean visited[] = new boolean[nodeNum + 1];

        for (int i = 0; i < network.length; i++) {
            network[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            network[num1].add(num2);
            network[num2].add(num1);
        }

        int infected = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        visited[1] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int next : network[current]) {
                if (!visited[next]) {
                    stack.push(next);
                    visited[next] = true;
                    infected++;
                }
            }
        }

        System.out.println(infected);
    }
}
