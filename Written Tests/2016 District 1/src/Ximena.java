import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Ximena {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ximena.dat"));
		while(in.hasNext()) {
			String str = in.next();
			int mid = str.length()/2;
			String res = "";
			StringBuilder temp = new StringBuilder();
			if(str.length() % 2 == 1) {
				temp.append(str.substring(0, mid));
				res += temp.toString() + temp.reverse().toString();
				temp.setLength(0);
				temp.append(str.substring(mid));
				res += temp.reverse().toString() + temp.reverse().toString();
			}else {
				temp.append(str.substring(0, mid));
				res += temp.reverse().toString() + temp.reverse().toString();
				temp.setLength(0);
				temp.append(str.substring(mid));
				res += temp.toString() + temp.reverse().toString();
			}
			out.println(res);
		}
		in.close();
	}
}
