import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Riley {

    public static class Cluster implements Comparable<Cluster> {
        ArrayList<Acre> acres;

        public Cluster() {
            acres = new ArrayList<Acre>();
        }

        public double getAcreage() {
            double acreage = 0.0;
            for (Acre a : acres) {
                acreage += a.acreage;
            }
            //out.println(acreage);
            return acreage;
        }

        public boolean contains(Acre a) {
            return acres.contains(a);
        }

        public void add(Acre a) {
            acres.add(a);
        }

        
        public int compareTo(Cluster other) {
            double myA = this.getAcreage();
            double oA = other.getAcreage();
            if(myA > oA) {
                return 1;
            }
            else if(myA == oA ) {
                return 0;
            }
            else {
                return -1;
            }
        }
    }

    public static class Acre {
        double acreage;
        ArrayList<Acre> neighbors;
        int i;
        int j;

        public Acre(int i, int j, int perc) {
            this.acreage = 25.0 * perc / 100.0;
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

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("riley.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            int r = in.nextInt();
            int c = in.nextInt();
            ArrayList<Cluster> clusters = new ArrayList<Cluster>();
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
            ArrayList<Acre> allAcres = new ArrayList<Acre>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (acres[i][j].acreage > 0)
                        allAcres.add(acres[i][j]);
                }
            }
            ArrayList<Acre> openSet = new ArrayList<Acre>();

            while (allAcres.size() != 0) {
                Cluster tempCluster = new Cluster();
                Acre temp = allAcres.get(0);
                openSet.add(temp);
                allAcres.remove(temp);
                tempCluster.add(temp);
                while (!openSet.isEmpty()) {
                    temp = openSet.get(0);
                    allAcres.remove(temp);
                    for (Acre a : temp.neighbors) {
                        allAcres.remove(a);
                        if (!openSet.contains(a) && !tempCluster.contains(a)) {
                            openSet.add(a);
                            
                        }

                        if (!tempCluster.contains(a)) {
                            tempCluster.add(a);
                        }

                    }
                    openSet.remove(temp);

                }
                clusters.add(tempCluster);
            }

            
            out.printf("%.2f acres\n", Math.round(total * 100.0) / 100.0);

            Collections.sort(clusters);
            Collections.reverse(clusters);
            for(Cluster clst:clusters) {
                out.printf("%.2f acres\n", Math.round(clst.getAcreage() * 100.0) / 100.0);
            }
            out.println("============");
        }

    }
}