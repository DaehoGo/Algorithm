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

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        boolean[][] memo = new boolean[N + 1][sum + 1];

        for(int i = 0; i <= N; i++) {
            memo[i][0] = true;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = sum; j >= arr[i - 1]; j--) {
                if(memo[i - 1][j]) {
                    memo[i][j] = true;
                }
                if((j - arr[i - 1]) >= 0 && memo[i - 1][j - arr[i - 1]]) {
                    memo[i][j] = true;
                    int abs = Math.abs(Math.abs(j - arr[i - 1]) - arr[i - 1]);
                    if(abs <= sum) {
                        memo[i][abs] = true;
                    }
                }
            }
        }
        int checkNum = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < checkNum; i++) {
            boolean check = false;
            int weight = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= N; j++) {
                if(weight > sum) {
                    continue;
                }
                if(memo[j][weight]) {
                    check = true;
                    break;
                }
            }
            if(check) {
                sb.append("Y ");
                continue;
            }
            sb.append("N ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}