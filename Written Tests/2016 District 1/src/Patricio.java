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
			eq = Replace(eq, "x", x);
			eq = Replace(eq, "y", y);
			eq = Replace(eq, "t", t);
			out.println(eq);
			//double z = (double)engine.eval(eq);
			//out.printf("Patricio should launch at x=%f and y=%f at t=%d at a height of %f.", x, y, t, z);
		}
		in.close();
	}
	
	public static Boolean isNumeric(String str) {
		try {
			int a = Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static String Replace(String str, String c, Object obj) {
		if(obj instanceof Integer) {
			while(str.contains(c)) {
				int index = str.indexOf(c);
				if(index != 0 || isNumeric(str.substring(index-1, index))) {
					str = str.replaceFirst(c, "\\*" + Integer.toString((Integer)obj));
				}else {
					str = str.replaceFirst(c, Integer.toString((Integer)obj));
				}
			}
		}else {
			while(str.contains(c)) {
				int index = str.indexOf(c);
				if(index != 0 || isNumeric(str.substring(index-1, index))) {
					str = str.replaceFirst(c, "\\*" + Double.toString((Double)obj));
				}else {
					str = str.replaceFirst(c, Double.toString((Double)obj));
				}
			}
		}
		return str;
	}
}
