import java.io.*;
import java.util.StringTokenizer;

public class main {
    static int n, m, answer;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;

    static int[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[n][n];
        visited = new boolean[n][n];
        list = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                list[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        visited[list[0][0]][list[0][1]] = true;
        DFS(list[0][0], list[0][1], 1);
        System.out.println(answer);
        br.close();
    }

    static void DFS(int r, int c, int count) {
        if(r == list[m -1][0] && c == list[m - 1][1] && count == m) {
            answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if(nextR < 0 || nextC < 0 || nextR >= n || nextC >= n || map[nextR][nextC] == 1 || visited[nextR][nextC])
                continue;

            visited[nextR][nextC] = true;
            if(nextR == list[count][0] && nextC == list[count][1]) {
                DFS(nextR, nextC, count + 1);
            } else {
                DFS(nextR, nextC, count);
            }
            visited[nextR][nextC] = false;

        }
    }

}
