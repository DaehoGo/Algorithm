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

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] cost = new int[N + 1];
        int[] weight = new int[N + 1];

        int maxSum = 0;
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxSum += cost[i];
        }

        int[] memo = new int[maxSum + 1];

        for(int i = 0; i <= N; i++) {
            for(int j = maxSum; j >= cost[i]; j--) {
                memo[j] = Math.max(memo[j], memo[j - cost[i]] + weight[i]);
            }
        }


        for(int i = maxSum; i >= 0; i--) {
            if(memo[i] < M) {
                break;
            }
            answer = i;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}