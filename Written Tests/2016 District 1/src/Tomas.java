import java.util.*;
import java.io.*;
import java.math.*;
import static java.lang.System.*;

public class Tomas {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("tomas.dat"));
		BigInteger[] arr = new BigInteger[50];
		for(int i = 0; i < 3; i++) arr[i] = BigInteger.valueOf(1);
		for(int i = 3; i < 50; i++) {
			arr[i] = arr[i-3].add(arr[i-2]).add(arr[i-1]);
		}
		while(in.hasNext()) {
			out.println(arr[in.nextInt()-1]);
		}
		in.close();
	}
}