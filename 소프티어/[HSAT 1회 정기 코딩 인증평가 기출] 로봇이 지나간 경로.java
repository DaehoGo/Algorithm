import java.io.*;
import java.util.StringTokenizer;

public class main {
    static int a, b, startR, startC, startIdx, endR, endC, directionIdx;
    static String commands;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[] direction = {'^', '>', 'v', '<'};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean start = false;
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        map = new char[a][b];
        visited = new boolean[a][b];

        for(int i = 0; i < a; i++) {
            String s = br.readLine();
            for(int j = 0; j < b; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < a; i++) {
            for(int j = 0; j < b; j++) {
                if(map[i][j] == '.')
                    continue;
                int count = 0;
                for(int k = 0; k < 4; k++) {
                    int nextR = i + dr[k];
                    int nextC = j + dc[k];
                    if(nextR < 0 || nextC < 0 || nextR >= a || nextC >= b || map[nextR][nextC] == '.')
                        continue;
                    directionIdx = k;
                    count++;
                }
                if(count == 1) {
                    if(!start) {
                        startR = i;
                        startC = j;
                        startIdx = directionIdx;
                        start = true;
                    } else {
                        endR = i;
                        endC = j;
                        break;
                    }
                }
            }
        }

        directionIdx = startIdx;
        DFS(new Node(startR, startC, ""));

        System.out.println((startR + 1) + " " + (startC + 1));
        System.out.println(direction[startIdx]);
        System.out.println(commands);
    }

    static void DFS(Node node) {
        visited[node.r][node.c] = true;

        if(node.r == endR && node.c == endC) {
            commands = node.command;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nextR1 = node.r + dr[i];
            int nextC1 = node.c + dc[i];
            int nextR2 = nextR1 + dr[i];
            int nextC2 = nextC1 + dc[i];
            String nextCommands = node.command;

            if(nextR2 < 0 || nextC2 < 0 || nextR2 >= a || nextC2 >= b || visited[nextR2][nextC2] || map[nextR1][nextC1] == '.' || map[nextR2][nextC2] == '.') {
                continue;
            }

            while (directionIdx != i) {
                if ((directionIdx + 1) % 4 == i) {
                    nextCommands += "R";
                    directionIdx = (directionIdx + 1) % 4;
                } else {
                    nextCommands += "L";
                    directionIdx = (directionIdx + 3) % 4;
                }
            }

            nextCommands += "A";

            visited[nextR1][nextC1] = true;
            visited[nextR2][nextC2] = true;
            DFS(new Node(nextR2, nextC2, nextCommands));
        }
    }

    static class Node {
        int r;
        int c;
        String command;

        public Node(int r, int c, String command) {
            this.r = r;
            this.c = c;
            this.command = command;
        }
    }
}
