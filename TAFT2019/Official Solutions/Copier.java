import java.util.*;
import java.io.*;

public class Copier {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("copier.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            int num = in.nextInt();
            System.out.printf("%d %d\n", num, num);
        }

    }
}