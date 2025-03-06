import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long answer = 0;

        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        long[] sumAB = new long[n * n];
        long[] sumCD = new long[n * n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[i * n + j] = A[i] + B[j];
                sumCD[i * n + j] = C[i] + D[j];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int start = 0;
        int end = n * n - 1;

        while (start < n * n && end >= 0) {
            long sum = sumAB[start] + sumCD[end];

            if (sum == 0) {
                long valueAB = sumAB[start];
                long valueCD = sumCD[end];

                int countAB = 0;
                while (start < n * n && sumAB[start] == valueAB) {
                    countAB++;
                    start++;
                }

                int countCD = 0;
                while (end >= 0 && sumCD[end] == valueCD) {
                    countCD++;
                    end--;
                }

                answer += (long) countAB * countCD;
            } else if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
