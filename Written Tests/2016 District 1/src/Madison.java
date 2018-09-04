import java.util.*;
import java.io.*;
import static java.lang.System.*;
import javax.script.*;

public class Madison {
	public static void main(String[] args) throws IOException, ScriptException {
		Scanner in = new Scanner(new File("madison.dat"));
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		while(in.hasNext()) {
			char c = 'A';
			String eq = in.next();
			String val = in.next();
			for(int i = 0; i < val.length(); i++) {
				eq = eq.replaceAll(Character.toString(c), val.substring(i, i+1));
				c++;
			}
			eq = eq.replaceAll("\\+", " || ");
			eq = eq.replaceAll("\\*", " && ");
			Object obj = engine.eval(eq);
			out.println(obj == (Object)1);
		}
		in.close();
	}
}
