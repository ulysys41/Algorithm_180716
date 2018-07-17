import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static long M;
    static long[] lods;

    static long ans;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        lods = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lods[i] = Long.parseLong(st.nextToken());
        }

        ans = binarySearch();

        bw.write(ans+"\n");

        long end = System.currentTimeMillis();
        bw.write("Time=" + (end - start) + "ms\n");
        br.close();
        bw.close();
    }

    private static long binarySearch() {
        long s = 0, e = 1000000000, m = 0;

        long ans = 0;
        while(s <= e){
            m = (s+e)/2;

            if(isPossible(m)){
                ans = m;
                s = m+1;
            }else{
                e = m-1;
            }
        }

        return ans;
    }

    private static boolean isPossible(long m){
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if(lods[i] > m) {
                sum += (lods[i] - m);
                if(M <= sum) return true; 
            }
        }

        return false;
    }
}