import java.util.*;
import java.io.*;

public class Pups {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pups.dat"));
        int n = in.nextInt();

        while (n-- > 0) {
            int d = in.nextInt();
            double f = in.nextDouble();
            double p = in.nextDouble();

            double cost = d * f * p;
            System.out.printf("$%.2f\n", cost);

        }
    }
}