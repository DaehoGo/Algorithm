import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        long answer = 0;
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] sumA = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            sumA[i] = sumA[i - 1] + A[i - 1];
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }


        int[] sumB = new int[m + 1];
        for(int i = 1; i <= m; i++) {
            sumB[i] = sumB[i - 1] + B[i - 1];
        }

        Map<Integer, Integer> mapA = new HashMap<>();

        for(int i = 0; i < sumA.length - 1; i++) {
            for(int j = i + 1; j < sumA.length; j++) {
                mapA.put(sumA[j] - sumA[i], mapA.getOrDefault(sumA[j] - sumA[i], 0) + 1);
            }
        }

        Map<Integer, Integer> mapB = new HashMap<>();

        for(int i = 0; i < sumB.length - 1; i++) {
            for(int j = i + 1; j < sumB.length; j++) {
                mapB.put(sumB[j] - sumB[i], mapB.getOrDefault(sumB[j] - sumB[i], 0) + 1);
            }
        }
        for(int key : mapA.keySet()) {
            answer += (long) mapA.get(key) * mapB.getOrDefault(T - key, 0);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}