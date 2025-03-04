import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, M, answer;
    static boolean[][] map;
    static List<Shark> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = 0;
        list = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.add(new Shark(new Node(r, c), s, d, z));

        }

        for(int i = 0; i < C; i++) {
            if(list.isEmpty()) {
                break;
            }
            catchShark(i);
            moveShark(i);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void catchShark(int k) {
        sort(k);
        Shark shark = list.get(0);
        if(shark.node.c == k) {
            answer += shark.size;
            list.remove(0);
        }
    }

    public static void moveShark(int k) {
        Queue<Shark> queue = new LinkedList<>();
        map = new boolean[R][C];

        list.sort((o1, o2) -> o2.size - o1.size);

        for (Shark shark : list) {
            queue.add(shark);
        }
        list.clear();

        while (!queue.isEmpty()) {
            Shark now = queue.poll();
            Shark nextShark = move(now);

            int nextSharkR = nextShark.node.r;
            int nextSharkC = nextShark.node.c;
            if (map[nextSharkR][nextSharkC]) {
                continue;
            }
            list.add(nextShark);
            map[nextSharkR][nextSharkC] = true;
        }
    }

    public static Shark move(Shark shark) {
        int r = shark.node.r;
        int c = shark.node.c;
        int speed = shark.speed;
        int direction = shark.direction;

        int cycle = (direction <= 2) ? (R - 1) * 2 : (C - 1) * 2; // 주기 계산 최적화
        if (cycle > 0) {
            speed %= cycle;
        }


        for (int i = 0; i < speed; i++) {
            if (direction == 1 && r == 0) {
                direction = 2;
            } else if (direction == 2 && r == R - 1) {
                direction = 1;
            } else if (direction == 3 && c == C - 1) {
                direction = 4;
            }  else if (direction == 4 && c == 0) {
                direction = 3;
            }

            if (direction == 1) {
                r--;
            } else if (direction == 2) {
                r++;
            } else if (direction == 3) {
                c++;
            } else {
                c--;
            }
        }

        return new Shark(new Node(r, c), shark.speed, direction, shark.size);
    }

    public static void sort(int k) {
        list.sort((o1, o2) -> {
            int diff1 = Math.abs(o1.node.c - k);
            int diff2 = Math.abs(o2.node.c - k);
            if(diff1 != diff2) {
                return diff1 - diff2;
            }
            return o1.node.r - o2.node.r;
        });
    }
    public static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static class Shark{
        Node node;
        int speed;
        int direction;
        int size;

        public Shark(Node node, int speed, int direction, int size) {
            this.node = node;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}