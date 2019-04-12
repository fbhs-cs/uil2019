import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Debra{
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(new File("debra.dat"));
        while(s.hasNext()){
            int x = s.nextInt();
            int y = s.nextInt();
            s.nextLine();
            char[][] t = new char[x][];
            for(int xt = 0; xt < x;xt++){
                t[xt] = s.nextLine().toCharArray();
        }
            for(int xp = 0;xp < x-1; xp++){
                for(int yp = y-1; yp >= 0;yp-- ){
                    out.print(t[xp][yp]);
                }
                out.println();
            }
            out.println("=====");
        }

    }
}