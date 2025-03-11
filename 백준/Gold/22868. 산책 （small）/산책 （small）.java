import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer, S, E;
    static List<Integer>[] graph;
    static boolean[] visited;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        answer = 0;

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        visited = new boolean[N + 1];
        BFS(S, E);


        Collections.sort(list);
        String s = list.get(0);

        st = new StringTokenizer(s);
        visited = new boolean[N + 1];
        while (st.hasMoreTokens()) {
            visited[Integer.parseInt(st.nextToken())] = true;
        }
        visited[S] = false;
        BFS(E, S);
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(int start, int end) {
        Queue<Road> queue = new LinkedList<>();
        queue.add(new Road(start, 0, start + ""));
        visited[start] = true;
        int count = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Road now = queue.poll();
            int dis = now.distance;
            String s = now.s;

            for (int next : graph[now.v]) {
                if (visited[next] || dis >= count) {
                    continue;
                }
                if (next == end) {
                    if (dis + 1 < count) {
                        list.clear();
                    }
                    list.add(s + " " + next);
                    count = dis + 1;
                } else {
                    queue.add(new Road(next, dis + 1, s + " " + next));
                    visited[next] = true;
                }
            }
        }
        answer += count;
    }

    public static class Road {
        int v;
        int distance;
        String s;

        public Road(int v, int distance, String s) {
            this.v = v;
            this.distance = distance;
            this.s = s;
        }
    }
}
