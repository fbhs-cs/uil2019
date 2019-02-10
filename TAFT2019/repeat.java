import java.io.*;
import java.util.*;

public class repeat {

    public static class SuffixArray {
        ArrayList<String> array;

        public SuffixArray(String str) {
            for (int i = 0; i < str.length(); i++) {
                array.add(str.substring(i));
            }
            Collections.sort(array);
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("repeat.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            String str = in.next();
            SuffixArray array = new SuffixArray(str);
            for (String s : array.array) {
                System.out.println(s);
            }
        }

    }
}