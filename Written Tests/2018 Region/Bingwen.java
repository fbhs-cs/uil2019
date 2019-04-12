import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Bingwen{
    public static void main(String [] args) throws IOException{
        Scanner s = new Scanner(new File("bingwen.dat"));
        while(s.hasNext()){
            long x = s.nextInt();
            if(x > 0)
            out.printf("%.2f\n",Math.pow(Math.abs(x),1.0/3));
            else
            out.printf("-"+"%.2f\n",Math.pow(Math.abs(x),1.0/3));
            
        }
    }
}