import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Violeta {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("violeta.dat"));
		int amt = in.nextInt();
		in.nextLine();
		while(amt-- > 0) {
			String str = in.next();
			out.println(findPalindrome(str));
		}
		in.close();
	}
	
	public static String findPalindrome(String str) { 
        int n = str.length();
        boolean table[][] = new boolean[n][n];
        int maxLength = 1;
        for (int i = 0; i < n; i++) 
            table[i][i] = true;
        int start = 0;
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                 if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    table[i][j] = true;
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        return str.substring(start, start+maxLength-1 + 1);
    }
}
