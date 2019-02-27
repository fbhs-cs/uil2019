import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class rain
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("rain.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int am = 0; am < times; am++)
        {
            String[] input = file.nextLine().split(" ");
            int[] arr = new int[input.length];
            for(int j = 0; j < arr.length; j++){
                arr[j] = Integer.parseInt(input[j]);
            }
            int[] l = new int[arr.length];
            l[0] = arr[0];
            int[] r = new int[arr.length];
            r[r.length - 1] = arr[arr.length - 1];
            for(int i = 1; i < l.length; i++){
                l[i] = Math.max(l[i - 1], arr[i]);
                r[r.length - 1 - i] = Math.max(r[r.length - i], arr[r.length - 1 - i]);
            }
            int out = 0;
            for(int i = 0; i < arr.length; i++){
                out += Math.min(l[i], r[i]) - arr[i];
            }
            System.out.println(out);

        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new rain().run();
    }

}