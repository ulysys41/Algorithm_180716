import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Student{
    int no;
    int indegree;
    List<Student> taller = new ArrayList<Student>();

    public Student(int no) {
        super();
        this.no = no;
    }

    public void addTaller(Student s) {
        taller.add(s);
        s.indegree++;
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int M;

    static Student[] students;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new Student[N+1];
        for (int i = 1; i <= N; i++) students[i] = new Student(i);

        int a, b;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            students[a].addTaller(students[b]);
        }

        Queue<Student> que = new LinkedList<Student>();
        for (int i = 1; i <= N; i++) {
            if(students[i].indegree == 0) {
                que.add(students[i]);
            }
        }

        Student s;
        while (!que.isEmpty()) {
            s = que.poll();
            answer.append(s.no + " ");

            for (Student t : s.taller) {
                t.indegree--;
                if(t.indegree == 0) que.add(t);
            }
        }

        bw.write(answer.toString());
        bw.newLine();

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }
}