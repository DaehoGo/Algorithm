import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        int[][] memo = new int[M + 1][K + 1];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        for(int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        memo[0][0] = 0;

        for(int i = 0; i < N; i++) {
            if(arr[i][0] > M || arr[i][1] > K)
                continue;
            for(int j = M; j >= arr[i][0]; j--) {
                for(int k = K; k >= arr[i][1]; k--) {
                    if(memo[j - arr[i][0]][k - arr[i][1]] != -1) {
                        memo[j][k] = Math.max(memo[j - arr[i][0]][k - arr[i][1]] + 1, memo[j][k]);
                        answer = Math.max(answer, memo[j][k]);
                    }

                }
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

}