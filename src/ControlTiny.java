import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlTiny {

    @FXML private TextField PlainText;
    @FXML private TextField Key;
    @FXML private Button Encode;
    @FXML private TextField Cbit;
    @FXML private TextField CText;

    /**
     * tính giá trị maij
     */
    public static int maij(Integer a, Integer b, Integer c) {
        int m = a;
        if( a == b || a == c) {
            return m;
        }
        if ( a != b && b == c) {
            return b;
        }
        return m;
    }

    /**
     * quay thanh ghi.
     */
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
        for (int i = 0 ; i < n - 1; i++) {
            b[i + 1] = a[i];
        }
        return b;
    }

    /**
     * chuyển bản mã hóa từ bit sang ký tự chữ cái
     */
    public static String Encrypt(int a, int b, int c) {
        String s = "";
        s += Integer.toString(a) + Integer.toString(b) + Integer.toString(c);
        int foo = Integer.parseInt(s, 2);
        char x = (char) ((char) foo + 97);
        return Character.toString(x);
    }

    public void Ok(ActionEvent event) throws IOException {
        try {
            String P = PlainText.getText().toLowerCase();
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
            String K = "0";
            K = Key.getText().toLowerCase();
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
            ArrayList C = new ArrayList();
            String codeBit = "";
            String codeText = "";
            for(int i = 0; i< S.size(); i++)
            {
                C.add((int)S.get(i) ^ (int)PEncode.get(i));
                codeBit += C.get(i).toString();
            }
            for(int i = 0; i< C.size(); i += 3) {
                codeText += Encrypt((int)C.get(i), (int)C.get(i+ 1), (int)C.get(i+ 2));
            }
            Cbit.setText(codeBit);
            CText.setText(codeText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
