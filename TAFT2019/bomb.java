import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class bomb
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("bomb.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int i = 0; i < times; i++)
        {
            int r = file.nextInt();
            int c = file.nextInt();
            char[][] mat = new char[r][c];
            for(int j = 0; j < r; j++){
                mat[j] = file.next().toCharArray();
            }
            int bestr = -1;
            int bestc = -1;
            int highest = 0;
            for(r = 0; r < mat.length; r++){
                for(c = 0; c < mat[0].length; c++){
                    if(mat[r][c] == '@') continue;
                    int curhighest = enemies(r, c, mat);
                    if(curhighest > highest){
                        bestr = r;
                        bestc = c;
                        highest = curhighest;
                    }
                }
            }
            System.out.println(bestr + ", " + bestc);
        }
    }

    public int enemies(int r, int c, char[][] mat){
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int out = 0;
        for(int i = 0; i < dirs.length; i++){
            int tr = r;
            int tc = c;
            while(Math.min(tr, tc) >= 0 && tr < mat.length && tc < mat[0].length && mat[tr][tc] != '#'){
                if(mat[tr][tc] == '@') out++;
                tr += dirs[i][0];
                tc += dirs[i][1];
            }
        }
        return out;
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new bomb().run();
    }

}