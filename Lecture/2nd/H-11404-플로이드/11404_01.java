import java.io.*;
import java.util.*;

public class Main {

    static int[][] distance = new int[101][101];

    static int MAX_VALUE=100000001;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());//정점의 개수
        int m = Integer.parseInt(br.readLine());//버스의 개수

        // 인접배열 초기화
        for(int i=1;i<=n;i++) 
        {
            for(int j=1;j<=n;j++) 
            {
                if(i==j) 
                {
                    distance[i][j]=0;
                }
                else 
                {
                    distance[i][j]=MAX_VALUE;
                }
            }
        }

        // 간선표 정보로 인접배열 정보 업데이트 
        for(int i=1;i<=m;i++) 
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 같은 두 정점을 연결하는 간선이 여러번 입력될 수 있음에 유의
            distance[a][b]=Math.min(distance[a][b], c);
        }

        for(int k=1;k<=n;k++) 
        {
            for(int i=1;i<=n;i++) 
            {
                for(int j=1;j<=n;j++) 
                {
                    // k-1번 노드까지만 경유하여 i에서 j로 가는 거리보다
                    // k-1번 노드까지만 경유하여 i에서 k를 거쳐 j로 가는 거리가 더 짧으면 갱신 
                    if(distance[i][j] > distance[i][k]+distance[k][j]) 
                    {
                        distance[i][j] = distance[i][k]+distance[k][j];
                    }
                }
            }
        }

        for(int i=1;i<=n;i++) 
        {
            for(int j=1;j<=n;j++) 
            {
                if(distance[i][j] == MAX_VALUE) // 갱신이 안되었다면 갈수 없음
                {
                    bw.write("0 ");
                }
                else // 갱신이 되었다면 최단거리 출력
                {
                    bw.write(distance[i][j]+" ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }
}