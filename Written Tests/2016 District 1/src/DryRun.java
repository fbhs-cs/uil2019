import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class DryRun {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("dryrun.dat"));
		int amt = in.nextInt();
		for(int i = 0; i < amt; i++) {
			out.println("I like " + in.next() + ".");
		}
		in.close();
	}
}
