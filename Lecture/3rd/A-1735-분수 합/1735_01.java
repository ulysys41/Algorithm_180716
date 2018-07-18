import java.io.*;
import java.util.*;
public class Main {

    static int gcd(int a, int b)
    {
        if(b>0) return gcd(b,a%b);
        else return a;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int A1,B1,A2,B2;
        A1 = sc.nextInt();
        B1 = sc.nextInt();
        A2 = sc.nextInt();
        B2 = sc.nextInt();
        int U,D,g;
        D = B1*B2; // 분모 통분 
        U = A1*B2 + A2*B1; // 분자 계산 
        g = gcd(D,U);
        System.out.println(U/g+" "+D/g);
    }
}