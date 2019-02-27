import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class repeat
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("repeat.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int am = 0; am < times; am++)
        {
            String input = file.nextLine();
            String out = "";
            //Updating the starting point
            for(int i = 0; i < input.length() - 2 * out.length(); i++){
                //checking substrings of each length
                for(int len = out.length() + 1; len * 2 < input.length() - i; len++){
                    String cur = input.substring(i, i + len);
                    //looking for other substrings later in the string
                    for(int j = i + len; j < input.length() - len; j++){
                        //if the starting character isn't the same, the substrings can't be the same
                        if(cur.charAt(0) != input.charAt(j)) continue;
                        if(cur.equals(input.substring(j, j + len)))
                            out = cur;
                    }
                }
            }
            System.out.println(out.length() + " " + out);
        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new repeat().run();
    }

}