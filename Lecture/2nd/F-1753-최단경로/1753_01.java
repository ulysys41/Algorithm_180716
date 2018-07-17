import java.io.*;
import java.util.*;

public class Main {
    static int MAX_VALUE=1000000;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());

        List<Info>[] path = new List[V+1];

        for(int i=1; i<=E ; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 간선표를 입력받아 인접 리스트 생성 
            if(path[u] == null){
                path[u]=new ArrayList();
            }
            path[u].add(new Info(v,w));
        }
        int[] distance = new int[V+1];
        for(int i=1; i<=V;i++){
            // 시작점에서 각 정점으로 가는 최단 거리 저장 배열 초기화 
            distance[i]=MAX_VALUE;
        }

        // 방문한 정점 중 최단거리가 가장 까까운 정점 정보를 위해 priority queue 생성 
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                // 거리가 가까운 정보가 가장 앞에 오도록 정렬
                if(o1.cost-o2.cost > 0){
                    return 1;
                }else if(o1.cost-o2.cost <0){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        pq.add(new Info(S,0)); // 시작정점 정보 추가 
        distance[S]=0; // 시작 정점까지의 최단거리는 0 
        while(!pq.isEmpty()) // priority queue에 데이터가 없을때 까지 탐색 
        {
            // 방문했떤 정점 중 가장 가까운 정점 정보 가져오기 
            // 최단거리가 확정된 정점 
            Info info = pq.poll();
            int node = info.node;
            int cost = info.cost;
            if(path[node]!=null)
            {
                for(Info next : path[node])
                {
                    // 최단거리가 확정된 정점으로 연결되는 새로운 정점
                    // 혹은 기존 거리보다 더 빠르게 갈 수 있는 정점을 추가 
                    if(distance[next.node] > distance[node] + next.cost)
                    {
                        distance[next.node] = distance[node] + next.cost;
                        pq.add(new Info(next.node,distance[next.node] ));
                    }
                }
            }
        }

        for(int i=1;i<=V;i++){
            if(distance[i]==MAX_VALUE){
                bw.write("INF"+"\n");
                continue;
            }
            bw.write(distance[i]+"\n");
        }
        bw.flush();
    }

    static class Info // priority queue에 넣을 상태 정보 
    {
        int node; // 방문한 노드 
        int cost; // 노드까지의 거리 
        public Info(int node, int cost) {
            super();
            this.node = node;
            this.cost = cost;
        }
    }
}