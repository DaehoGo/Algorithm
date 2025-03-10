import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Principal;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N, M, R;
    static int[][] answer;
    static int[][] copy;
    static int[][] copyMap;
    static int[] now = {0, 1, 2, 3};
    static int check1 = 1;
    static int check2 = 1;
    static int rotate = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num == 1) {
                int temp = now[0];
                now[0] = now[2];
                now[2] = temp;
                temp = now[1];
                now[1] = now[3];
                now[3] = temp;
                rotate = (rotate + 2) % 4;
                if((rotate + 4) % 2 == 0) {
                    check2 *= -1;
                } else
                    check1 *= -1;

            } else if (num == 2) {
                int temp = now[0];
                now[0] = now[1];
                now[1] = temp;
                temp = now[2];
                now[2] = now[3];
                now[3] = temp;
                rotate = (rotate + 2) % 4;
                if((rotate + 4) % 2 == 0) {
                    check1 *= -1;
                } else
                    check2 *= -1;
            } else if (num == 3) {
                int temp = now[0];
                now[0] = now[2];
                now[2] = now[3];
                now[3] = now[1];
                now[1] = temp;
                rotate = (rotate + 1) % 4;
            } else if (num == 4) {
                int temp = now[0];
                now[0] = now[1];
                now[1] = now[3];
                now[3] = now[2];
                now[2] = temp;
                rotate = (rotate - 1) % 4;
            } else if (num == 5) {
                int temp = now[0];
                now[0] = now[2];
                now[2] = now[3];
                now[3] = now[1];
                now[1] = temp;
            } else {
                int temp = now[0];
                now[0] = now[1];
                now[1] = now[3];
                now[3] = now[2];
                now[2] = temp;
            }
        }
        
        calculate();

        for(int i = 0; i < answer.length; i++) {
            for(int j = 0; j < answer[0].length; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();



    }

    public static void calculate() {
        if((rotate + 4) % 2 == 1) {
            answer = new int[M][N];
        } else {
            answer = new int[N][M];
        }
        for(int i = 0; i < 4; i++) {
            copy = new int[N / 2][M / 2];
            copyMap = new int[copy.length][copy[0].length];
            for(int j = 0; j < N / 2; j++) {
                for(int k = 0; k < M / 2; k++) {
                    copy[j][k] = map[(N / 2) * (now[i] / 2) + j][(M / 2) * (now[i] % 2) + k];
                }
            }
            if(check1 == -1) {
                reverse1();
            }
            if(check2 == -1) {
                reverse2();
            }

            rotate(i, (rotate + 4) % 4);
        }
    }

    public static void reverse1() {
        int cnt = 0;
        for(int i = copy.length - 1; i >= 0; i--) {
            for(int j = 0; j < copy[0].length; j++) {
                copyMap[cnt / copyMap[0].length][cnt % copyMap[0].length] = copy[i][j];
                cnt++;
            }
        }
        for(int i = 0; i < copy.length; i++) {
            for(int j = 0; j < copy[0].length; j++) {
                copy[i][j] = copyMap[i][j];
            }
        }
    }
    public static void reverse2() {
        int cnt = 0;
        for(int i = 0; i < copy.length; i++) {
            for(int j = copy[0].length - 1; j >= 0; j--) {
                copyMap[cnt / copyMap[0].length][cnt % copyMap[0].length] = copy[i][j];
                cnt++;
            }
        }
        for(int i = 0; i < copy.length; i++) {
            for(int j = 0; j < copy[0].length; j++) {
                copy[i][j] = copyMap[i][j];
            }
        }
    }
    public static void rotate(int k, int count) {
        int cnt = 0;
        if(count == 0) {
            for(int i = 0; i < copy.length; i++) {
                for(int j = 0; j < copy[0].length; j++) {
                    answer[(copy.length) * (k / 2) + (cnt / copy[0].length)][(copy[0].length) * (k % 2) + (cnt % copy[0].length)] = copy[i][j];
                    cnt++;
                }
            }
        } else if(count == 1) {
            for(int i = 0; i < copy[0].length; i++) {
                for(int j = copy.length - 1; j >= 0; j--) {
                    answer[(copy[0].length) * (k / 2) + (cnt / copy.length)][(copy.length) * (k % 2) + (cnt % copy.length)] = copy[j][i];
                    cnt++;
                }
            }
        } else if (count == 2) {
            for(int i = copy.length - 1; i >= 0; i--) {
                for(int j = copy[0].length - 1; j >= 0; j--) {
                    answer[(copy.length) * (k / 2) + (cnt / copy[0].length)][(copy[0].length) * (k % 2) + (cnt % copy[0].length)] = copy[i][j];
                    cnt++;
                }
            }
        } else {
            for(int i = copy[0].length - 1; i >= 0; i--) {
                for(int j = 0; j < copy.length; j++) {
                    answer[(copy[0].length) * (k / 2) + (cnt / copy.length)][(copy.length) * (k % 2) + (cnt % copy.length)] = copy[j][i];
                    cnt++;
                }
            }
        }
    }
}