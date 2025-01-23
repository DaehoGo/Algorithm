import java.io.*;
import java.util.Arrays;

public class main {

    static String message, key, answer;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        message = br.readLine();
        key = br.readLine();
        map = new char[5][5];
        answer = "";
        boolean[] check = new boolean[26];

        int count = 0;

        for (char c : key.toCharArray()) {
            int k = (int) c - 65;
            if(!check[k]) {
                check[k] = true;
                map[count / 5][count % 5] = c;
                count++;
            }
        }

        for(int i = 0; i < 26; i++) {
            if(i == 9)
                continue;
            if(!check[i]) {
                check[i] = true;
                map[count / 5][count % 5] = (char) (i + 65);
                count++;
            }
        }

        for(int i = 0; i < message.length(); i++) {
            if(i == message.length() - 1) {
                encryption(message.charAt(i), 'X');
            } else if(message.charAt(i) == message.charAt(i + 1)) {
                if(message.charAt(i) == 'X') {
                    encryption(message.charAt(i), 'Q');
                } else {
                    encryption(message.charAt(i), 'X');
                }
            } else {
                encryption(message.charAt(i), message.charAt(i + 1));
                i++;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int[] search(char a) {
        int[] rc = new int[2];
        for(int j = 0; j < 5; j++) {
            for(int k = 0 ; k < 5; k++) {
                if(map[j][k] == a) {
                    rc[0] = j;
                    rc[1] = k;
                }
            }
        }
        return rc;
    }

    static void encryption(char a, char b) {
        int[] rc1 = search(a);
        int[] rc2 = search(b);

        if(rc1[0] == rc2[0]) {
            answer += map[rc1[0]][(rc1[1] + 1) % 5];
            answer += map[rc2[0]][(rc2[1] + 1) % 5];
        } else if(rc1[1] == rc2[1]) {
            answer += map[(rc1[0] + 1) % 5][rc1[1]];
            answer += map[(rc2[0] + 1) % 5][rc2[1]];
        } else {
            answer += map[rc1[0]][rc2[1]];
            answer += map[rc2[0]][rc1[1]];
        }
    }

}
