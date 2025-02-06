import java.io.*;
import java.util.*;

public class Main {
    static int N, M, score;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static List<Block> group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        while(true) {
            group = new ArrayList<>();
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        BFS(i, j, map[i][j]);
                    }
                }
            }
            if (group.isEmpty()) {
                break;
            }
            group.sort((a, b) -> {
                if(a.count != b.count)
                    return b.count - a.count;
                if(a.rainbow != b.rainbow)
                    return b.rainbow - a. rainbow;
                if(a.r != b.r)
                    return b.r - a.r;
                return b.c - a.c;
            });

            removeBlock(group.get(0));
            gravity();
            rotate();
            gravity();

        }
        bw.write(String.valueOf(score));
        bw.flush();
        bw.close();
        br.close();
    }


    static void BFS(int r, int c, int color) {
        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        boolean[][] rainbowVisited = new boolean[N][N];
        visited[r][c] = true;
        queue.add(new Node(r, c));
        list.add(new Node(r, c));

        int rainbowCount = 0;
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N || visited[nextR][nextC] || map[nextR][nextC] == -1) {
                    continue;
                }
                if (map[nextR][nextC] == 0 && !rainbowVisited[nextR][nextC]) {
                    queue.add(new Node(nextR, nextC));
                    rainbowVisited[nextR][nextC] = true;
                    list.add(new Node(nextR, nextC));
                    rainbowCount++;
                } else if (map[nextR][nextC] == color) {
                    queue.add(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                    list.add(new Node(nextR, nextC));
                }
            }
        }
        if (list.size() >= 2) {
            group.add(new Block(list.size(), rainbowCount, r, c, list));
        }
    }

    static void gravity() {
        for (int c = 0; c < N; c++) {
            for (int r = N - 2; r >= 0; r--) {
                if (map[r][c] >= 0) {
                    int nr = r;
                    while (nr + 1 < N && map[nr + 1][c] == -2) {
                        map[nr + 1][c] = map[nr][c];
                        map[nr][c] = -2;
                        nr++;
                    }
                }
            }
        }
    }

    static void removeBlock(Block block) {
        for (Node node : block.list) {
            map[node.r][node.c] = -2;
        }
        score += block.count * block.count;
    }

    static void rotate() {
        int[][] newMap = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                newMap[N - c - 1][r] = map[r][c];
            }
        }
        map = newMap;
    }

    static class Block {
        int count;
        int rainbow;
        int r;
        int c;
        List<Node> list;
        public Block (int count, int rainbow, int r, int c, List<Node> list) {
            this.count = count;
            this.rainbow = rainbow;
            this.r = r;
            this.c = c;
            this.list = list;
        }
    }
    static class Node {
        int r;
        int c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c ;
        }
    }

}