import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, nowR, nowC, answer, count;
    static char[][] map;
    static int[] dr = {1, 1, 1, 0, 0, -1, -1, -1, 0};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1, 0};
    static boolean check;
    static Queue<Node> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        queue = new LinkedList<>();

        for(int i = 0 ; i < R; i++) {
            String s = br.readLine();
            for(int j = 0 ; j < C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'I') {
                    nowR = i;
                    nowC = j;
                } else if(map[i][j] == 'R') {
                    queue.add(new Node(i, j));
                }
            }
        }

        String s = br.readLine();
        count = 1;
        for(int i = 0; i < s.length(); i++) {
            if(check) {
                break;
            }
            int k = Integer.parseInt(String.valueOf(s.charAt(i)));
            int direct = 0;
            if(k < 5) {
                direct = k;
            } else if (k > 5) {
                direct = k - 1;
            } else {
                direct = 9;
            }
            if(map[nowR + dr[direct - 1]][nowC + dc[direct - 1]] == 'R') {
                check = true;
                answer = count;
                break;
            }
            map[nowR][nowC] = '.';

            nowR += dr[direct - 1];
            nowC += dc[direct - 1];

            map[nowR][nowC] = 'I';

            play();
            count++;
        }
        if(check) {
            sb.append("kraj ").append(answer);
        } else {
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void play() {
        int size = queue.size();
        Map<Node, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < size; i++) {
            Node now = queue.poll();
            int k = direct(now);
            int direct = 0;
            if(k < 5) {
                direct = k;
            } else if (k > 5) {
                direct = k - 1;
            } else {
                direct = 9;
            }
            int r = now.r;
            int c = now.c;


            if(r + dr[direct - 1] == nowR && c + dc[direct - 1] == nowC) {
                answer = count;
                check = true;
                return;
            }
            map[r][c] = '.';

            Node next = new Node(r + dr[direct - 1], c + dc[direct - 1]);
            hashMap.put(next, hashMap.getOrDefault(next, 0) + 1);
        }

        for(Node next : hashMap.keySet()) {
            if(hashMap.get(next) > 1) {
                continue;
            }
            map[next.r][next.c] = 'R';
            queue.add(next);
        }
    }

    public static int direct(Node node) {
        int r = node.r;
        int c = node.c;

        if(nowR == r && nowC > c)
            return 6;
        if(nowR == r && nowC < c)
            return 4;
        if(nowC == c && nowR > r)
            return 2;
        if(nowC == c && nowR < r)
            return 8;
        if(nowR > r && nowC > c)
            return 3;
        if(nowR < r && nowC < c)
            return 7;
        if(nowR > r)
            return 1;
        return 9;
    }

}
