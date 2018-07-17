import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left, right = 1;
        int sum = 0, ans = 0;
        for (left = 1; left <= N; left++) {
            while (right <= N && sum < M) sum += arr[right++];
            if (sum == M) ans++;
            sum -= arr[left];
        }

        bw.write(ans+"\n");

        //long end = System.currentTimeMillis();
        //bw.write("Time=" + (end - start) + "ms\n");
        br.close();
        bw.close();
    }
}