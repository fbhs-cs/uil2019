import java.util.*;
import java.io.*;

public class word {

    // count the number of occurences of word in string
    public static int countWord(String string, String word) {
        int count = 0;
        while (string.indexOf(word) > -1) {
            count++;
            string = string.substring(string.indexOf(word) + 1);
        }
        return count;

    }

    public static void findWord(char[][] grid, String word) {
        int found = 0;

        ArrayList<String> allStrings = new ArrayList<String>();
        // make all possible strings

        // horizontal forward
        for (int i = 0; i < grid.length; i++) {
            String temp = "";
            for (int j = 0; j < grid[i].length; j++) {
                temp += grid[i][j];
            }
            allStrings.add(temp);
        }

        // horizontal backward
        for (int i = 0; i < grid.length; i++) {
            String temp = "";
            for (int j = 0; j < grid[i].length; j++) {
                temp += grid[i][grid[i].length - 1 - j];
            }
            allStrings.add(temp);
        }

        // vertical
        for (int i = 0; i < grid.length; i++) {
            String temp = "";
            for (int j = 0; j < grid[i].length; j++) {
                temp += grid[j][i];
            }
            allStrings.add(temp);
        }

        // vertical backward
        for (int i = 0; i < grid.length; i++) {
            String temp = "";
            for (int j = 0; j < grid[i].length; j++) {
                temp += grid[grid.length - 1 - j][i];
            }
            allStrings.add(temp);
        }

        // diagonal left -> right down
        for (int k = 0; k < grid.length; k++) {
            String temp = "";
            for (int i = 0; i < grid.length; i++) {

                for (int j = 0; j < grid[i].length; j++) {
                    if (Math.abs(i - j) == k)
                        temp += grid[i][j];
                }

            }
            allStrings.add(temp);
        }

        // diagonal right -> left up
        for (int k = 0; k < grid.length; k++) {
            String temp = "";
            for (int i = grid.length - 1; i >= 0; i--) {

                for (int j = grid[i].length - 1; j >= 0; j--) {
                    if (Math.abs(i - j) == k)
                        temp += grid[i][j];
                }

            }
            allStrings.add(temp);
        }

        // diagonal left -> right up
        for (int k = 0; k <= 2 * (grid.length - 1); k++) {
            String temp = "";
            for (int i = grid.length - 1; i >= 0; i--) {

                for (int j = grid[i].length - 1; j >= 0; j--) {
                    if (Math.abs(i + j) == k)
                        temp += grid[i][j];
                }

            }
            allStrings.add(temp);
        }

        // diagonal right -> left down
        for (int k = 0; k <= 2 * (grid.length - 1); k++) {
            String temp = "";
            for (int i = 0; i < grid.length; i++) {

                for (int j = 0; j < grid[i].length; j++) {
                    if (Math.abs(i + j) == k)
                        temp += grid[i][j];
                }

            }
            allStrings.add(temp);
        }

        for (String s : allStrings) {
            if (s.equals(word))
                found++;
        }
        System.out.println(found);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("word.dat"));

        int n = in.nextInt();

        while (n-- > 0) {
            int r = in.nextInt();
            int c = in.nextInt();

            char[][] grid = new char[r][c];

            for (int i = 0; i < r; i++) {
                String line = in.next();
                for (int j = 0; j < c; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            findWord(grid, "word");

        }
    }
}