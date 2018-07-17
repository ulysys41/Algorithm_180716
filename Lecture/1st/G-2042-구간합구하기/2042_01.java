import java.io.*;
import java.util.*;
public class Main {
    static int N,M,K;
    //100만보다 큰 2의 제곱수 : 1048576 -> 최대 약 210만개의 배열이 필요, 대략 데이터의 3배정도면 충분
    static long tree[] = new long[1000000*3];

    static int init() // 트리 초기화
    {
        for(int i = 0 ; i < 3*N ; i++) tree[i] = 0;

        // 처음으로 n보다 큰 2의 제곱수 구하기
        int ret = 1;
        while(ret < N ) ret *= 2;

        ret--;// 1 base 입력처리를 위해 1을 미리 빼주기

        return ret;
    }

    // 갱신 함수
    static void update(int node, long delta)
    {
        int cur = node;
        while(cur > 0)
        {
            tree[cur] += delta; // 트리의 데이터를 갱신
            cur /= 2; // 부모 노드로 이동
        }

    }

    // 구간 쿼리 함수
    static long sum(int start, int end)
    {
        long ret = 0;
        while(start <= end)
        {
            if(start%2 == 1) ret += tree[start];
            if(end%2 == 0) ret += tree[end];
            start = (start+1)/2;
            end = (end-1)/2;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리 초기화 및 데이터의 첫 인덱스 구하기
        int Nidx = init();

        for(int i = 1 ; i <= N ; i++) 
        {
            // 인덱스 트리의 리프 노드에 데이터 입력
            tree[Nidx+i] = (long)Integer.parseInt(br.readLine());
        }

        // 트리 구성 하기
        for(int i = Nidx ; i > 0 ; i--) tree[i] = tree[i*2] + tree[i*2+1];

        for(int i = 0 ; i < M+K ; i++)
        {
            int a,b,c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(a == 1)
            {
                // 데이터 갱신 하기
                update(Nidx+b,(long)c-tree[Nidx+b]);
            }
            else
            {
                // 구간의 합 구하기
                bw.write(sum(Nidx+b,Nidx+c)+"\n");
            }
        }
        bw.flush();
    }
}