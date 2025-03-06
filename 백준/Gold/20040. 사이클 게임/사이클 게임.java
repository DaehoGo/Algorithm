import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static boolean check;
    static int[] parent;
    static int[][] point;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;

        parent = new int[n];
        point = new int[m][2];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++) {
            if(find(point[i][0]) == find(point[i][1])) {
                answer = i + 1;
                break;
            } else {
                unionFind(point[i][0], point[i][1]);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int x) {
        if(parent[x] == x) {
            return parent[x];
        }
        return parent[x] = find(parent[x]);
    }

    public static void unionFind(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX != parentY) {
            parent[parentY] = parentX;
        }
    }
}