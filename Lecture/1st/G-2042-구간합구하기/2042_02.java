import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class IndexedTree{
    int bIdx, size;
    long tree[];

    public IndexedTree(int size) {
        super();
        this.size = size;

        int len = 1;
        while (len < this.size) {
            len *= 2;
        }
        len *= 2;
        tree = new long[len];

        this.bIdx = len/2-1;
    }

    public void setData(long[] data){
        for (int i = 1; i <= size; i++) {
            tree[bIdx+i] = data[i];
        }

        for (int i = bIdx; i >=1; i--) {
            tree[i] = tree[2*i] + tree[2*i+1];
        }
    }

    public void update(int idx, long value){
        int tIdx = bIdx + idx;
        tree[tIdx] = value;

        while((tIdx=tIdx/2) > 0){
            tree[tIdx] = tree[tIdx*2]+tree[tIdx*2+1];
        }
    }

    public long query(int s, int e){
        long sum = 0;
        s += bIdx; e += bIdx;

        while (s <= e) {
            if(s%2 == 1) sum += tree[s++];
            if(e%2 == 0) sum += tree[e--];
            s /= 2;
            e /= 2;
        }

        return sum;
    }

    public String toString() {
        return "IndexTree [size=" + size + ", tree=" + Arrays.toString(tree) + "]";
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static long[] arr;
    static int N;
    static int M;
    static int K;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        IndexedTree it = new IndexedTree(N);
        it.setData(arr);

        int a, b, c;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == 1){
                it.update(b, c);
            }else{
                bw.write(it.query(b, c)+"\n");
            }
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }
}