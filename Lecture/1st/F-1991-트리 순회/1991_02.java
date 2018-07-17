import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node{
    char c;
    Node leftChild;
    Node rightChild;

    public Node(char c) {
        super();
        this.c = c;
    }

    public String toString() {
        return "Node [c=" + c + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static Node[] nodes = new Node[26];

    static StringBuilder answer;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 26; i++) {
            nodes[i] = new Node((char)('A'+i));
        }

        N = Integer.parseInt(br.readLine());

        char p, lc, rc;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p  = st.nextToken().charAt(0);
            lc  = st.nextToken().charAt(0);
            rc  = st.nextToken().charAt(0);
            if(lc != '.') nodes[p-'A'].leftChild = nodes[lc-'A'];
            if(rc != '.') nodes[p-'A'].rightChild = nodes[rc-'A'];
        }

        answer = new StringBuilder();
        preOrder(nodes[0]);
        answer.append("\n");

        inOrder(nodes[0]);
        answer.append("\n");

        postOrder(nodes[0]);
        answer.append("\n");

        bw.write(answer.toString());

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static void preOrder(Node n) {
        if(n==null) return;
        answer.append(n.c);
        preOrder(n.leftChild);
        preOrder(n.rightChild);
    }

    private static void inOrder(Node n) {
        if(n==null) return;
        inOrder(n.leftChild);
        answer.append(n.c);
        inOrder(n.rightChild);
    }

    private static void postOrder(Node n) {
        if(n==null) return;
        postOrder(n.leftChild);
        postOrder(n.rightChild);
        answer.append(n.c);
    }
}