import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Queue{
    int[] s = new int[10001];
    int front = 0;  //삭제될 위치
    int back = 0;   //삽입될 위치

    void push(int a) {
        s[back++] = a;
    }

    int pop() {
        return empty() ==1 ? -1 : s[front++];
    }

    int size() {
        return back-front;
    }

    int empty() {
        return front == back ? 1 : 0;
    }

    int front() {
        return empty() == 1 ? -1 : s[front];
    }

    int back() {
        return empty() == 1 ? -1 : s[back-1];
    }

    @Override
    public String toString() {
        return "Queue [front=" + front + ", back=" + back + ", s=" + Arrays.toString(s) + "]";
    }

}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Queue q = new Queue();
        String m;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            m = st.nextToken();
            switch (m) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    bw.write(q.pop()+"\n");
                    break;
                case "size":
                    bw.write(q.size()+"\n");
                    break;
                case "empty":
                    bw.write(q.empty()+"\n");
                    break;
                case "front":
                    bw.write(q.front()+"\n");
                    break;
                case "back":
                    bw.write(q.back()+"\n");
                    break;
                default:
                    break;
            }
        }

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }
}