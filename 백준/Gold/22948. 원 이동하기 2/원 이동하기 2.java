import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, start, end;
    static List<Integer>[] graph;
    static int[][] circle;
    static String answer;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        circle = new int[N + 1][3];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circle[i][0] = num;
            circle[i][1] = x;
            circle[i][2] = r;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());


        Arrays.sort(circle, (o1, o2) -> o1[2] - o2[2]);

        int startidx = 0;
        int endidx = 0;
        for(int i = 1; i <= N; i++) {
            if(circle[i][0] == start)
                startidx = i;
            if(circle[i][0] == end)
                endidx = i;
        }


        int temp = startidx;
        for(int i = startidx + 1; i <= N; i++) {
            if(Math.abs(circle[temp][1] - circle[i][1]) < circle[i][2]) {
                graph[circle[temp][0]].add(circle[i][0]);
                graph[circle[i][0]].add(circle[temp][0]);
                temp = i;
            }
            if(i == N) {
                graph[circle[temp][0]].add(0);
                graph[0].add(circle[temp][0]);
            }
        }

        temp = endidx;
        for(int i = endidx + 1; i <= N; i++) {
            if(Math.abs(circle[temp][1] - circle[i][1]) < circle[i][2]) {
                graph[circle[temp][0]].add(circle[i][0]);
                graph[circle[i][0]].add(circle[temp][0]);
                temp = i;
            }
            if(i == N) {
                graph[circle[temp][0]].add(0);
                graph[0].add(circle[temp][0]);
            }
        }



        BFS(new Move(start, 1, new StringBuilder().append(start)));
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();

    }

    public static void BFS(Move move) {
        Queue<Move> queue = new LinkedList<>();
        queue.add(move);
        visited[move.circle] = true;

        while (!queue.isEmpty()) {
            Move now = queue.poll();

            if (now.circle == end) {
                answer = now.count + "\n" + now.course.toString();
                return;
            }

            for (int next : graph[now.circle]) {
                if (visited[next]) continue;

                queue.add(new Move(next, now.count + 1,
                        new StringBuilder(now.course).append(" ").append(next)));
                visited[next] = true;
            }
        }
    }

    public static class Move {
        int circle;
        int count;
        StringBuilder course;

        public Move(int circle, int count, StringBuilder course) {
            this.circle = circle;
            this.count = count;
            this.course = course;
        }
    }

}