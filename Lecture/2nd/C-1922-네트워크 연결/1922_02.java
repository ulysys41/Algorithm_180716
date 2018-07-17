import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int M;

    static int[][] edges;
    static int[] disjointSets;
    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        disjointSets = new int[N+1];
        for (int i = 1; i <= N; i++) {
            disjointSets[i] = i;
        }

        edges = new int[M][3];
        int a, b, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = w;
        }

        int ans = kruskal();

        bw.write(ans+"\n");

        //long end = System.currentTimeMillis();
        //bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static int kruskal() {
        Arrays.sort(edges, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[2] < o2[2]) return -1;
                if(o1[2] > o2[2]) return 1;
                return 0;
            }
        });

        int ret = 0;
        int k = 0;
        for (int[] e : edges) {
            if(isUnion(e[0], e[1])) continue;
            union(e[0], e[1]);
            ret += e[2];

            if(++k == N-1) break;
        }

        return ret;
    }

    private static void union(int a, int b) {
        disjointSets[find(a)] = find(b);
    }

    private static boolean isUnion(int a, int b) {
        return find(a)==find(b);
    }

    private static int find(int a) {
        if(disjointSets[a] == a) return a;
        else return disjointSets[a] = find(disjointSets[a]);
    }
}