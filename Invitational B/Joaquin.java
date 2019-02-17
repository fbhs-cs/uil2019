import java.util.*;
import java.io.*;

public class Joaquin {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("joaquinj.dat"));
        char[][] squares;
        int n = in.nextInt();
        while (n-- > 0) {
            int size = in.nextInt();

            squares = new char[size][size];
            
            // initialize
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    squares[r][c] = ' ';
                }
            }

            // FILL IN SQUARES
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (r == 0 || r == size - 1) {
                        squares[r][c] = 'X';
                    }
                    if(c == 0 || c == size-1) {
                        squares[r][c] = 'X';
                    }
                    if(r%2==0 && r < size/2 && c > r-1 && c < size - r) {
                        squares[r][c] = 'X';
                    }
                    if((size-1-r)%2 == 0 && r >= size/2 && c > size-1-r-1 && c < size - (size-1-r)) {
                        squares[r][c] = 'X';
                    }
                    
                    if(c%2==0 && r > c -1 && r < size - c) {
                         squares[r][c] = 'X';
                    }

                    if((size-1-c)%2 == 0 && r > size-1-c-1 && r < size - (size-1-c)) {
                        squares[r][c] = 'X';
                    }

                }
            }

            // print squares
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    System.out.print(squares[r][c]);
                }
                System.out.println();
            }
            System.out.println("--------------------");
        }

    }

}