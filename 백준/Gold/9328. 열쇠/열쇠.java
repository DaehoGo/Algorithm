import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] check;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int h, w, answer;
    static StringBuilder sb;
    static Set<Character> keySet;
    static List<Node> startPlace;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            keySet = new HashSet<>();
            startPlace = new ArrayList<>();

            for(int j = 0; j < h; j++){
                String s = br.readLine();
                for(int k = 0; k < w; k++) {
                    map[j][k] = s.charAt(k);
                }
            }
            String keys = br.readLine();
            if(!keys.equals("0")) {
                keys = keys.toUpperCase();
                for(int j = 0; j < keys.length(); j++) {
                    keySet.add(keys.charAt(j));
                }
            }

            answer = 0;
            check = new boolean[h][w];
            FindStartPlace();
            FindKey();
            sb.append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void FindKey() {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[h][w];
        queue.addAll(startPlace);

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(nextR < 0 || nextC < 0 || nextR >= h || nextC >= w || visited[nextR][nextC] || map[nextR][nextC] == '*') {
                    continue;
                }
                if(map[nextR][nextC] == '.') {
                    queue.add(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                } else if(map[nextR][nextC] == '$') {
                    answer++;
                    map[nextR][nextC] = '.';
                    queue.add(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                } else if((int) map[nextR][nextC] >= 97) {
                    keySet.add((char)(map[nextR][nextC] - 32));
                    map[nextR][nextC] = '.';
                    visited = new boolean[h][w];
                    queue.clear();
                    FindStartPlace();
                    queue.addAll(startPlace);
                } else {
                    if(keySet.contains(map[nextR][nextC])) {
                        map[nextR][nextC] = '.';
                        queue.add(new Node(nextR, nextC));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
    }
    public static void FindStartPlace() {
        for(int i = 0; i < h; i++) {
            function(i, 0);
            function(i, w - 1);
        }
        for(int i = 0; i < w; i++) {
            function(0, i);
            function(h - 1, i);
        }
    }
    public static void function(int i, int j) {
        if(check[i][j] || map[i][j] == '*') {
            return;
        }
        if(map[i][j] == '.') {
            startPlace.add(new Node(i, j));
            check[i][j] = true;
        } else if(map[i][j] == '$') {
            answer++;
            map[i][j] = '.';
            startPlace.add(new Node(i, j));
            check[i][j] = true;
        } else if((int) map[i][j] >= 97) {
            keySet.add((char)(map[i][j] - 32));
            map[i][j] = '.';;
            startPlace.add(new Node(i, j));
            check[i][j] = true;
        } else {
            if(keySet.contains(map[i][j])) {
                map[i][j] = '.';
                startPlace.add(new Node(i, j));
                check[i][j] = true;
            }
        }
    }
    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}