import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int[][] map;
    static int[] paper = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[10][10];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        if(answer == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r, int c, int count) {
        if (r >= 10) {
            answer = Math.min(answer, count);
            return;
        }

        if (c >= 10) {
            dfs(r + 1, 0, count);
            return;
        }
        // row column을 따로 설정해서 값을 벗어나는 경우에 다시 재귀에 넣어줄 수 있는 방법
        if (map[r][c] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (paper[size] > 0 && check(r, c, size)) {
                    func(r, c, size, 0);
                    paper[size]--;

                    dfs(r, c + 1, count + 1);

                    func(r, c, size, 1);
                    paper[size]++;
                }
            }
        } else {
            dfs(r, c + 1, count);
        }
    }

    static boolean check(int r, int c, int size) {
        if (r + size > 10 || c + size > 10)
            return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    static void func(int r, int c, int size, int num) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = num;
            }
        }
    }
}
