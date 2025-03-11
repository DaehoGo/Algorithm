import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] map;
    static int N, K;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[100001];
        if(N > K) {
            sb.append(N - K).append("\n").append(N);
            for(int i = N - 1; i >= K; i--) {
                sb.append(" ").append(i);
            }
        } else {
            BFS();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS() {
        Queue<Place> queue = new LinkedList<>();
        queue.add(new Place(N, 0, new StringBuilder().append(N)));
        map[N] = true;

        while(!queue.isEmpty()) {
            Place now = queue.poll();
            if(now.p == K) {
                sb.append(now.cnt).append("\n").append(now.s);
                return;
            }

            int[] nextPositions = {now.p - 1, now.p + 1, now.p * 2};

            for (int next : nextPositions) {
                if (next < 0 || next > 100000 || map[next]) {
                    continue;
                }
                map[next] = true;
                StringBuilder newPath = new StringBuilder(now.s).append(" ").append(next);
                queue.add(new Place(next, now.cnt + 1, newPath));
            }
        }
    }

    public static class Place {
        int p;
        int cnt;
        StringBuilder s;

        public Place(int p, int cnt, StringBuilder s) {
            this.p = p;
            this.cnt = cnt;
            this.s = s;
        }
    }
}
