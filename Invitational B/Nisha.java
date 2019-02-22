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

        // make an array of the number of ones less than or equal to 2^k

        long[] numOnes = new long[51]; // largest is k = 50
        long p = 1; // initial number of ones
        for (int k = 1; k <= 50; k++) {
            numOnes[k] = 2L * numOnes[k - 1] + p;
            p *= 2L;
        }

        Scanner in = new Scanner(new File("nisha_judge.dat"));
        int T = in.nextInt();
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            int k = in.nextInt();
            System.out.printf("Case %d: %d\n", caseNum, numOnes[k]+1);
        }

    }
}