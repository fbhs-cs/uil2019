import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Goro {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("goro.dat"));
        
        int m = in.nextInt();
        int n = 0;
        double v = 0.0;
        while(n<m) {
            double pi = Math.sqrt(12);
            for(int i = 0; i<=n;i++) {
                v += (1.0*Math.pow(-1,i))/((2*i+1)*Math.pow(3,i));
                
            }
            n++;
            pi *= v;
            v = 0.0;
            out.printf("%.5f\n",pi);
        }
    }
}