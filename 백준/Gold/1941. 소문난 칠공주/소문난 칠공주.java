import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static boolean[][] check;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        answer = 0;

        for(int i = 0; i < 5; i++) {
            String s = br.readLine();
            for(int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for(int a = 0; a < 19; a++) {
            visited[a / 5][a % 5] = true;
            for(int b = a + 1; b < 20; b++) {
                visited[b / 5][b % 5] = true;
                for(int c = b + 1; c < 21; c++) {
                    visited[c / 5][c % 5] = true;
                    for(int d = c + 1; d < 22; d++) {
                        visited[d / 5][d % 5] = true;
                        for(int e = d + 1; e < 23; e++) {
                            visited[e / 5][e % 5] = true;
                            for(int f = e + 1; f < 24; f++) {
                                visited[f / 5][f % 5] = true;
                                for(int g = f + 1; g < 25; g++) {
                                    visited[g / 5][g % 5] = true;
                                    if (BFS(g / 5, g % 5)) {
                                        answer++;
                                    }
                                    visited[g / 5][g % 5] = false;
                                }
                                visited[f / 5][f % 5] = false;
                            }
                            visited[e / 5][e % 5] = false;
                        }
                        visited[d / 5][d % 5] = false;
                    }
                    visited[c / 5][c % 5] = false;
                }
                visited[b / 5][b % 5] = false;
            }
            visited[a / 5][a % 5] = false;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();

    }
    public static boolean BFS(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r, c));
        visited[r][c] = true;
        int count = 1;
        int s = 0;
        if(map[r][c] == 'S') {
            s++;
        }

        check = new boolean[5][5];
        check[r][c] = true;

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(nextR < 0 || nextC < 0 || nextR >= 5 || nextC >= 5 || !visited[nextR][nextC] || check[nextR][nextC]) {
                    continue;
                }
                if(map[nextR][nextC] == 'S') {
                    s++;
                }
                queue.add(new Node(nextR, nextC));
                check[nextR][nextC] = true;
                count++;
            }
        }
        return count == 7 && s >= 4;
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