import java.io.*;
import java.util.*;

public class Main {

    static int n,m,data[]= new int[10001];
    static int right,sum,cnt;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++)
        {
            data[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++)
        {
            while(right<n&&sum<m)
                sum+=data[++right];

            //data[i] ~ data[right]의 합이 m이면 cnt++
            if(sum==m)
                cnt++;

            //data[i] ~ data[right]의 합 --> data[i+1] ~ data[right]의 합
            sum-=data[i];
        }
        System.out.println(cnt);
    }
}