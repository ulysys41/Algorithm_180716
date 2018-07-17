import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int V, E;
    static List<Integer>[] vertices;
    static boolean[] isCutVertex;
    static int[] vOrder;
    static int tOrder;

    public static int min(int a, int b){
        return (a<b)?a:b;
    }
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertices = new List[V+1];
        vOrder = new int[V+1];
        isCutVertex = new boolean[V+1];

        for (int i = 1; i <= V; i++) {
            vertices[i] = new ArrayList<Integer>();
        }

        int a, b;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            vertices[a].add(b);
            vertices[b].add(a);
        }
        tOrder = 0;
        for (int i = 1; i <= V; i++) {
            if(vOrder[i] != 0) continue;
            // 방문 하지 않은 노드 dfs 탐색
            dfs(i, -1);
        }

        int cutVCnt = 0;
        String ans = "";
        for (int i = 1; i <= V; i++) {
            if(isCutVertex[i]) {
                cutVCnt++;
                ans += i + " ";
            }
        }

        bw.write(cutVCnt +"\n");
        bw.write(ans +"\n");

        br.close();
        bw.flush();
        bw.close();
    }

    private static int dfs(int v, int p) // v:방문한 노드, p:부모 노드(-1인 경우 root)
    {
        if(vOrder[v] != 0) return vOrder[v];

        vOrder[v] = ++tOrder; // 방문 순서 저장
        int minLow = vOrder[v]; // 연결된 점을 방문하며 기존 방문했던 노드 중 가장 방문순서가 빠른 순서 저장

        int child = 0; // 연결된 자식의 수
        for (int n: vertices[v]) {
            if(n == p) continue; // 부모 노드의 경우 skip

            if(vOrder[n] != 0) // 연결된 노드가 이미 방문했던 노드라면
            {
                // minLow 갱신
                minLow = min(minLow, vOrder[n]);
            } 
            else // 처음 방문하는 노드라면
            {
                child++; // 자식의 수 갱신
                int low = dfs(n, v); // 연결된 노드로 탐색 후 기존 방문했던 노드 중 가장 방문 순서가 빠른 순서 받아오기
                // 현재 노드가 root가 아니고, 자식 노드로 탐색 했을 때 현재 노드 방문순서와 같거나 빠른 노드가 없다면 현재노드는 단절점
                if(p != -1 && low >= vOrder[v]) isCutVertex[v] = true;

                // minLow 갱신
                minLow = Math.min(minLow, low);
            }
        }

        // root 노드라면 자식의 수 체크, edge size로 체크하면 안됨
        if(p == -1 && child > 1)  isCutVertex[v] = true;

        return minLow;
    }
}