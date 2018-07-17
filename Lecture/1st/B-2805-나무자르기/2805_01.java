import java.io.*;
import java.util.*;

public class Main {

    static int n,m,tree[]= new int[1000001];

    static int max(int a, int b)
    {
        return (a>b)?a:b;
    }
    //height높이에서 나무를 잘랐을 때 자른 나무의 총 길이를 반환.
    static long calc(int height)
    {
        long rtn = 0;
        for(int i=1;i<=n;i++)
        {
            if(height<tree[i])
                rtn+=tree[i]-height;
        }
        return rtn;
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int mx=0;
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++)
        {
            tree[i] = Integer.parseInt(st.nextToken());
            mx = max(mx, tree[i]);
        }

        int l=0, r=mx;
        int mid,ans=0;
        long tmp;

        //Binary Search의 모범 코드
        //구하고자 하는 정답에 따라 ans=mid;의 위치가 else문이 아닌 if문으로 이동 가능.
        while(l<r)
        {
            mid = (l+r)/2;

            tmp = calc(mid);

            if(tmp<m)
                r=mid;
            else 
            {
                ans = mid;
                l=mid+1;
            }
        }

        System.out.println(ans);
    }
}