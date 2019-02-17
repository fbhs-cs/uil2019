import java.util.*;
import java.io.*;

public class Barb {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("barb.dat"));
        int n = in.nextInt();

        System.out.println("A   A*A   3*A   A/2");
        for (int A = 1; A <= n; A++) {
            System.out.printf("%d     %d     %d     %d\n",A,A*A,3*A,A/2);

        }

    }
}