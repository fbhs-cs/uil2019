import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Yash {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("yash.dat"));
		while(in.hasNext()) {
			String str = in.next();
			str.replaceAll(",", "");
			int num = Integer.parseInt(str);
			int x = 0;
			while(Math.pow(2, x) < num) x++;
			out.println("1 " + toString(x) + " " + toString(num) + " " + toString(num*x) + " " + toString(num*num));
		}
		in.close();
	}
	
	public static String toString(int num) {
		StringBuilder str = new StringBuilder();
		str.append(Integer.toString(num));
		str.reverse();
		StringBuilder res = new StringBuilder();
		int c = 0;
		while(c < str.length()) {
			if(c != 0 && c % 3 == 0) {
				res.append(",");
			}else {
				res.append(str.substring(c, c+1));
			}
		}
		res.reverse();
		return res.toString();
	}
}
