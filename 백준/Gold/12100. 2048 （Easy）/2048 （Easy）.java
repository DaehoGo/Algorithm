import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 4; i++) {
            int[][] newMap = new int[N][N];
            for (int j = 0; j < N; j++) {
                newMap[j] = Arrays.copyOf(map[j], N);
            }
            play(0, i, newMap);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void play(int depth, int direct, int[][] arr) {
        if(depth == 5) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    answer = Math.max(answer, arr[i][j]);
                }
            }
            return;
        }

        move(arr, direct);
        sum(arr, direct);
        move(arr, direct);

        for(int i = 0; i < 4; i++) {
            int[][] newArr = new int[N][N];
            for (int j = 0; j < N; j++) {
                newArr[j] = Arrays.copyOf(arr[j], N);
            }
            play(depth + 1, i, newArr);
        }
    }

    public static void move(int[][] arr, int direct) {
        if(direct == 0) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 1; j++) {
                    if (arr[i][j] != 0) {
                        continue;
                    }
                    for(int k = j + 1; k < N; k++) {
                        if(arr[i][k] != 0) {
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        } else if(direct == 1) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 1; j++) {
                    if (arr[j][i] != 0) {
                        continue;
                    }
                    for(int k = j + 1; k < N; k++) {
                        if(arr[k][i] != 0) {
                            arr[j][i] = arr[k][i];
                            arr[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        } else if(direct == 2) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j > 0; j--) {
                    if (arr[i][j] != 0) {
                        continue;
                    }
                    for(int k = j - 1; k >= 0; k--) {
                        if(arr[i][k] != 0) {
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        } else {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j > 0; j--) {
                    if (arr[j][i] != 0) {
                        continue;
                    }
                    for(int k = j - 1; k >= 0; k--) {
                        if(arr[k][i] != 0) {
                            arr[j][i] = arr[k][i];
                            arr[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void sum(int[][] arr, int direct) {
        if(direct == 0) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 2; j++) {
                    if(arr[i][j] == arr[i][j + 1]) {
                        arr[i][j] += arr[i][j + 1];
                        arr[i][j + 1] = 0;
                        j++;
                    }
                }
            }
        } else if(direct == 1) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 2; j++) {
                    if(arr[j][i] == arr[j + 1][i]) {
                        arr[j][i] += arr[j + 1][i];
                        arr[j + 1][i] = 0;
                        j++;
                    }
                }
            }
        } else if(direct == 2) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j >= 1; j--) {
                    if(arr[i][j] == arr[i][j - 1]) {
                        arr[i][j] += arr[i][j - 1];
                        arr[i][j - 1] = 0;
                        j--;
                    }
                }
            }
        } else {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j >= 1; j--) {
                    if(arr[j][i] == arr[j - 1][i]) {
                        arr[j][i] += arr[j - 1][i];
                        arr[j - 1][i] = 0;
                        j--;
                    }
                }
            }
        }
    }

}