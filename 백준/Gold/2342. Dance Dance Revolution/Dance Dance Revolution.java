import java.io.*;

public class Main {
    static int[][] cost = {{0, 2, 2, 2, 2}, {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};
    static int[][][] memo;
    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        arr = new int[100000];
        cnt = 0;

        String[] inputs = br.readLine().split(" ");
        for (String input : inputs) {
            int k = Integer.parseInt(input);
            if (k == 0) break;
            arr[cnt++] = k;
        }

        memo = new int[5][5][cnt + 1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k <= cnt; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }

        bw.write(String.valueOf(dp(0, 0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dp(int left, int right, int idx) {
        if(idx == cnt) {
            return 0;
        }
        if(memo[left][right][idx] != -1) {
            return memo[left][right][idx];
        }

        int next = arr[idx];

        int moveLeft = dp(next, right, idx + 1) + cost[left][next];
        int moveRight = dp(left, next, idx + 1) + cost[right][next];

        return memo[left][right][idx] = Math.min(moveLeft, moveRight);
    }

}
