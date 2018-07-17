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

    static int[][] edges;
    static int[] D;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new int[M][3];
        D = new int[N+1];

        for (int i = 1; i <= N; i++) {
            D[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        boolean hasMinusCycle = bellmanFord();

        if(hasMinusCycle) {
            bw.write("-1\n");
        }else {
            for (int i = 2; i <= N; i++) {
                bw.write(D[i] == Integer.MAX_VALUE ? "-1\n" : D[i]+"\n");
            }
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean bellmanFord() {
        D[1] = 0;
        boolean hasNCycle = false;
        int f, t, w;
        for (int i = 1; i <= N-1; i++) {
            for (int j = 0; j < M; j++) {
                f = edges[j][0];
                t = edges[j][1];
                w = edges[j][2];
                if(D[f] != Integer.MAX_VALUE && D[t] > D[f] + w) {
                    D[t] = D[f] + w;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            f = edges[i][0];
            t = edges[i][1];
            w = edges[i][2];
            if(D[f] != Integer.MAX_VALUE && D[t] > D[f] + w){
                hasNCycle = true;
                break;
            }
        }

        return hasNCycle;
    }
}