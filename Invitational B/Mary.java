import java.util.*;
import java.io.*;
import static java.lang.System.*;

// sort of works...ignores lexographic order for now

public class Mary {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("maryj.dat"));
        int n = in.nextInt();
        boolean possible = true;
        while (n-- > 0) {
            possible = true;
            int numWheels = in.nextInt();
            ArrayList<String> toy = new ArrayList<String>();
            for (int i = 0; i < numWheels; i++) {
                toy.add(in.next());
            }
            
            int numTurns = 0;
            char[] word = new char[numWheels];
            int row = 0;
            int col = 0;
            while (row < numWheels / 2) {
                while (col < toy.get(row).length()) {
                    char top = toy.get(row).charAt(col);
                    int index = toy.get(toy.size() - 1 - row).indexOf(top); // first index in bottom
                    int last = toy.get(toy.size() - 1 - row).lastIndexOf(top); // last index in bottom
                    if (index == -1) { // top not in bottom
                        col++;
                        break;
                    } else {
                        // out.println(top);
                    }

                    numTurns += col;
                    // out.print(col + " ");
                    // out.print(Math.min(index, toy.get(toy.size() - 1 - row).length() - last));
                    numTurns += Math.min(index, toy.get(toy.size() - 1 - row).length() - last);
                    word[row] = toy.get(row).charAt(col);
                    // out.println(" " + word[row]);
                    word[word.length - 1 - row] = toy.get(row).charAt(col);
                    row++;
                    if (row >= numWheels / 2)
                        break;
                    col = 0;

                }
                if (col == toy.get(row).length()) {
                    out.println("IMPOSSIBLE");
                    possible = false;
                    break;
                }

            }
            if (numWheels % 2 == 1) {
                word[numWheels / 2] = toy.get(numWheels / 2).charAt(0);
            }

            for (char c : word) {
                out.print(c);
            }
            if (possible)
                out.println(" " + numTurns);
            // out.println();

        }
    }
}