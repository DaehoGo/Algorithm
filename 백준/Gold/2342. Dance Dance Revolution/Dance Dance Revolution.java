import java.io.*;

public class Main {
    static int[][] cost = {{0, 2, 2, 2, 2}, {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};
    static int[][][] memo;
    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[100000];
        cnt = 0;

        String[] inputs = br.readLine().split(" ");
        for (String input : inputs) {
            int k = Integer.parseInt(input);
            if (k == 0) break;
            arr[cnt++] = k;
        }

        memo = new int[5][5][cnt + 1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k <= cnt; k++) {
                    memo[i][j][k] = -1;  // 방문하지 않은 상태는 -1로 설정
                }
            }
        }

        System.out.println(dp(0, 0, 0));  // 시작 상태에서 최소 비용 찾기
    }

    static int dp(int left, int right, int idx) {
        if (idx == cnt) return 0;  // 종료 조건: 모든 명령 수행 완료
        if (memo[left][right][idx] != -1) return memo[left][right][idx];  // 이미 계산된 값 반환

        int next = arr[idx];

        // 왼발 이동
        int moveLeft = dp(next, right, idx + 1) + cost[left][next];

        // 오른발 이동
        int moveRight = dp(left, next, idx + 1) + cost[right][next];

        // 최소 비용 저장
        return memo[left][right][idx] = Math.min(moveLeft, moveRight);
    }
}
