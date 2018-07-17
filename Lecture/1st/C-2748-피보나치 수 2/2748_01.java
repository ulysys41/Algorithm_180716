import java.io.*;
import java.util.*;

public class Main {

    static long d[] = new long[100];
    static int n;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        d[0] = 0;
        d[1] = 1;
        for(int i=2;i<=n;i++)
            d[i] = d[i-1] + d[i-2];

        System.out.println(d[n]);
    }
}