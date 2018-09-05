import java.util.*;
import java.io.*;
import static java.lang.System.*;

/* String adding solution IN PROGRESS
 * Matrix Multiplication is easier
 */
public class Nicholas {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("nicholas.dat"));
		while(in.hasNext()) {
			int n = in.nextInt();
			int p = in.nextInt();
			HashMap<String, Boolean> map = new HashMap<String, Boolean>();
			char defC = 'A';
			char c = 'A';
			for(int i = 0; i < n; i++) {
				String str = in.next();
				for(int j = 0; j < n; j++) {
					map.put(Character.toString(c) + Character.toString((char)(defC + j)), getBool(str.substring(j, j+1)));
				}
				c++;
			}
			out.println(map);
		}
		in.close();
	}
	
	public static Boolean getBool(String str) {
		if(str.equals("1")) {
			return true;
		}
		return false;
	}
}
