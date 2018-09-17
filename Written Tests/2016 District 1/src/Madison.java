import java.util.*;
import java.io.*;
import static java.lang.System.*;
import javax.script.*;

public class Madison {
	public static void main(String[] args) throws IOException, ScriptException {
		Scanner in = new Scanner(new File("madison.dat"));
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		while(in.hasNext()) {
			String eq = in.next();
			String vals = in.next();
			boolean[] arr = new boolean[3];
			for(int i = 0; i < vals.length(); i++) {
				arr[i] = vals.substring(i, i+1).equals("1");
			}
			eq = eq.replaceAll("A", String.valueOf(arr[0]));
			eq = eq.replaceAll("B", String.valueOf(arr[1]));
			eq = eq.replaceAll("C", String.valueOf(arr[2]));
			eq = eq.replaceAll("\\+", "||");
			eq = eq.replaceAll("\\*", "&&");
			Object obj = engine.eval(eq);
			if(obj instanceof Integer) {
				if((int)obj == 1) {
					out.println(true);
				}else {
					out.println(false);
				}
			}else {
				out.println(obj);
			}
		}
		in.close();
	}
}