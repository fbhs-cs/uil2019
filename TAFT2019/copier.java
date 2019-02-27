import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class copier
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("copier.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int i = 0; i < times; i++)
        {
            String t = file.next();
            System.out.println(t + " " + t);
        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new copier().run();
    }

}