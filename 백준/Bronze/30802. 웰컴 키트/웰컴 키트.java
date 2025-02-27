import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int tShirt = 0;
        int pens = 0;
        int pen = 0;

        int[] arr = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());


        for(int i = 0; i < 6; i++) {
            if(arr[i] % T != 0) {
                tShirt += 1;
            }
            tShirt += arr[i] / T;

        }
        pens = N / P;
        pen = N % P;

        bw.write(tShirt + "\n" + pens + " " + pen);
        bw.flush();
        bw.close();
        br.close();

    }
}