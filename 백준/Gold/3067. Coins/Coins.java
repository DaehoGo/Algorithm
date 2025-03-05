import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[N];
            for(int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[][] memo = new int[N + 1][M + 1];
            for(int j = 0; j <= N; j++) {
                memo[j][0] = 1;
            }
            for(int j = 1; j <= N; j++) {
                for(int k = 1; k <= M; k++){
                    if(coins[j - 1] <= k) {
                        memo[j][k] = memo[j][k - coins[j - 1]] + memo[j - 1][k];
                    } else {
                        memo[j][k] = memo[j - 1][k];
                    }
                }
            }
            sb.append(memo[N][M]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}