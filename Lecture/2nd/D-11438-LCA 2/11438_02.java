import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static int[][] parents;
    static int[] depth;
    static int K = 0;

    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nodes = new List[N+1];
        visited = new boolean[N+1];
        depth = new int[N+1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        int n = N;
        while(n > 0) {
            n >>= 1;
            K++;
        }

        parents = new int[K][N+1];

        int a,b;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

        parents[0][1] = 0;
//      dfs(1, 0);
        bfs();

        for (int k = 1; k < K; k++) {
            for (int v = 1; v <= N; v++) {
                parents[k][v] = parents[k-1][parents[k-1][v]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b)+"\n");
        }

        //long end = System.currentTimeMillis();
        //bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b; b = tmp;
        }

        int d = depth[a]-depth[b];
        int k = 0;
        while(d > 0){
            if(d%2 == 1) a = parents[k][a];
            d >>= 1;
            k++;
        }

        if(a==b) return a;

        for (k = K-1; k > -1; k--) {
            if(parents[k][a] != parents[k][b]){
                a = parents[k][a];
                b = parents[k][b];
            }
        }

        return parents[0][a];
    }

    private static void dfs(int v, int p) {
        parents[0][v] = p;
        depth[v] = depth[p]+1;

        for (int n : nodes[v]) {
            if(n==p) continue;
            dfs(n, v);
        }
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        int v;
        while(!q.isEmpty()) {
            v = q.poll();
            for(int n: nodes[v]) {
                if(n==parents[0][v]) continue;
                depth[n] = depth[v]+1;
                parents[0][n] = v;
                q.add(n);
            }
        }
    }
}

//http://blog.naver.com/PostView.nhn?blogId=kks227&logNo=220820773477&redirect=Dlog&widgetTypeCall=true

//https://www.slideshare.net/HYUNJEONGKIM11/3b-lca

//vs code git push test