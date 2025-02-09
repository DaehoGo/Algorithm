import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        int[] dp = new int[k + 1];
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for(int coin : coins) {
            for(int i = 1; i <= k; i++) {
                if(i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        bw.write(String.valueOf(dp[k]));
        bw.flush();
        bw.close();
        br.close();

    }


}