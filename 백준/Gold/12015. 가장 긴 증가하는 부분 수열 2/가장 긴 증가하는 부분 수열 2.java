import java.io.*;
import java.util.*;

public class Main {
    static int cnt;
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        sequence = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sequence[0] = arr[0];
        cnt = 0;

        for (int i = 1; i < N; i++) {
            if (sequence[cnt] < arr[i]) {
                sequence[++cnt] = arr[i];
            } else {
                int idx = binarySearch(arr[i]);  // 올바른 위치 찾기
                sequence[idx] = arr[i];  // 위치에 대체
            }
        }

        int answer = cnt + 1;  // LIS 길이 계산
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int num) {
        int left = 0;
        int right = cnt;

        while (left < right) {
            int mid = (left + right) / 2;
            if (sequence[mid] >= num) {  // 현재 값보다 크거나 같으면 왼쪽에서 더 작은 값이 있는지 탐색
                right = mid;
            } else { // 현재 값보다 작으면 오른쪽에서 찾아야 함
                left = mid + 1;
            }
        }
        return right;
    }
}
