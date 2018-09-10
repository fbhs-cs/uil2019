import java.util.*;
import java.io.*;
import static java.lang.System.*;
import javax.script.*;

public class Patricio {
	public static void main(String[] args) throws IOException, ScriptException {
		Scanner in = new Scanner(new File("patricio.dat"));
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		int amt = in.nextInt();
		while(amt-- > 0) {
			in.nextLine();
			String eq = in.nextLine();
			double maxX = in.nextDouble();
			double maxY = in.nextDouble();
			int maxT = in.nextInt();
			eq = eq.substring(4);
			eq = AddMult(eq);
			eq = ReplacePower(eq);
			Object[] res = {0.0, 0.0, 0, 0.0};
			for(double x = 0; x <= maxX; x+=0.1) {
				for(double y = 0; y <= maxY; y+=0.1) {
					for(int t = 0; t <= maxT; t++) {
						String temp = eq;
						temp = temp.replaceAll("Math", "Maah");
						temp = temp.replaceAll("x", Double.toString(x));
						temp = temp.replaceAll("y", Double.toString(y));
						temp = temp.replaceAll("t", Integer.toString(t));
						temp = temp.replaceAll("Maah", "Math");
						double z = (double)engine.eval(temp);
						if(!(z >= Double.MAX_VALUE) && z > (double)res[3]) {
							res[0] = x;
							res[1] = y;
							res[2] = t;
							res[3] = z;
						}
					}
				}
			}
			out.printf("Patricio should launch at x=%.1f and y=%.1f at t=%d at a height of %.1f.\n", res[0], res[1], res[2], res[3]);
		}
		in.close();
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
		String[] arr = eq.split(" ");
		for(int i = 0; i < arr.length; i++) {
			if(!(arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*") || arr[i].equals("/")) && arr[i].contains("^")) {
				int loc = arr[i].indexOf("^");
				int firstOp = findFirstOp(arr[i], loc);
				int nextOp = findNextOp(arr[i], loc);
				if(firstOp > 0 && nextOp > 0) {
					arr[i] = arr[i].substring(0, firstOp+1) + "Math.pow((" + arr[i].substring(firstOp+1, loc) + ")," + "(" + arr[i].substring(loc+1, nextOp) + "))" + arr[i].substring(nextOp);
				}else if(firstOp > 0) {
					arr[i] = arr[i].substring(0, firstOp+1) + "Math.pow((" + arr[i].substring(firstOp+1, loc) + ")," + "(" + arr[i].substring(loc+1) + "))";
				}else if(nextOp > 0) {
					arr[i] = "Math.pow((" + arr[i].substring(0, loc) + ")," + "(" + arr[i].substring(loc+1, nextOp) + "))" + arr[i].substring(nextOp);
				}else {
					arr[i] = "Math.pow((" + arr[i].substring(0, loc) + ")," + "(" + arr[i].substring(loc+1) + "))";
				}
			}
		}
		return String.join(" ", arr);
	}
	
	public static String AddMult(String eq) {
		for(int i = 0; i < eq.length(); i++) {
			try {
				if((Character.isAlphabetic(eq.charAt(i)) && (Character.isDigit(eq.charAt(i+1)) || Character.isAlphabetic(eq.charAt(i+1)))) || (Character.isDigit(eq.charAt(i)) && Character.isAlphabetic(eq.charAt(i+1)))) {
					eq = eq.substring(0, i+1) + "*" + eq.substring(i+1);
				}
			}catch(Exception e) {}
		}
		return eq;
	}
}