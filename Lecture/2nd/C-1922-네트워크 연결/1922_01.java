import java.io.*;
import java.util.*;

public class Main {

    static class Edge{
        int dist;
        int x,y;
        Edge(int dist,int x,int y){
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    static int n,m;
    static int uni[] = new int[100001];
    static Edge data[] = new Edge[100001];

    static int getParent(int x)
    {
        if(x==uni[x])return x;
        return uni[x] = getParent(uni[x]);
    }
    static void join(int x, int y)
    {
        x = getParent(x);
        y = getParent(y);
        uni[x] = uni[y];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            data[i] = new Edge(dist,x,y);
        }

        for(int i=1;i<=n;i++)uni[i]=i;

        Arrays.sort(data,1,m+1,new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                if(a.dist==b.dist)return 0;
                if(a.dist<b.dist)return -1;
                return 1;
            }
        });

        int ans = 0;

        for(int i=1;i<=m;i++)
        {
            if(getParent(data[i].x) != getParent(data[i].y))
            {
                join(data[i].x,data[i].y);
                ans +=data[i].dist;
            }
        }

        System.out.println(ans);
    }
}