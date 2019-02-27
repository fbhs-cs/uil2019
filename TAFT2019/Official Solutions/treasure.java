import java.util.*;
import java.io.*;

public class treasure {

    public static int recursiveSol(int n, int capacity, int[] w, int[] v) {
        int result = 0;
        if (n == 0 || capacity == 0) {
            result = 0;
        } else if (w[n] > capacity) {
            result = recursiveSol(n - 1, capacity, w, v);
        } else {
            int tmp1 = recursiveSol(n - 1, capacity, w, v);
            int tmp2 = v[n] + recursiveSol(n - 1, capacity - w[n], w, v);
            result = Math.max(tmp1, tmp2);
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        String pathname = "/home/chad/Dropbox/UIL/uil2019/TAFT2019/treasure.dat";
        Scanner in = new Scanner(new File(pathname));
        int n = in.nextInt();
        while (n-- > 0) {
            int numItems = in.nextInt();
            int capacity = in.nextInt();

            int[] weight = new int[numItems+1];
            int[] value = new int[numItems+1];

            for (int i = 1; i <= numItems; i++) {
                value[i] = in.nextInt();
                weight[i] = in.nextInt();
            }

            System.out.println(recursiveSol(numItems, capacity, weight, value));

        }

    }
}