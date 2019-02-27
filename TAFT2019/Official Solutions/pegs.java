import java.util.*;
import java.io.*;

public class pegs {

    public static int countPegs(char[][] board) {
        int count = 0;
        for (char[] row : board) {
            for (char c : row) {
                if (c == '@') {
                    count++;
                }
            }
        }
        return count;
    }

    public static class Board {
        boolean solved;
        char[][] board;

        public Board(char[][] board) {
            this.board = new char[board.length][board[0].length];
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    this.board[r][c] = board[r][c];
                }
            }
            this.solved = false;
        }

        public int countPegs() {
            int count = 0;
            for (char[] row : board) {
                for (char c : row) {
                    if (c == '@') {
                        count++;
                    }
                }
            }
            return count;
        }

        public void displayBoard() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        public void dispSolvable() {
            if (this.solved) {
                System.out.println("Solvable!");
            } else {
                System.out.println("Impossible.");
            }
        }

        public void solvable(Board mainBoard) {

            if (this.countPegs() == 1) {
                mainBoard.solved = true;
                //System.out.println("Solvable!");
                return;
            }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != '@')
                        continue;

                    // current spot is a peg check if it can jump something
                    if (j > 1 && !this.solved) {
                        if (board[i][j - 1] == '@' && board[i][j - 2] == '.') { // can jump left
                            Board tempBoard = new Board(board);
                            tempBoard.board[i][j - 2] = '@';
                            tempBoard.board[i][j - 1] = '.';
                            tempBoard.board[i][j] = '.';
                            tempBoard.solvable(mainBoard);
                        }
                    }

                    if (j < board[i].length - 2 && !this.solved) {
                        if (board[i][j + 1] == '@' && board[i][j + 2] == '.') { // can jump left
                            Board tempBoard = new Board(board);
                            tempBoard.board[i][j + 2] = '@';
                            tempBoard.board[i][j + 1] = '.';
                            tempBoard.board[i][j] = '.';
                            tempBoard.solvable(mainBoard);
                        }
                    }

                    if (i > 1 && !this.solved) {
                        if (board[i - 1][j] == '@' && board[i - 2][j] == '.') { // can jump up
                            Board tempBoard = new Board(board);
                            tempBoard.board[i - 2][j] = '@';
                            tempBoard.board[i - 1][j] = '.';
                            tempBoard.board[i][j] = '.';
                            tempBoard.solvable(mainBoard);
                        }
                    }

                    if (i < board.length - 2 && !this.solved) {
                        if (board[i + 1][j] == '@' && board[i + 2][j] == '.') { // can jump up
                            Board tempBoard = new Board(board);
                            tempBoard.board[i + 2][j] = '@';
                            tempBoard.board[i + 1][j] = '.';
                            tempBoard.board[i][j] = '.';
                            tempBoard.solvable(mainBoard);
                        }
                    }

                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pegs.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            int r = in.nextInt();
            int c = in.nextInt();
            //System.out.printf("%d %d\n", r, c);
            in.nextLine();
            char[][] board = new char[r][c];
            for (int i = 0; i < r; i++) {
                String row = in.nextLine();
                for (int j = 0; j < row.length(); j++) {
                    board[i][j] = row.charAt(j);

                }
            }

            Board mainBoard = new Board(board);
            //mainBoard.displayBoard();
            mainBoard.solvable(mainBoard);
            mainBoard.dispSolvable();

        }
    }
}