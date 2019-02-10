
import java.util.*;
import java.io.*;

public class bomb {

    public static int blowUp(char[][] map, int startI, int startJ) {
        int i = startI;
        int j = startJ;
        int numEnemies = 0;
        if (map[i][j] != '.')
            return 0;

        boolean up = true;
        boolean left = true;
        boolean right = true;
        boolean down = true;

        // check up
        while (i > 0) {
            i--;
            if (map[i][j] == '@') {
                numEnemies++;
            } else if (map[i][j] == '#')
                break;

        }

        // check down
        i = startI;
        while (i < map.length - 1) {
            i++;
            if (map[i][j] == '@') {
                numEnemies++;
            } else if (map[i][j] == '#')
                break;

        }

        // check left
        i = startI;
        while (j > 0) {
            j--;
            if (map[i][j] == '@') {
                numEnemies++;
            } else if (map[i][j] == '#')
                break;

        }

        // check right
        j = startJ;
        while (j < map[i].length - 1) {
            j++;
            if (map[i][j] == '@') {
                numEnemies++;
            } else if (map[i][j] == '#')
                break;

        }

        return numEnemies;

    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("bomb.dat"));
        int n = in.nextInt();

        while (n-- > 0) {
            int r = in.nextInt();
            int c = in.nextInt();

            char[][] map = new char[r][c];
            // build map
            for (int i = 0; i < r; i++) {
                String row = in.next();
                for (int j = 0; j < c; j++) {
                    map[i][j] = row.charAt(j);
                }
            }

            int mostEnemies = 0;
            int bestI = 0;
            int bestJ = 0;
            // test all bomb locations

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    int numEnemies = blowUp(map, i, j);
                    // System.out.printf("%d, %d: %d\n", i, j, numEnemies);
                    if (numEnemies > mostEnemies) {
                        bestI = i;
                        bestJ = j;
                        mostEnemies = numEnemies;
                    }

                }
            }
            System.out.printf("%d, %d\n", bestI, bestJ);

        }

    }
}
