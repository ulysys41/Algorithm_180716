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

class City{
    int no;
    int depth;
    boolean visited = false;
    List<Road> roads = new ArrayList<Road>();

    int pDist;

    public City(int no) {
        super();
        this.no = no;
    }

    @Override
    public String toString() {
        return "City [no=" + no + ", depth=" + depth + ", visited=" + visited + ", roads=" + roads + "]";
    }

}

class Road{
    City to;
    int d;
    public Road(City to, int d) {
        super();
        this.to = to;
        this.d = d;
    }
    @Override
    public String toString() {
        return "Road [to=" + to.no + ", d=" + d + "]";
    }

}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, K, L;
    static City[] cities;
    static int[][] parents;
    static int[][] pMin;
    static int[][] pMax;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        cities = new City[N+1];
        for (int i = 0; i <= N; i++) {
            cities[i] = new City(i);
        }

        int a, b, w;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            cities[a].roads.add(new Road(cities[b], w));
            cities[b].roads.add(new Road(cities[a], w));
        }

        createArrays(N);
        dfs();
        fillArrays();

        K = Integer.parseInt(br.readLine());
        int[] ans;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            ans = lca(a, b);
            bw.write(ans[0] + " " + ans[1] + "\n");
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static void createArrays(int n) {
        L = 1;
        while(n>0) {
            L++;
            n >>= 1;
        }

        parents = new int[L][N+1];
        pMin = new int[L][N+1];
        pMax = new int[L][N+1];
    }

    private static void dfs() {
        Queue<City> q = new LinkedList<City>();
        q.add(cities[1]);
        cities[1].depth = 1;

        City c;
        while (!q.isEmpty()) {
            c = q.poll();
            c.visited = true;

            for (Road r : c.roads) {
                if(r.to.visited) continue;
                r.to.depth = c.depth+1;

                parents[0][r.to.no] = c.no;
                pMin[0][r.to.no] = pMax[0][r.to.no] =r.d;

                q.add(r.to);
            }
        }
    }

    private static void fillArrays() {
        for (int i = 1; i < L; i++) {
            for (int j = 1; j <= N; j++) {
                parents[i][j] = parents[i-1][parents[i-1][j]];
                pMin[i][j] = Math.min(pMin[i-1][j], pMin[i-1][parents[i-1][j]]);
                pMax[i][j] = Math.max(pMax[i-1][j], pMax[i-1][parents[i-1][j]]);
            }
        }
    }

    private static int[] lca(int a, int b) {
        int[] ret = new int[] {Integer.MAX_VALUE, 0};

        if(cities[a].depth < cities[b].depth) {
            int tmp = a; a = b; b = tmp;
        }

        int diff = cities[a].depth - cities[b].depth;
        int k = 0;
        while(diff > 0) {
            if(diff%2 == 1) {
                ret[0] = Math.min(ret[0], pMin[k][a]);
                ret[1] = Math.max(ret[1], pMax[k][a]);
                a = parents[k][a];
            }
            k++;
            diff >>= 1;
        }

        if(a==b) return ret;

        for (int i = L-1; i > -1; i--) {
            if(parents[i][a] == parents[i][b]) continue;

            ret[0] = Math.min(ret[0], Math.min(pMin[i][a], pMin[i][b]));
            ret[1] = Math.max(ret[1], Math.max(pMax[i][a], pMax[i][b]));
            a = parents[i][a];
            b = parents[i][b];
        }

        ret[0] = Math.min(ret[0], Math.min(pMin[0][a], pMin[0][b]));
        ret[1] = Math.max(ret[1], Math.max(pMax[0][a], pMax[0][b]));

        return ret;
    }
}