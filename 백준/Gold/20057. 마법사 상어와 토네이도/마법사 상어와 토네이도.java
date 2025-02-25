import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, nowR, nowC, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static int[] drc = {1, -1, 2, 1, -1, -2, 1, -1};
    static double[] percent = {0.01, 0.01, 0.02, 0.07, 0.07, 0.02, 0.1, 0.1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        answer = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nowR = N / 2;
        nowC = N / 2;
        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < i; k++) {
                    tornado(nowR, nowC, cnt);
                }
                cnt = (cnt + 1) % 4;
            }
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void tornado(int r, int c, int num) {
        int R = r;
        int C = c;
        nowR += dr[num];
        nowC += dc[num];
        if(!isIn(nowR, nowC)) {
            return;
        }
        int sand = map[nowR][nowC];
        if(num % 2 == 1) {
            for(int i = 0; i <= 1; i++) {
                if(isIn(R , C + drc[i])) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R][C + drc[i]] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            for(int i = 2; i <= 5; i++) {
                if(isIn(R , C + drc[i])) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R][C + drc[i]] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            for(int i = 6; i <= 7; i++) {
                if(isIn(R , C + drc[i])) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R][C + drc[i]] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            if(isIn(R, C)) {
                int amount = (int) (map[nowR][nowC] * 0.05);
                sand -= amount;
                map[R][C] += amount;
            } else {
                int amount = (int) (map[nowR][nowC] * 0.05);
                sand -= amount;
                answer += amount;
            }
            R -= dr[num];
            C -= dc[num];
            if(isIn(R, C)) {
                map[R][C] += sand;
            } else {
                answer += sand;
            }
            map[nowR][nowC] = 0;
        } else {
            for(int i = 0; i <= 1; i++) {
                if(isIn(R + drc[i] , C)) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R  + drc[i]][C] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            for(int i = 2; i <= 5; i++) {
                if(isIn(R + drc[i] , C)) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R  + drc[i]][C] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            for(int i = 6; i <= 7; i++) {
                if(isIn(R + drc[i] , C)) {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    map[R  + drc[i]][C] += amount;
                } else {
                    int amount = (int) (map[nowR][nowC] * percent[i]);
                    sand -= amount;
                    answer += amount;
                }
            }
            R += dr[num];
            C += dc[num];
            if(isIn(R, C)) {
                int amount = (int) (map[nowR][nowC] * 0.05);
                sand -= amount;
                map[R][C] += amount;
            } else {
                int amount = (int) (map[nowR][nowC] * 0.05);
                sand -= amount;
                answer += amount;
            }
            R -= dr[num];
            C -= dc[num];
            if(isIn(R, C)) {
                map[R][C] += sand;
            } else {
                answer += sand;
            }
            map[nowR][nowC] = 0;
        }
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}