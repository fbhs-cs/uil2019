import java.io.*;
import java.util.*;

public class treasureDp {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("treasure.dat"));

        int n = in.nextInt();
        while(n-->0) {
            int i = in.nextInt();
            int w = in.nextInt();

            int[] weights = new int[i+1];
            int[] values = new int[i+1];

            int[][] mat = new int[i+1][w+1];

            for(int j = 1;j<=i;j++) {
                values[j] = in.nextInt();
                weights[j] = in.nextInt();
            }

            for(int a = 0; a <= i; a++) {
                for(int b = 0; b <= w; b++) {
                    if(a == 0 || b == 0) {
                        mat[a][b] = 0;
                    }

                    else if(weights[a-1]>b) {
                        mat[a][b] = mat[a-1][b];
                    } else {
                        mat[a][b] = Math.max(mat[a-1][b], mat[a-1][b-weights[a-1]]+values[a-1]);
                    }


                }
            }

            // for(int[] r:mat) {
            //     for(int a:r) {
            //         System.out.print(a + " ");
            //     }
            //     System.out.println();
            // }

            // answer is in the last row/col
            System.out.println(mat[i][w]);

        }
    }
}