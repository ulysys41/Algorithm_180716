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
    static int[] disjointSets;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        disjointSets = new int[N+1];
        for (int i = 1; i <= N; i++) {
            disjointSets[i] = i;
        }

        int t, a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(t == 0){
                union(a, b);
            }else{
                bw.write(isUnion(a, b) ? "YES\n" :"NO\n");
            }
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean isUnion(int a, int b) {
        return find(a)==find(b);
    }

    private static int find(int a) {
        if(disjointSets[a] == a) return a;
        else return disjointSets[a] = find(disjointSets[a]);
    }

    private static void union(int a, int b) {
        disjointSets[find(a)] = find(b);
    }
}