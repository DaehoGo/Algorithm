import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        arr[N][0] = arr[0][0];
        arr[N][1] = arr[0][1];

        double answer = 0.0;
        for (int i = 0; i < N; i++) {
            answer += (long) arr[i][0] * arr[i + 1][1];
            answer -= (long) arr[i][1] * arr[i + 1][0];
        }

        answer = Math.abs(answer) / 2.0;

        bw.write(String.format("%.1f", answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
