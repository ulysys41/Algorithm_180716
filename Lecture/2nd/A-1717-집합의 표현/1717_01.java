import java.io.*;
import java.util.*;

public class Main {

    static int n,m;
    static int uni[] = new int[1000001];

    static int getParent(int x){
        if(x==uni[x])return x;
        return uni[x] = getParent(uni[x]);
    }
    static void join(int x,int y){
        x = getParent(x);
        y = getParent(y);
        uni[x] = uni[y];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //union배열 초기화
        for(int i=0;i<=n;i++)
            uni[i]=i;

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(query == 0){
                join(a,b);
            }
            else{
                //uni[a]==uni[b]가 아니다. -- why?
                if(getParent(a)==getParent(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }
}