import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int[][] parent;
    static boolean[][] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        parent = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                parent[i][j] = i * M + j;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    DFS(i, j);
                }
            }
        }
        
        HashSet<Integer> uniqueParents = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                uniqueParents.add(find(i, j));
            }
        }

        bw.write(String.valueOf(uniqueParents.size()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int r, int c) {
        visited[r][c] = true;
        int nextR = r;
        int nextC = c;

        if (map[r][c] == 'D') {
            nextR++;
        } else if (map[r][c] == 'U') {
            nextR--;
        } else if (map[r][c] == 'L') {
            nextC--;
        } else {
            nextC++;
        }

        if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
            return;
        }
        if (!visited[nextR][nextC]) {
            DFS(nextR, nextC);
        }

        union(r, c, nextR, nextC);
    }

    public static void union(int r1, int c1, int r2, int c2) {
        int parentA = find(r1, c1);
        int parentB = find(r2, c2);
        if (parentA != parentB) {
            parent[parentB / M][parentB % M] = parentA;
        }
    }

    public static int find(int r, int c) {
        if (parent[r][c] == r * M + c) {
            return parent[r][c];
        }
        return parent[r][c] = find(parent[r][c] / M, parent[r][c] % M);
    }
}
