import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Caleb{
    public static void main(String [] args) throws IOException{
        Scanner s = new Scanner(new File("caleb.dat"));
        s.nextInt();
        while(s.hasNext()){
            String x = s.next();
            double h = Integer.parseInt(x.substring(0,x.indexOf("/")),10)/360.0*12;
            double m = (Integer.parseInt(x.substring(x.indexOf("/")+1),10)/360.0)*60;
            out.printf("%f:%2f",h,m);
            
        }
    }
}