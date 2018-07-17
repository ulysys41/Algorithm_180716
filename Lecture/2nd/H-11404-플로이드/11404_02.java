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

    static long[][] D;
    static int N;
    static int M;
    static long MAX = 10000000001L;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        D = new long[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                D[i][j] = MAX;
            }
            D[i][i] = 0;
        }

        int s, e, w;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            D[s][e] = Math.min(D[s][e], w);
        }

        floyd_warshall();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bw.write((D[i][j] == MAX ? 0 : D[i][j])+" ");
            }
            bw.newLine();
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static void floyd_warshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(D[i][j] > D[i][k]+D[k][j]) D[i][j] = D[i][k]+D[k][j];
                }
            }
        }
    }
}