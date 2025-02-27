import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int[][] dp = new int[size + 2][size + 2];
        int[][] arr = new int[size][size + 1];
        for(int i = 0; i < size; i++){
            for(int j = 0; j <= i; j++){
                arr[i][j + 1] = triangle[i][j];
            }
        }
        dp[0][1] = arr[0][1];
        for(int i = 1; i < size; i++){
            for(int j = 1; j <= i + 1; j++){
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < size + 1; i++){
            answer = Math.max(answer, dp[size - 1][i]);
        }
        
        
        return answer;
    }
}