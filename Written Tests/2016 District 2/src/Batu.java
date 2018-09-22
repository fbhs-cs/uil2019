import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Batu {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("batu.dat"));
		int[][] board = new int[9][9];
		for(int i = 0; i < 9; i++) {
			String line = in.next();
			for(int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(line.substring(j, j+1));
			}
		}
		while(in.hasNext()) {
			int row = in.nextInt();
			int col = in.nextInt();
			ArrayList<Integer> possible = findPossible(board, row, col);
			for(int x : possible) {
				out.print(x);
			}
			out.println();
		}
		in.close();
	}
	
	public static ArrayList<Integer> findPossible(int[][] board, int row, int col) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Integer> neg = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++) {
			if(!neg.contains(board[row-1][i]) && board[row-1][i] != 0) {
				neg.add(board[row-1][i]);
			}
			if(!neg.contains(board[i][col-1]) && board[i][col-1] != 0) {
				neg.add(board[i][col-1]);
			}
		}
		int a = row-1 - (row-1)%3;
		int b = col-1 - (col-1)%3;
		for(int i = a; i < a+3; i++) {
			for(int j = b; j < b+3; j++) {
				if(!neg.contains(board[i][j]) && board[i][j] != 0) {
					neg.add(board[i][j]);
				}
			}
		}
		for(int i = 1; i < 10; i++) {
			if(!neg.contains(i)) {
				res.add(i);
			}
		}
		return res;
	}
}
