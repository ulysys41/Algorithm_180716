import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static ArrayList<Integer> mp[] = new ArrayList[32001];
    static int indegree[] = new int[32001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int x,y;

        for(int i=1;i<=n;i++)
        {
            mp[i] = new ArrayList<Integer>();
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            mp[x].add(y);
            indegree[y]++;
        }

        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=1;i<=n;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }

        for(int i=1;i<=n;i++)
        {
            x = q.poll();
            System.out.print(x + " ");

            for(int p : mp[x])
            {
                indegree[p]--;
                if(indegree[p]==0)
                    q.add(p);
            }
        }
    }
}