import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        int cnt = 1;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    cnt++;
                    BFS1(i, j, cnt);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    visited = new boolean[N][N];
                    BFS(i, j);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS1(int r, int c, int islandNum) {
        Queue<Node> queue = new LinkedList<>();
        visited[r][c] = true;
        map[r][c] = islandNum;
        queue.add(new Node(r, c));
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0 ; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(isIn(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    map[nextR][nextC] = islandNum;
                    queue.add(new Node(nextR, nextC));
                }
            }
        }
    }

    public static void BFS(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r, c, 0));
        visited[r][c] = true;
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if (!isIn(nextR, nextC) || map[nextR][nextC] == map[r][c]
                        || visited[nextR][nextC]) {
                    continue;
                }
                if (map[nextR][nextC] != 0 && map[nextR][nextC] != map[r][c]) {
                    answer = Math.min(answer, now.size);
                    return;
                }
                queue.add(new Node(nextR, nextC, now.size + 1));
                visited[nextR][nextC] = true;
            }
        }
    }

    public static class Node {
        int r;
        int c;
        int size;

        public Node(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}