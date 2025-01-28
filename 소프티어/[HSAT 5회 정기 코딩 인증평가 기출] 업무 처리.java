import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int day = 1;
        int answer = 0;


        Queue<Integer>[][][] queue1 = new Queue[H][(int) Math.pow(2, H - 1)][2];
        for (int i = 0; i < H; i++) {
            for(int j = 0; j < Math.pow(2, i); j++) {
                queue1[i][j][0] = new LinkedList<>();
                queue1[i][j][1] = new LinkedList<>();
            }
        }

        Queue<Integer>[][] queue2 = new Queue[1][(int) Math.pow(2, H)];

        for (int i = 0; i < Math.pow(2, H); i++) {
            queue2[0][i] = new LinkedList<>();
        }

        for(int i = 0; i < Math.pow(2, H); i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                queue2[0][i].add(Integer.parseInt(st.nextToken()));
            }
        }

        while(day <= R) {
            for (int i = 0; i < H; i++) {
                for(int j = 0; j < Math.pow(2, i); j++) {
                    if (day % 2 == 1 && !queue1[i][j][0].isEmpty()) {
                        if(i == 0) {
                            answer += queue1[i][j][0].poll();
                        } else {
                            queue1[i - 1][j / 2][j % 2].add(queue1[i][j][0].poll());
                        }
                    } else if (day % 2 == 0 && !queue1[i][j][1].isEmpty()) {
                        if(i == 0) {
                            answer += queue1[i][j][1].poll();
                        } else {
                            queue1[i - 1][j / 2][j % 2].add(queue1[i][j][1].poll());
                        }
                    }
                }
            }
            for (int i = 0; i < Math.pow(2, H); i++) {
                if (queue2[0][i].isEmpty())
                    continue;
                queue1[H - 1][i / 2][i % 2].add(queue2[0][i].poll());
            }
            day++;
        }

        System.out.println(answer);
    }

}
