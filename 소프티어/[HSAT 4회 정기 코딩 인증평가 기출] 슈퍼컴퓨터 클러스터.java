import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long answer = 0;
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());


        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long left = arr[0];
        long right = (long) Math.sqrt(B) + arr[0];

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for(int i = 0; i < N; i++) {
                if(sum > B || arr[i] >= mid) {
                    break;
                }
                sum += (arr[i] - mid) * (arr[i] - mid);
            }
            if(sum <= B) {
                answer = mid;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }


        System.out.println(answer);


    }

}
