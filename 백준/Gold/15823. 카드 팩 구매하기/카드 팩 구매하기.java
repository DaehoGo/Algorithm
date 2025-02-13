import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N / M + 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(card(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();

    }

    static boolean card(int max) {
        int count = 0;
        int start = 0;
        int i = 0;
        Set<Integer> set = new HashSet<>();

        while(i < N) {
            if(!set.add(arr[i]))  {
                set.remove(arr[start]);
                start++;
            } else {
                i++;
            }
            if(set.size() == max) {
                count++;
                start = i;
                set.clear();
            }
            if(count == M) {
                return true;
            }
        }

        return false;
    }



}