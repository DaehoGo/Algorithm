import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, dis, sum, answer;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map, copyMap;
    static boolean[][] visited;
    static List<List<Integer>> combination;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dis = (int) Math.pow(2, N);

        map = new int[dis][dis];
        copyMap = new int[dis][dis];
        visited = new boolean[dis][dis];

        sum = 0;
        answer = 0;

        for (int i = 0; i < dis; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < dis; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < Q; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        for (int L : list) {
            int size = (int) Math.pow(2, L);
            int count = (dis / size);
            copy();

            for(int i = 0; i < count; i++) {
                for(int j = 0; j < count; j++) {
                    rotate(map, i * size, j * size, size);
                }
            }
            List<Node> iceList = new ArrayList<>();

            for (int i = 0; i < dis; i++) {
                for (int j = 0; j < dis; j++) {
                    if(map[i][j] == 0)
                        continue;
                    if (!checkIce(i, j)) {
                        iceList.add(new Node(i, j));
                    }
                }
            }

            for(Node ice : iceList) {
                map[ice.r][ice.c]--;
                sum--;
            }
        }

        for(int i = 0; i < dis; i++) {
            for(int j = 0; j < dis; j++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    BFS(i, j);
                }
            }
        }
        bw.write(sum + "\n" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r, c));
        visited[r][c] = true;
        int loaf = 1;

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if(isIn(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != 0) {
                    visited[nextR][nextC] = true;
                    queue.add(new Node(nextR, nextC));
                    loaf++;
                }
            }
        }
        answer = Math.max(answer, loaf);
    }
    static boolean checkIce(int r, int c) {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];
            if(isIn(nextR, nextC) && map[nextR][nextC] > 0) {
                count++;
            }
        }
        return count >= 3;
    }
    static void copy() {
        for (int i = 0; i < dis; i++) {
            copyMap[i] = Arrays.copyOf(map[i], dis);
        }
    }
    static void rotate(int[][] map, int r, int c, int size) {
        int rPlus = 0;
        for(int i = c;  i < c + size; i++) {
            int cPlus = 0;
            for(int j = r + size - 1; j >= r; j--) {
                map[r + rPlus][c + cPlus] = copyMap[j][i];
                cPlus++;
            }
            rPlus++;
        }

    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < dis && c < dis;
    }

    static class Node {
        int r;
        int c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}