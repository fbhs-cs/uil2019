import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class word
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("word.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        //read in each data set
        for(int i = 0; i < times; i++)
        {
            int r = file.nextInt();
            int c = file.nextInt();
            char[][] mat = new char[r][c];
            for(int j = 0; j < r; j++){
                mat[j] = file.next().toCharArray();
            }
            int output = 0;
            for(r = 0; r < mat.length; r++){
                for(c = 0; c < mat[0].length; c++){
                    output +=   recur(mat, r, c, "word", dirs[0]) +
                                recur(mat, r, c, "word", dirs[1]) +
                                recur(mat, r, c, "word", dirs[2]) +
                                recur(mat, r, c, "word", dirs[3]) +
                                recur(mat, r, c, "word", dirs[4]) +
                                recur(mat, r, c, "word", dirs[5]) +
                                recur(mat, r, c, "word", dirs[6]) +
                                recur(mat, r, c, "word", dirs[7]);
                }
            }
            System.out.println(output);
        }
    }

    public int recur(char[][] mat, int r, int c, String word, int[] dir){
        if(Math.min(r, c) < 0 || r >= mat.length || c >= mat[0].length || word.length() == 0 || mat[r][c] != word.charAt(0))
            return 0;
        if(word.length() == 1){
            return 1;
        }
        String newWord = word.substring(1);
        return recur(mat, r + dir[0], c + dir[1], newWord, dir);
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new word().run();
    }

}