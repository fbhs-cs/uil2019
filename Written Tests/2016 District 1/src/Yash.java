import java.util.*;
import java.io.*;
import java.text.*;
import static java.lang.System.*;

public class Yash {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("yash.dat"));
		NumberFormat myformat = NumberFormat.getInstance();
		myformat.setGroupingUsed(true);
		while(in.hasNext()) {
			String str = in.next();
			str.replaceAll(",", "");
			int num = Integer.parseInt(str);
			int x = 0;
			while(Math.pow(2, x) < num) x++;
			out.println("1 " + myformat.format(x) + " " + myformat.format(num) + " " + myformat.format(x*num) + " " + myformat.format(num*num));
		}
		in.close();
	}
}
