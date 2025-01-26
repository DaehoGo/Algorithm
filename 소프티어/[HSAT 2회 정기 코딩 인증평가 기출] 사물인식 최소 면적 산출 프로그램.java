import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class main {

    static List<Node>[] list;
    static int answer = Integer.MAX_VALUE;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[K + 1];

        for (int i = 1; i <= K; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[Integer.parseInt(st.nextToken())].add(new Node(r, c));
        }

        DFS(1, new int[K + 1],  Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
        System.out.println(answer);

    }

    static void DFS(int color, int[] idx, int minX, int maxX, int minY, int maxY) {
        if (color > K) {
            answer = Math.min(answer, (maxX - minX) * (maxY - minY));
            return;
        }

        for (int i = 0; i < list[color].size(); i++) {
            Node point = list[color].get(i);
            idx[color] = i;

            int newMinX = Math.min(minX, point.c);
            int newMaxX = Math.max(maxX, point.c);
            int newMinY = Math.min(minY, point.r);
            int newMaxY = Math.max(maxY, point.r);

            if ((newMaxX - newMinX) * (newMaxY - newMinY) >= answer)
                continue;

            DFS(color + 1, idx,  newMinX, newMaxX, newMinY, newMaxY);
        }
    }
    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
