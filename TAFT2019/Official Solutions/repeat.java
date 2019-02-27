import java.io.*;
import java.util.*;

public class repeat {

    public static class SuffixArray {
        ArrayList<String> array;
        int[] lengths;

        public SuffixArray(String str) {

            array = new ArrayList<String>();
            for (int i = 0; i < str.length(); i++) {
                // System.out.println(str.substring(i));
                array.add(str.substring(i));
            }
            Collections.sort(array);
            lengths = new int[array.size()];
        }

        public String lrs() {
            String longest = "";
            for (int i = 0; i < this.array.size() - 1; i++) {
                String x = lcp(array.get(i), array.get(i + 1));
                if (x.length() > longest.length()) {
                    longest = x;
                }
            }
            return longest;
        }

    }

    public static String lcp(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int l = 0;
        for (int i = 1; i < n; i++) {
            if (a.substring(0, i).equals(b.substring(0, i)))
                l++;
        }
        return a.substring(0, l);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("repeat.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            String str = in.next();
            // System.out.println(str);
            SuffixArray array = new SuffixArray(str);
            String ans = array.lrs();
            System.out.printf("%d %s\n", ans.length(), ans);
        }
    }

}