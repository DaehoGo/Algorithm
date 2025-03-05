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
        int K = Integer.parseInt(st.nextToken());
        int[][] memo = new int[N + 1][K + 1];

        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if(arr[i - 1][0] <= j) {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - arr[i - 1][0]] + arr[i - 1][1]);
                } else {
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }
        bw.write(String.valueOf(memo[N][K]));
        bw.flush();
        bw.close();
        br.close();
    }
}