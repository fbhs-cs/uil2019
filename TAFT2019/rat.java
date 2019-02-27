import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class rat
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("rat.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int am = 0; am < times; am++)
        {
            int f = file.nextInt();
            int r = file.nextInt();
            int c = file.nextInt();
            int sf = -1;
            int sr = -1;
            int sc = -1;
            int ef = -1;
            int er = -1;
            int ec = -1;
            char[][][] mat = new char[f][r][c];
            for(int i = 0; i < f; i++){
                for(int j = 0; j < r; j++){
                    String line = file.next();
                    if(line.contains("S")){
                        sf = i; sr = j; sc = line.indexOf("S");
                    }
                    if(line.contains("E")){
                        ef = i; er = j; ec = line.indexOf("E");
                    }
                    mat[i][j] = line.toCharArray();
                }
            }
            int[][][] qshad = new int[f][r][c];
            for(int[][] m : qshad){
                for(int[] row : m){
                    Arrays.fill(row, Integer.MAX_VALUE);
                }
            }

            int[][][] sshad = new int[f][r][c];
            for(int[][] m : sshad){
                for(int[] row : m){
                    Arrays.fill(row, Integer.MAX_VALUE);
                }
            }

            recurs(mat, sf, sr, sc, sshad, 0);
            recurq(mat, sf, sr, sc, qshad, 0, false);

            System.out.println("Shortest Path: " + sshad[ef][er][ec] + " spaces\nQuickest Path: " + qshad[ef][er][ec] + " seconds");

        }
    }

    public void recurq(char[][][] mat, int f, int r, int c, int[][][] qshad, int time, boolean updown){
        if(Math.min(f, Math.min(r, c)) < 0 || f >= mat.length || r >= mat[0].length || c >= mat[0][0].length || mat[f][r][c] =='#' || qshad[f][r][c] < time)
            return;
        int add = 0;
        if(mat[f][r][c] != 'S'){
            add = 1;
            if(mat[f][r][c] == 'T') add += 2;
            if(updown) add *= 2;
        }
        time += add;
        qshad[f][r][c] = time;
        recurq(mat, f+1, r, c, qshad, time, true);
        recurq(mat, f-1, r, c, qshad, time, true);
        recurq(mat, f, r+1, c, qshad, time, false);
        recurq(mat, f, r-1, c, qshad, time, false);
        recurq(mat, f, r, c+1, qshad, time, false);
        recurq(mat, f, r, c-1, qshad, time, false);
    }

    public void recurs(char[][][] mat, int f, int r, int c, int[][][] sshad, int dist){
        if(Math.min(f, Math.min(r, c)) < 0 || f >= mat.length || r >= mat[0].length || c >= mat[0][0].length || mat[f][r][c] =='#' || sshad[f][r][c] < dist)
            return;
        sshad[f][r][c] = dist;
        dist += 1;
        recurs(mat, f+1, r, c, sshad, dist);
        recurs(mat, f-1, r, c, sshad, dist);
        recurs(mat, f, r+1, c, sshad, dist);
        recurs(mat, f, r-1, c, sshad, dist);
        recurs(mat, f, r, c+1, sshad, dist);
        recurs(mat, f, r, c-1, sshad, dist);
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new rat().run();
    }

}