import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Susan {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("susan.dat"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String reg = line.substring(line.indexOf("["), line.indexOf("]")+1);
			int pos = Integer.parseInt(line.substring(line.indexOf("]")+1));
			line = line.substring(0, line.indexOf("[")-1);
			line = line.replaceAll(reg, "nnP");
			String[] arr = line.split("nnP");
			out.println(arr[pos]);
		}
		in.close();
	}
}
