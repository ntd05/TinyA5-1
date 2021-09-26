import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TinyA5 {

    public static int maij(Integer a, Integer b, Integer c) {
        int m = a;
        if( a== b || a == c) {
            return m;
        }
        if ( a != b && b == c) {
            return b;
        }
        return m;

    }

    public static int[] Turn(int []a) {
        int n = a.length;
        int []b = new int[n];
        if( n == 6) {
            b[0] = a[2] ^ a[4] ^ a[5];
        }
        if(n == 8) {
            b[0] = a[6] ^ a[7];
        }
        if(n == 9) {
            b[0] = a[2] ^ a[7] ^ a[8];
        }
        for(int i = 0 ; i < n - 1; i++) {
            b[i + 1] = a[i];
        }
        return b;
    }

    public static void main(String[] args) {
        TinyA5 tinyA5 = new TinyA5();
        Scanner scan = new Scanner(System.in);
        String P = scan.nextLine();
        ArrayList PEncode = new ArrayList();
        for(int i = 0; i < P.length(); i++) {
            String str = Integer.toBinaryString((int)P.charAt(i)- 97);
            String str1 ="";
            if(str.length() == 0) {
                str1 ="000";
            }
            if(str.length() == 1) {
                str1 ="00";
                str1 += str;
            }
            if(str.length() == 2) {
                str1 ="0";
                str1 += str;
            }
            if(str.length() == 3) {
                str1 = str;
            }
            for(int j = 0; j < str1.length(); j++) {
                PEncode.add(Integer.parseInt(String.valueOf(str1.charAt(j))));
            }
        }
        String K = scan.nextLine();
        int x[] = new int[6];
        int y[] = new int[8];
        int z[] = new int[9];
        for(int i = 0; i < K.length(); i++) {
            if(i < 6) {
                x[i] = Integer.parseInt(String.valueOf(K.charAt(i)));
            }
            if(i < 14 && i >= 6) {
                y[i - 6] = Integer.parseInt(String.valueOf(K.charAt(i)));
            }
            if(i >= 14 ) {
                z[i - 14] = Integer.parseInt(String.valueOf(K.charAt(i)));
            }
        }
        ArrayList S = new ArrayList();
        for(int i = 0; i < PEncode.size(); i++) {
            int m = maij(x[1], y[3], z[3]);
            if(x[1] == m) {
                x = Turn(x);
            }
            if(y[3] == m) {
                y = Turn(y);
            }
            if(z[3] == m) {
                z = Turn(z);
            }
            S.add( x[5] ^ y[7] ^ z[8]);
        }
        System.out.print("S = ");
        for(int i = 0; i< S.size(); i++)
        {
            System.out.print(S.get(i));
        }
        System.out.println();
        System.out.print("C = ");
        for(int i = 0; i< S.size(); i++)
        {
            System.out.print((int)S.get(i) ^ (int)PEncode.get(i));
        }
    }
}
