import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Riley {
    
    public static class Acre {
        double acreage;
        ArrayList<Acre> neighbors;
        int i;
        int j;

        public Acre(int i, int j, int perc) {
            this.acreage = 25.0 * perc / 100;
            neighbors = new ArrayList<Acre>();
            this.i = i;
            this.j = j;
        }

        public void getNeighbors(Acre[][] grid) {
            if (i > 0 && grid[i - 1][j].acreage != 0) {
                neighbors.add(grid[i - 1][j]);
            }
            if (i < grid.length - 1 && grid[i + 1][j].acreage != 0) {
                neighbors.add(grid[i + 1][j]);
            }
            if (j > 0 && grid[i][j - 1].acreage != 0) {
                neighbors.add(grid[i][j - 1]);
            }
            if (j < grid[i].length - 1 && grid[i][j + 1].acreage != 0) {
                neighbors.add(grid[i][j + 1]);
            }
            if (i > 0 && j > 0 && grid[i - 1][j - 1].acreage != 0) {
                neighbors.add(grid[i - 1][j - 1]);
            }
            if (i > 0 && j < grid[i].length - 1 && grid[i - 1][j + 1].acreage != 0) {
                neighbors.add(grid[i - 1][j + 1]);
            }
            if (i < grid.length - 1 && j > 0 && grid[i + 1][j - 1].acreage != 0) {
                neighbors.add(grid[i + 1][j - 1]);
            }
            if (i < grid.length - 1 && j < grid[i].length - 1 && grid[i + 1][j + 1].acreage != 0) {
                neighbors.add(grid[i + 1][j + 1]);
            }
        }

        public String toString() {
            return("(" + i + ", " + j + ") : " + acreage);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("riley.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            int r = in.nextInt();
            int c = in.nextInt();
            HashMap<Integer, HashSet<Acre>> data = new HashMap<Integer, HashSet<Acre>>();
            Acre[][] acres = new Acre[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    acres[i][j] = new Acre(i, j, in.nextInt());
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    acres[i][j].getNeighbors(acres);
                }
            }
            double total = 0.0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    total += acres[i][j].acreage;
                }
            }
            ArrayList<Acre> openSet = new ArrayList<Acre>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (acres[i][j].acreage > 0)
                        openSet.add(acres[i][j]);
                }
            }
            int num = 0;
            while (openSet.size() != 0) {
                HashSet<Acre> closedSet = new HashSet<Acre>();
                ArrayList<Acre> toCheck = new ArrayList<Acre>();
                Acre temp = openSet.get(0);
                toCheck.add(temp);
                closedSet.add(temp);
                while (toCheck.size() != 0) {
                    for(Acre a : temp.neighbors) {
                        if(!toCheck.contains(a) && !closedSet.contains(a)) {
                            toCheck.add(a);
                        }
                    }
                    openSet.remove(temp);
                    toCheck.remove(temp);
                    closedSet.add(temp);
                    try {
                        temp = toCheck.get(0);
                    }catch(Exception e) {}
                }
                data.put(num, closedSet);
                num++;
            }
            ArrayList<Double> output = new ArrayList<Double>();
            for(HashSet<Acre> a : data.values()) {
                double x = 0;
                for(Acre b : a) {
                    x += b.acreage;
                }
                output.add(x);
            }
            Collections.sort(output);
            Collections.reverse(output);
            out.printf("%.2f acres\n", Math.round(total * 100) / 100.0);
            for(double a : output) {
                out.printf("%.2f acres\n", Math.round(a * 100) / 100.0);
            }
            out.println("============");
        }
    }
}
