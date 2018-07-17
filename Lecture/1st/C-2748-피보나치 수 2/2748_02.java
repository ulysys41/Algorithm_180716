import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Matrix{
    long[][] matrix;
    int r, c;

    public Matrix(long[][] matrix) {
        this.r = matrix.length;
        this.c = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix(int r, int c) {
        super();
        this.r = r;
        this.c = c;
        this.matrix = new long[r][c];
    }

    public Matrix power(long k) {
        Matrix res = new Matrix(new long[][] {{1, 0}
                                             ,{0, 1}});
        Matrix a2k = this;

        while (k > 0) {
            if(k%2 == 1) res = res.multiply(a2k);
            a2k = a2k.multiply(a2k);
            k /= 2;
        }

        return res;
    }

    public Matrix multiply(Matrix m) {
        //AxB는 A 행렬의 컬럼의 열의 수와 B 행렬의 행의 수가 같아야 가능하다.!!! 
        if(this.c != m.r) return null;

        //AxB의 결과는 (A행렬의 행의수)X(B행렬의 열의수) 짜리 핼렬이다.  
        Matrix result = new Matrix(this.r, m.c);

        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < m.c; j++) {
                for (int k = 0; k < this.c; k++) {
                    result.matrix[i][j] += (this.matrix[i][k] * m.matrix[k][j]);
                }
            }
        }

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (long[] r : matrix) {
            sb.append(Arrays.toString(r) + " \n");
        }
        return sb.toString();
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

//      bw.write(String.valueOf("matrix="+matrix(n)+"\n"));
//      bw.write(String.valueOf("forLoop="+forLoop(n)+"\n"));
        bw.write(String.valueOf(matrix(n)+"\n"));

        long end = System.currentTimeMillis();
        bw.write("Time="+(end-start)+"ms\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static int recursive(int n) {
        if(n <= 2) return 1;
        return recursive(n-1) + recursive(n-2);
    }

    private static long forLoop(int n) {
        long n0 = 0;
        long n1 = 1;
        long n2 = 1;

        for (int i = 3; i <= n; i++) {
            n2 = (n0 + n1);
            n0 = n1;
            n1 = n2;
        }

        return n2;
    }

    private static long matrix(int n) {
        if(n==0) return 0;
        if(n==1 || n==2) return 1;

        Matrix pm = new Matrix(new long[][] {{1, 1},
                                             {1, 0}});
        Matrix result = pm.power(n-2).multiply(new Matrix(new long[][] {{1}, {1}}));

        return result.matrix[0][0];
    }
}