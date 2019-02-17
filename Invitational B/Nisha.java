import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Nisha {

    public static BigInteger fact(int n) {
        BigInteger ans = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            ans = ans.multiply(BigInteger.valueOf(i));
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("nisha_judge.dat"));
        int n = in.nextInt();
        int caseNum = 1;
        while (n-- > 0) {

            int k = in.nextInt();

            BigInteger countOnes = BigInteger.ZERO;
            for (int digits = 0; digits < k; digits++) {
                int numOnes = 2;
                for (int ones = 1; ones <= digits; ones++) {
                    countOnes = countOnes.add(BigInteger.valueOf(numOnes).multiply(fact(digits).divide(fact(ones).multiply(fact(digits - ones)))));
                    numOnes++;
                }

                countOnes = countOnes.add(BigInteger.ONE);
            }
            countOnes = countOnes.add(BigInteger.ONE);
            System.out.printf("Case %d: %d\n", caseNum, countOnes);

            caseNum++;
        }

    }
}