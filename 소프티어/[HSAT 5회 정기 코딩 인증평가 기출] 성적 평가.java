import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class main {
    static final int line = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] totalResult = new int[N][2];
        int[] totalrank = new int[N];

        for (int i = 0; i < line; i++) {
            int[][] result = new int[N][2];
            int[] rank = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                result[j][0] = Integer.parseInt(st.nextToken());
                totalResult[j][0] += result[j][0];
                result[j][1] = j;
                totalResult[j][1] = j;
            }

            Arrays.sort(result, (a, b) -> b[0] - a[0]);

            int lastResult = result[0][0];
            int lastRank = result[0][1];
            rank[lastRank] = 1;
            int count = 1;

            for(int j = 1; j < N; j++) {
                if(result[j][0] == lastResult) {
                    rank[result[j][1]] = rank[lastRank];
                    lastRank = result[j][1];
                    count++;
                }
                else {
                    rank[result[j][1]] = rank[lastRank] + count;
                    count = 1;
                    lastResult = result[j][0];
                    lastRank = result[j][1];
                }
            }

            for(int j = 0; j < N; j++){
                sb.append(rank[j]).append(" ");
            }
            sb.append("\n");
        }

        Arrays.sort(totalResult, (a, b) -> b[0] - a[0]);

        int lastResult = totalResult[0][0];
        int lastRank = totalResult[0][1];
        totalrank[lastRank] = 1;
        int count = 1;

        for(int j = 1; j < N; j++) {
            if(totalResult[j][0] == lastResult) {
                totalrank[totalResult[j][1]] = totalrank[lastRank];
                lastRank = totalResult[j][1];
                count++;
            }
            else {
                totalrank[totalResult[j][1]] = totalrank[lastRank] + count;
                count = 1;
                lastResult = totalResult[j][0];
                lastRank = totalResult[j][1];
            }
        }

        for(int j = 0; j < N; j++){
            sb.append(totalrank[j]).append(" ");
        }
        sb.append("\n");

        System.out.println(sb.toString());
        br.close();
    }
}
