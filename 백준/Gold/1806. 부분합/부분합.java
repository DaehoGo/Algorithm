import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLength = 100000;
        int start = 0;
        int end = 0;
        int currentSum = 0;

        while (end < N) {
            currentSum += arr[end++];

            while (currentSum >= S) {
                minLength = Math.min(minLength, end - start);
                currentSum -= arr[start++];
            }
        }

        if (minLength == 100000) {
            minLength = 0;
        }

        bw.write(String.valueOf(minLength));
        bw.flush();
        bw.close();
    }
}
