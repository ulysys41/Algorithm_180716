import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int stack[] = new int[10001],top;

    static String cmd;
    static int arg;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        top=0;

        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            switch(cmd)
            {
                case "push":
                    //top위치에 데이터 추가 및 top위치 증가.
                    stack[top++]=Integer.parseInt(st.nextToken());
                    break;

                case "pop":
                    if(top==0){
                        System.out.println(-1);
                        break;
                    }
                    //top-1번째 원소 출력 및 top위치 감소.
                    System.out.println(stack[--top]);
                    break;

                case "size":
                    System.out.println(top);
                    break;

                case "empty":
                    System.out.println(top==0?1:0);
                    break;

                case "top":
                    if(top==0)
                    {
                        System.out.println(-1);
                        break;
                    }
                    System.out.println(stack[top-1]);
                    break;
            }
        }
    }
}+