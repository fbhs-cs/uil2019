import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Rishabh {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("rishabh.dat"));
		while(in.hasNext()) {
			String bin = Integer.toBinaryString(Integer.parseInt(in.next(), 16));
			String par = in.next();
			int n = log(bin.length()) + 1;
			String[] arr = new String[bin.length() + n];
			int pos = 1;
			int binC = 0;
			for(int i = 0; i < arr.length; i++) {
				if(n > 0 && i == pos-1) {
					arr[i] = "_";
					pos *= 2;
					n--;
				}else {
					arr[i] = Character.toString(bin.charAt(binC++));
				}
			}
			String newBin = String.join("", arr);
			String res = "";
			while(newBin.contains("_")) {
				pos = newBin.indexOf("_")+1;
				int count = 0;
				int sum = 0;
				Boolean skip = false;
				for(int i = pos-1; i < newBin.length(); i++) {
					if(!skip) {
						if(!(newBin.charAt(i) == '_')) {
							sum += Integer.parseInt(Character.toString(newBin.charAt(i)));
						}
						count++;
					}else {
						count++;
					}
					if(count == pos) {
						skip = !skip;
						count = 0;
					}
				}
				if(par.equals("EVEN")) {
					if(sum % 2 == 0) {
						newBin = newBin.replaceFirst("_", "0");
						res += "0";
					}else {
						newBin = newBin.replaceFirst("_", "1");
						res += "1";
					}
				}else {
					if(sum % 2 == 0) {
						newBin = newBin.replaceFirst("_", "1");
						res += "1";
					}else {
						newBin = newBin.replaceFirst("_", "0");
						res += "0";
					}
				}
			}
			out.println(res);
		}
		in.close();
	}
	
	public static int log(int x) {
		return (int)(Math.ceil(Math.log(x) / Math.log(2)));
	}
}
