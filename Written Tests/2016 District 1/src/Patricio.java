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
			double maxX = in.nextDouble();
			double maxY = in.nextDouble();
			int maxT = in.nextInt();
			eq = eq.substring(4);
			Object[] res = FindMaxZ(engine, eq, maxX, maxY, maxT);
			out.printf("Patricio should launch at x=%.1f and y=%.1f at t=%d at a height of %.1f.", res[0], res[1], res[2], res[3]);
		}
		in.close();
	}
	
	public static Object[] FindMaxZ(ScriptEngine engine, String eq, double maxX, double maxY, int maxT) throws ScriptException {
		Object[] res = {0.0, 0.0, 0, 0.0};
		for(double x = maxX; x <= maxX; x-=0.1) {
			for(double y = maxY; y <= maxY; y-=0.1) {
				for(int t = maxT; t <= maxT; t--) {
					String temp = eq;
					temp = ReplaceVal(temp, "x", x);
					temp = ReplaceVal(temp, "y", y);
					temp = ReplaceVal(temp, "t", t);
					temp = ReplacePower(temp);
					Object z = engine.eval(temp);
					if((double)z > (double)res[3]) {
						res[0] = x;
						res[1] = y;
						res[2] = t;
						res[3] = (double)z;
					}
				}
			}
		}
		return res;
	}

	public static int findNextOp(String str, int loc) {
		int[] arr = new int[4];
		int res = 0;
		arr[0] = str.indexOf("+", loc);
		arr[1] = str.indexOf("-", loc);
		arr[2] = str.indexOf("/", loc);
		arr[3] = str.indexOf("*", loc);
		for(int a : arr) {
			if(a > res) {
				res = a;
			}
		}
		return res;
	}

	public static int findFirstOp(String str, int loc) {
		str = str.substring(0, loc);
		int[] arr = new int[4];
		int res = 0;
		arr[0] = str.indexOf("+");
		arr[1] = str.indexOf("-");
		arr[2] = str.indexOf("/");
		arr[3] = str.indexOf("*");
		for(int a : arr) {
			if(a > res) {
				res = a;
			}
		}
		return res;
	}
	
	public static String ReplacePower(String eq) {
		String[] temp = eq.split(" ");
		for(int i = 0; i < temp.length; i++) {
			if(!(temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("/") || temp[i].equals("*"))) {
				if(temp[i].contains("^")) {
					int loc = temp[i].indexOf("^");
					int firstOp = findFirstOp(temp[i], loc);
					int nextOp = findNextOp(temp[i], loc);
					if(firstOp > 0 && nextOp > 0) {
						temp[i] = temp[i].substring(0, firstOp+1) + "Math.pow((" + temp[i].substring(firstOp+1, loc) + ")," + "(" + temp[i].substring(loc+1, nextOp) + "))" + temp[i].substring(nextOp);
					}else if(firstOp > 0) {
						temp[i] = temp[i].substring(0, firstOp+1) + "Math.pow((" + temp[i].substring(firstOp+1, loc) + ")," + "(" + temp[i].substring(loc+1) + "))";
					}else if(nextOp > 0) {
						temp[i] = "Math.pow((" + temp[i].substring(0, loc) + ")," + "(" + temp[i].substring(loc+1, nextOp) + "))" + temp[i].substring(nextOp);
					}else {
						temp[i] = "Math.pow((" + temp[i].substring(0, loc) + ")," + "(" + temp[i].substring(loc+1) + "))";
					}
				}
			}
		}
		return String.join(" ", temp);
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