import java.io.*;
import java.util.*;

public class Main {

    static int[][] path = new int[6001][3];
    static int[] distance = new int[501];
    static final int MAX_VALUE = 500*10000+1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++) {
            // 시작점에서 각 정점으로 가는 최단 거리 저장 배열 초기화 
            distance[i]=MAX_VALUE;
        }

        for(int i=1; i<=m ; i++) {
            st = new StringTokenizer(br.readLine());
            path[i][0]=Integer.parseInt(st.nextToken());
            path[i][1]=Integer.parseInt(st.nextToken());
            path[i][2]=Integer.parseInt(st.nextToken());
        }
        // 1번 정점이 시작점, 시작점까지의 최단거리는 0
        distance[1]=0;
        for(int i=1;i<n;i++) // 정점의 수 - 1 번 수행 
        {
            for(int j=1;j<=m;j++) // 모든 간선을 사용하여 최단거리가 줄어들면 정보 갱신 
            {
                if(distance[ path[j][0]] != MAX_VALUE &&  distance[ path[j][1] ] > distance[ path[j][0]] + path[j][2]) 
                {
                    distance[ path[j][1] ] = distance[ path[j][0]] + path[j][2];
                }
            }
        }

        // 음수 cycle 확인 
        // 만약 음수 cycle이 없다면 시작점에서 모든 점으로 가는 최단거리는 갱신되어 있어야 한다.
        for(int j=1;j<=m;j++) 
        {
            // 만약 갱신되는 간선이 있다면 음수 cycle 존재
            if(distance[ path[j][0]] != MAX_VALUE && distance[ path[j][1] ] > distance[ path[j][0]] + path[j][2]) 
            {
                bw.write("-1");
                bw.flush();
                return;
            }
        }

        for(int i=2; i<= n;i++) {
            if(distance[i] != MAX_VALUE) {
                bw.write(distance[i]+"\n");
            }else {
                bw.write("-1\n");
            }
        }
        bw.flush();
    }   
}