import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[] horseR = {2, 2, 1, 1, -1, -1, -2, -2};
    static int[] horseC = {1, -1, 2, -2, 2, -2, 1, -1};
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = BFS();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();

    }

    static int BFS() {
        if (W == 1 && H == 1) {
            return 0;
        }
        Queue<move> queue = new LinkedList<>();
        queue.add(new move(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            move now = queue.poll();
            int nowUse = now.use;
            int nowMove = now.move;
            if (now.r == H - 1 && now.c == W - 1) {
                return nowMove;
            }
            if (nowUse < K) {
                for (int i = 0; i < 8; i++) {
                    int nextR = now.r + horseR[i];
                    int nextC = now.c + horseC[i];
                    if (isIn(nextR, nextC) && !visited[nextR][nextC][nowUse + 1]) {
                        visited[nextR][nextC][nowUse + 1] = true;
                        queue.add(new move(nextR, nextC, nowUse + 1, nowMove + 1));
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if (isIn(nextR, nextC) && !visited[nextR][nextC][nowUse]) {
                    visited[nextR][nextC][nowUse] = true;
                    queue.add(new move(nextR, nextC, nowUse, nowMove + 1));
                }
            }
        }
        return -1;
    }

    static class move {
        int r;
        int c;
        int use;
        int move;
        public move (int r, int c, int use, int move) {
            this.r = r;
            this.c = c;
            this.use = use;
            this.move = move;
        }
    }

    static boolean isIn (int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W && map[r][c] == 0;
    }
}