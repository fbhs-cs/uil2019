import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Walter {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("walter.dat"));
		while(in.hasNext()) {
			double l = in.nextDouble();
			double w = in.nextDouble();
			double d = in.nextDouble();
			if(l >= 3.5 && l <= 4.25 && w >= 3.5 && w <= 6 && d >= .007 && d <= .016) {
				out.println("SMALL POST CARD");
			}else if(l >= 4.25 && l <= 6 && w >= 6 && w <= 11.5 && d >= .007 && d <= .016) {
				out.println("LARGE POST CARD");
			}else if(l >= 3.5 && l <= 6.125 && w >= 5 && w <= 11.5 && d >= .016 && d <= .25) {
				out.println("SMALL ENVELOPE");
			}else if(l >= 6.125 && l <= 24 && w >= 11 && w <= 18 && d >= .25 && d <= .5) {
				out.println("LARGE ENVELOPE");
			}else if(l > 24 && w > 18 && d > .5 && l + 2*w + 2*d <= 84) {
				out.println("SMALL PACKAGE");
			}else if(l + 2*w + 2*d <= 130 && l + 2*w + 2*d >= 84) {
				out.println("LARGE PACKAGE");
			}else {
				out.println("UNMAILABLE");
			}
		}
		in.close();
	}
}
