import java.io.*;
import java.util.StringTokenizer;

public class main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N - 2; i++) {
            long count = 0;
            for(int j = i + 1; j < N; j++) {
                if(arr[i] < arr[j]) {
                    count++;
                } else {
                    answer += count;
                }
            }
        }
        System.out.println(answer);
    }

}
