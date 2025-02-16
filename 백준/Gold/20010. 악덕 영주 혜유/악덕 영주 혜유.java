import java.io.*;
import java.util.*;

public class Main {
    static int N, K, maxDistance, farthestNode;
    static List<Edge>[] graph;
    static List<Edge>[] mstGraph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        mstGraph = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            mstGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        int totalMSTCost = prim(0);

        findTreeDiameter();

        bw.write(totalMSTCost + "\n" + maxDistance);
        bw.flush();
        bw.close();
    }

    static int prim(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        pq.add(new Edge(start, 0));
        int totalCost = 0;
        int count = 0;

        while (!pq.isEmpty() && count < N) {
            Edge now = pq.poll();
            int node = now.to;

            if (visited[node]) continue;
            visited[node] = true;
            totalCost += now.cost;
            count++;

            if (now.cost != 0) {
                mstGraph[now.from].add(new Edge(now.to, now.cost));
                mstGraph[now.to].add(new Edge(now.from, now.cost));
            }

            for (Edge next : graph[node]) {
                if (!visited[next.to]) {
                    pq.add(new Edge(next.to, next.cost, node));
                }
            }
        }
        return totalCost;
    }

    // MST에서 트리의 지름을 찾는 함수
    static void findTreeDiameter() {
        // 1. BFS로 가장 먼 노드 A 찾기
        bfs(0);  // 첫 번째 BFS
        int startNode = farthestNode;  // 가장 먼 노드 A

        // 2. A에서 다시 BFS 수행 → 트리의 지름 찾기
        bfs(startNode);  // 두 번째 BFS
    }

    static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        Arrays.fill(visited, false);  // visited 배열 초기화
        queue.add(new int[]{start, 0});
        visited[start] = true;

        maxDistance = 0;
        farthestNode = start;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int node = now[0];
            int distance = now[1];

            for (Edge next : mstGraph[node]) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    int newDist = distance + next.cost;
                    queue.add(new int[]{next.to, newDist});

                    if (newDist > maxDistance) {
                        maxDistance = newDist;
                        farthestNode = next.to;
                    }
                }
            }
        }
    }

    static class Edge {
        int from, to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public Edge(int to, int cost, int from) {
            this.to = to;
            this.cost = cost;
            this.from = from;
        }
    }
}
