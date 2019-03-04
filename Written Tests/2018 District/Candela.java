import java.util.*;
import java.io.*;

import static java.lang.System.*;

public class Candela {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("candela.dat"));

        int Q = in.nextInt();
        int[] P = new int[Q];
        int[] D = new int[Q];
        for(int i = 0;i < Q; i++) {
            P[i] = in.nextInt();
            D[i] = in.nextInt();
        }

        while(in.hasNext()) {
            int T = in.nextInt();
            int[][] mat = new int[Q+1][T+1];
            for(int i = 1; i <= Q; i++) {
                for(int j = 1; j<=T; j++) {
                    if(D[i-1] > j) {
                        mat[i][j] = mat[i-1][j];
                    } else {
                    mat[i][j] = Math.max(mat[i-1][j],P[i-1] + mat[i-1][j-D[i-1]]);
                    }
                }
            }
            int expected = mat[Q][T];
            ArrayList<Integer> taken = new ArrayList<Integer>();

            int q = Q;
            int t = T;
            while(t > 0 && q > 0 ) {
                if(mat[q][t] == mat[q-1][t]) { // item not taken
                    q--;
                    continue;
                }
                taken.add(q);
                t -= D[q-1];
                q--;
            }
            
            int calc = 0;
            for(int qn: taken) {
                calc += D[qn-1];
            }

            out.printf("Target diff     = %d\n",T);
            out.printf("Calculated diff = %d\n",calc);
            out.printf("Expected points = %d\n",expected);
            for(int i = taken.size()-1;i>=0;i--) {
                int qn = taken.get(i);
                out.printf("Q#%2d, %2d pts, diff %d\n",qn,P[qn-1],D[qn-1]);

            }
            out.println("=====");

            

        }
    }
}