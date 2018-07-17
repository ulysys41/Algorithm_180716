import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int queue[] = new int[10001],front,back,size;

    static String cmd;
    static int arg;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        //queue초기화
        size=0;front=0;back=-1;

        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            switch(cmd)
            {
                case "push":
                    //back위치 증가 및 back위치에 데이터 추가.
                    queue[++back]=Integer.parseInt(st.nextToken());
                    size++;
                    break;

                case "pop":
                    if(size==0){
                        System.out.println(-1);
                        break;
                    }
                    //front번째 원소 출력 및 front위치 증가.
                    System.out.println(queue[front++]);
                    size--;
                    break;

                case "size":
                    System.out.println(size);
                    break;

                case "empty":
                    System.out.println(size==0?1:0);
                    break;

                case "front":
                    if(size==0)
                    {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(queue[front]);
                    break;

                case "back":
                    if(size==0)
                    {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(queue[back]);
                    break;
            }
        }
    }
}