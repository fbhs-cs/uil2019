import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Nicholas {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("nicholas.dat"));
		while(in.hasNext()) {
			int n = in.nextInt();
			int p = in.nextInt();
			int[][] arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				String str = in.next();
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(str.substring(j, j+1));
				}
			}
			arr = Mult(arr, arr, p);
			out.println(Count(arr));
		}
		in.close();
	}
	
	public static int[][] Mult(int[][] arr, int[][] bArr, int p) {
		if(p == 1) {
			return arr;
		}
		int[][] temp = new int[arr.length][arr.length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				for(int k = 0; k < arr.length; k++) {
					temp[i][j] += arr[i][k] * bArr[k][j];
				}
			}
		}
		p--;
		if(p > 1) {
			temp = Mult(temp, bArr, p);
		}
		return temp;
	}
	
	public static int Count(int[][] arr) {
		int res = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				res += arr[i][j];
			}
		}
		return res;
	}
}
