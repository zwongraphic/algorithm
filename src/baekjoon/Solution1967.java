package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1967 {
    static int nodeNum;
    static ArrayList<Edge>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodeNum = Integer.parseInt(br.readLine());

        if (nodeNum == 1) {
            System.out.println(0);
            return;
        }

        tree = new ArrayList[nodeNum + 1];

        for (int i = 1; i <= nodeNum; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            tree[parent].add(new Edge(child, length));
            tree[child].add(new Edge(parent, length));
        }

        int findNode = bfs(1)[0];
        int maxLength = bfs(findNode)[1];

        System.out.println(maxLength);
    }

    static int[] bfs(int start) {
        int maxNode = 0;
        int maxLength = Integer.MIN_VALUE;

        boolean[] visited = new boolean[nodeNum + 1];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentLength = current[1];

            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxNode = currentNode;
            }

            for (int i = 0; i < tree[currentNode].size(); i++) {
                Edge next = tree[currentNode].get(i);
                int nextNode = next.node;
                int nextLength = currentLength + next.length;

                if (!visited[nextNode]) {
                    queue.offer(new int[]{nextNode, nextLength});
                    visited[nextNode] = true;
                }
            }
        }

        return new int[]{maxNode, maxLength};
    }
}

class Edge {
    int node;
    int length;

    public Edge(int node, int length) {
        this.node = node;
        this.length = length;
    }
}
