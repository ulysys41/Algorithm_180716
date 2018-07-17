import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int V, E, K;
    static List<int[]>[] nodes;
    static int[] D;
    static final int INF = 300000*10+1;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        D = new int[V+1];
        nodes = new List[V+1];

        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<int[]>();
            D[i] = INF;
        }

        int s, e, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            nodes[s].add(new int[]{e, w});
        }

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            bw.write(D[i] == INF ? "INF\n" : D[i]+"\n");
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static void dijkstra(int s) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1]) return -1;
                if(o1[1] > o2[1]) return 1;
                return 0;
            }
        });

        D[s] = 0;
        pq.add(new int[] {s, D[s]});

        int[] c;
        while (!pq.isEmpty()) {
            c = pq.poll();
//          if(c[1] != D[c[0]]) continue;
//          if(c == 도착지) break;
            for (int[] e : nodes[c[0]]) {
                if(D[e[0]] > D[c[0]] + e[1]){
                    D[e[0]] = D[c[0]] + e[1];
                    pq.add(new int[] {e[0], D[e[0]]});
                }
            }
        }
    }
}