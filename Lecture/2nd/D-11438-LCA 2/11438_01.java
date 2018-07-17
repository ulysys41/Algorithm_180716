import java.io.*;
import java.util.*;

public class Main {
    static final int KMAX = 17; // 2^17 = 131072
    static int N,M;
    static ArrayList <Integer> adj[] = new ArrayList[100001];
    static Queue <Integer> que = new LinkedList<Integer>();
    static int depth[] = new int[100001];
    static int par[][] = new int[KMAX+1][100001]; 
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<=N;i++)
        {
            depth[i]=-1;
            adj[i] = new ArrayList<Integer>();
            for(int k=0;k<KMAX+1;k++) par[k][i]=0;
        }
        for(int i=1;i<N;i++)
        {
            int from,to;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            adj[to].add(from);
        }
        // 트리 만들기
        depth[1]=0;
        par[0][1]=1;
        que.add(1);
        while(!que.isEmpty())
        {
            int node = que.poll();
            int len = adj[node].size();
            for(int i=0;i<len;i++)
            {
                int nnode = adj[node].get(i);
                if(depth[nnode] == -1)
                {
                    par[0][nnode] = node; //nnode의 부모는 node
                    depth[nnode] = depth[node]+1; // depth 갱신
                    que.add(nnode);
                }
            }
        }

        // make sparse table
        for(int k=1;k<KMAX+1;k++)
        {
            for(int n=1;n<=N;n++)
            {
                // n의 2^k번째 조상은 n의 2^(k-1)번째 조상의 2^(k-1)번째 조상.
                // 2^k = 2^(k-1) + 2^(k-1)
                par[k][n] = par[k-1][par[k-1][n]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0,a,b;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            //a의 높이를 b와 맞춘다.
            if(depth[a] < depth[b])
            {
                for(int k=KMAX;k>=0;k--)
                {
                    while(a!=b && depth[par[k][b]] >= depth[a]) b = par[k][b];
                }
            }
            else if(depth[a] > depth[b])
            {
                for(int k=KMAX;k>=0;k--)
                {
                    while(a!=b && depth[par[k][a]] >= depth[b]) a = par[k][a];
                }
            }

            int ans;

            //유사 binary search
            for(int k=KMAX;k>=0 && a!=b ; k--)
            {
                while(a!=b && par[k][a] != par[k][b])
                {
                    a = par[k][a];
                    b = par[k][b];
                }
            }

            // 두 노드가 같다면 -> a는 b의 조상. -> LCA = a
            if(a == b) ans = a;
            else ans = par[0][a];
            bw.write(ans+"\n");
        }
        bw.flush();
    }
}