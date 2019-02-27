import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class date
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("date.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        TreeMap<String, Integer> months = new TreeMap<>();
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);

        //read in each data set
        for(int i = 0; i < times; i++)
        {
            String monthstr = file.next();
            int month;
            if(months.get(monthstr) != null)
                month = months.get(monthstr);
            else month = -1;
            String daystr = file.next();
            int day = Integer.parseInt(daystr.substring(0, daystr.length() - 1));
            int year = file.nextInt();
            if(month == -1 || day < 1 || day > 31) System.out.println("Invalid");
            else System.out.printf("%02d/%02d/%02d\n", month, day, year % 100);
        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new date().run();
    }

}