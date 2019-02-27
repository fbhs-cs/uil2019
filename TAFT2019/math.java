import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class math
{
    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("math.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int i = 0; i < times; i++)
        {
            String[] input = file.nextLine().split(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(input));
            double a; double b;
            for(int x = 0; x < list.size(); x++){
                switch(list.get(x).charAt(0)) {
                    case '*':
                        a = Double.parseDouble(list.remove(x - 1));
                        list.remove(x - 1);
                        b = Double.parseDouble(list.remove(x - 1));
                        list.add(x - 1, "" + (a * b));
                        x--;
                        break;
                    case '/':
                        a = Double.parseDouble(list.remove(x - 1));
                        list.remove(x - 1);
                        b = Double.parseDouble(list.remove(x - 1));
                        list.add(x - 1, "" + (a / b));
                        x--;
                        break;
                    case '%':
                        a = Double.parseDouble(list.remove(x - 1));
                        list.remove(x - 1);
                        b = Double.parseDouble(list.remove(x - 1));
                        list.add(x - 1, "" + (a % b));
                        x--;
                        break;
                }
            }
            while(list.size() > 1){
                a = Double.parseDouble(list.remove(0));
                String str = list.remove(0);
                b = Double.parseDouble(list.remove(0));
                if(str.equals("+"))
                    list.add(0, "" + (a + b));
                else list.add(0, "" + (a - b));
            }
            System.out.println(Math.round(Double.parseDouble(list.get(0))));
        }
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new math().run();
    }

}