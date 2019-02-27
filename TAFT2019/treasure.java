import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class treasure
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("treasure.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int am = 0; am < times; am++) {
            int items = file.nextInt();
            int weight = file.nextInt();
            int[] vals = new int[items];
            int[] wts = new int[items];

            for (int i = 0; i < items; i++) {
                vals[i] = file.nextInt();
                wts[i] = file.nextInt();
            }
            int[][] k = new int[items + 1][weight + 1];
            for (int i = 1; i <= items; i++) {
                for (int w = 1; w <= weight; w++) {
                    if (wts[i - 1] <= w) {
                        k[i][w] = Math.max(k[i - 1][w], vals[i - 1] + k[i - 1][w - wts[i - 1]]);
                    } else {
                        k[i][w] = k[i - 1][w];
                    }
                }
            }
            System.out.println(k[items][weight]);
        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new treasure().run();
    }

}