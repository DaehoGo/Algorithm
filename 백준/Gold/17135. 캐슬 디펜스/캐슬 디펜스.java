import java.io.*;
import java.util.*;

public class Main {
    static final int archer = 3;
    static int N, M, D, answer, count;
    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};
    static int[][] map;
    static List<List<Integer>> combination;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        answer = 0;

        map = new int[N + 1][M];
        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination = new ArrayList<>();
        int[] nums = new int[M];

        for(int i = 0; i < M; i++){
            nums[i] = i;
        }

        combination(nums, new int[archer], 0, 0);

        for (List<Integer> list : combination) {
            count = 0;
            int[][] newMap = new int[N + 1][M];
            for (int i = 0; i <= N; i++) {
                newMap[i] = Arrays.copyOf(map[i], M);
            }

            for(int i = 0; i < N; i++) {
                BFS(list, newMap);
                move(newMap, N, M);
            }
            answer = Math.max(answer, count);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS(List<Integer> comb, int[][] map) {
        Queue<Node> queue = new LinkedList<>();
        List<Node> list = new ArrayList<>();
        boolean[] check = new boolean[M];
        for(int c : comb) {
            queue.add(new Node(N + 1, c, 0, c));
        }
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0; i < 3; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(check[now.archer] || nextR < 0 || nextC < 0 || nextR >= N + 1 || nextC >= M) {
                    continue;
                }
                if (map[nextR][nextC] == 0) {
                    if(now.distance == D - 1) {
                        continue;
                    }
                    queue.add(new Node(nextR, nextC, now.distance + 1, now.archer));
                } else {
                    list.add(new Node(nextR, nextC, 0, 0));
                    check[now.archer] = true;
                    break;
                }
            }
        }

        for(Node now : list) {
            if(map[now.r][now.c] == 1) {
                map[now.r][now.c] = 0;
                count++;
            }
        }

    }
    static void move(int[][] map, int N, int M) {
        for(int i = N; i > 0; i--) {
            for(int j = 0; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
    }

    static void combination(int[] arr, int[] out, int start, int depth){
        if (depth == archer) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < archer; i++) {
                list.add(out[i]);
            }
            combination.add(list);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            out[depth] = arr[i];
            combination(arr, out, i + 1, depth + 1);
        }
    }

    static class Node {
        int r;
        int c;
        int distance;
        int archer;
        public Node (int r, int c, int distance, int archer) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.archer = archer;
        }
    }
}