import java.util.*;
import java.io.*;

public class Candela {

    public static void solve(int[] v, int[] w, int W) {
        int[][] mat = new int[w.length+1][W+1];

        for (int i = 0; i <= w.length; i++) {
            for(int j = 0; j<=W; j++) {
                if(i == 0 || j == 0) {
                    mat[i][j] = 0;
                } else if(w[i-1]>j) {
                    mat[i][j] = mat[i-1][j];
                } else {
                    mat[i][j] = Math.max(mat[i-1][j], mat[i-1][j-w[i-1]]+v[i-1]);
                }
            }
        }

        System.out.println("Expected points = " + mat[w.length][W]);


    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("candela.dat"));
        int Q = in.nextInt();

        int[] points = new int[Q];
        int[] diff = new int[Q];

        for(int i = 0; i < Q; i++) {
            points[i] = in.nextInt();
            diff[i] = in.nextInt();
        }

        while(in.hasNext()) {
            solve(points,diff,in.nextInt());
        }
    }
}