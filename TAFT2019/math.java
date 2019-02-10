import java.util.*;
import java.io.*;
import javax.script.*;

public class math {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("math.dat"));

        int n = in.nextInt();
        in.nextLine();
        while (n-- > 0) {
            ScriptEngineManager man = new ScriptEngineManager();
            ScriptEngine s = man.getEngineByName("javascript");
            try {
                Object ans = s.eval(in.nextLine());
                System.out.println(Integer.parseInt(ans.toString()));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}