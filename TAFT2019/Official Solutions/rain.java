import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class rain {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("rain.dat"));

        int n = in.nextInt();

        while (n-- > 0) {
            in.nextLine(); // clear newline character
            in.useDelimiter(" ");
            ArrayList<Integer> heights = new ArrayList<Integer>();
            while (in.hasNextInt()) {
                heights.add(in.nextInt());
            }

            // with delimiter " " it won't find the last element in a line
            // except at the EOF, so check if there is a newline character,
            // then reset the delimiter back to default and read in nextInt
            if (in.hasNextLine()) {
                in.reset();
                heights.add(in.nextInt());
            }

            // for (int num : heights) {
            // System.out.println(num);
            // }

            int left = 0;
            int right = 1;
            int water = 0;
            while (right < heights.size()) {
                // two buildings with same height are next to each other
                if (heights.get(left) <= heights.get(left + 1)) {
                    left++;
                    right++;
                    continue;
                }

                // scan for same height buildings
                boolean sameHeight = false;
                for (int i = left + 1; i < heights.size(); i++) {
                    if (heights.get(i) == heights.get(left)) {
                        right = i;
                        sameHeight = true;
                        break;
                    }
                }

                // if no same height buildings are found, find next enclosed area
                if (!sameHeight && heights.get(right) >= heights.get(right + 1)) {
                    right++;
                    continue;
                } else if (!sameHeight) {
                    right++;
                }

                // if we get here, we've found an area to enclose!
                int leftHeight = heights.get(left);

                int rightHeight = heights.get(right);
                out.printf("%d -- %d\n", leftHeight, rightHeight);
                int minHeight = Math.min(leftHeight, rightHeight);

                for (int i = left + 1; i < right; i++) {
                    water += minHeight - heights.get(i);
                }
                left = right;
                right = left + 1;

            }
            System.out.println(water);
        }
    }
}