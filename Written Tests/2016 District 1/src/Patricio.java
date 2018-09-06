import java.util.*;
import java.io.*;
import static java.lang.System.*;
import javax.script.*;

public class Patricio {
	public static void main(String[] args) throws IOException, ScriptException {
		Scanner in = new Scanner(new File("patricio.dat"));
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		int amt = in.nextInt();
		for(int i = 0; i < amt; i++) {
			in.nextLine();
			String eq = in.nextLine();
			double x = in.nextDouble();
			double y = in.nextDouble();
			int t = in.nextInt();
			eq = eq.substring(4);
			eq = ReplaceVal(eq, "x", x);
			eq = ReplaceVal(eq, "y", y);
			eq = ReplaceVal(eq, "t", t);
			eq = ReplacePower(eq);
			out.println(eq);
		}
		in.close();
	}
	
	public static String ReplacePower(String eq) {
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> ops = new ArrayList<String>();
		String[] temp = eq.split(" ");
		for(String s : temp) {
			if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) {
				ops.add(s);
			}else {
				arr.add(s);
			}
		}
		
		return eq;
	}
	
	public static String ReplaceVal(String eq, String let, Object val) {
		if(val instanceof Integer) {
			while(eq.contains(let)) {
				int loc = eq.indexOf(let);
				if(loc > 0 && isNumeric(eq.substring(loc-1, loc))) {
					eq = eq.substring(0, loc) + "*" + eq.substring(loc).replaceFirst(let, Integer.toString((int)val));
				}else {
					eq = eq.replaceFirst(let, Integer.toString((int)val));
				}
			}
		}else {
			while(eq.contains(let)) {
				int loc = eq.indexOf(let);
				if(loc > 0 && isNumeric(eq.substring(loc-1, loc))) {
					eq = eq.substring(0, loc) + "*" + eq.substring(loc).replaceFirst(let, Double.toString((double)val));
				}else {
					eq = eq.replaceFirst(let, Double.toString((double)val));
				}
			}
		}
		return eq;
	}
	
	public static Boolean isNumeric(String str) {
		try {
			int a = Integer.parseInt(str);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
}
