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
            HashMap<String, Integer> words = new HashMap<String, Integer>();
            int numTurns = 0;
            char[] word = new char[numWheels];
            int row = 0;
            int col = 0;
            int[] colLeftOff = new int[numWheels/2+1];
            boolean found = false;
            while (row < numWheels / 2) {
                col = colLeftOff[row];
                
                while (col < toy.get(row).length()) {
                    found = false;
                    char top = toy.get(row).charAt(col);
                    int index = toy.get(toy.size() - 1 - row).indexOf(top); // first index in bottom
                    int last = toy.get(toy.size() - 1 - row).lastIndexOf(top); // last index in bottom
                    if (index == -1) { // top not in bottom
                        col++;
                        continue;
                    } else {
                        
                    }
                    found = true;
                    numTurns += Math.min(col, toy.get(row).length() - 1 - col);
                    // out.print(col + " ");
                    // out.print(Math.min(index, toy.get(toy.size() - 1 - row).length() - last));
                    numTurns += Math.min(index, toy.get(toy.size() - 1 - row).length() - last);
                    word[row] = toy.get(row).charAt(col);
                    // out.println(" " + word[row]);
                    word[word.length - 1 - row] = toy.get(row).charAt(col);
                    row++;
                    colLeftOff[row] = col;
                    if (row >= numWheels / 2)
                        break;
                    col = 0;

                }
                if (!found) {
                    out.println("IMPOSSIBLE");
                    possible = false;
                    break;
                }

            }
            if (numWheels % 2 == 1) {
                word[numWheels / 2] = toy.get(numWheels / 2).charAt(0);
            }

            String outStr = "";
            for (char c : word) {
                outStr += c;
            }
            if (possible)
                words.put(outStr, numTurns);

            List<Integer> values = new ArrayList<Integer>(words.values());
            List<String> keys = new ArrayList<String>(words.keySet());
            //out.println(values);
            //out.println(keys);
            Collections.sort(values);
            if (values.size() > 1 && values.get(0) == values.get(1)) { // same number of turns
                ArrayList<String> ans = new ArrayList<String>();
                for (String s : keys) {
                    if (words.get(s) == values.get(0)) {
                        ans.add(s);
                    }
                }
                Collections.sort(ans);
                System.out.println(ans.get(0) + " " + values.get(0));

            }

            else {
                for (String s : keys) {
                    if (words.get(s) == values.get(0)) {
                        System.out.println(s + " " + values.get(0));
                        break;
                    }
                }
            }

        }
    }
}