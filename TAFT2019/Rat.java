import java.util.*;
import java.io.*;

public class Rat {

    public static class Node {
        boolean open;
        ArrayList<Node> neighbors;
        boolean wall;
        boolean start;
        boolean trap;
        boolean end;
        int gPath;
        int gTime;
        int h;
        int fPath;
        int fTime;
        int i;
        int j;
        int k;
        Node prev;
        char val;

        public Node(int i, int j, int k, char val) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.val = val;
            if (val == '#') {
                wall = true;
            }

            if (val == 'S') {
                start = true;
            }

            if (val == 'T') {
                trap = true;
            }

            if (val == '.') {
                open = true;
            }

            if (val == 'E') {
                end = true;
            }
            neighbors = new ArrayList<Node>();
        }

        public void getNeighbors(Node[][][] grid) {
            try {
                // System.out.println(grid[i][j][k].val);
                if (i > 0) {
                    neighbors.add(grid[i - 1][j][k]);
                }

                if (i < grid.length - 1) {
                    neighbors.add(grid[i + 1][j][k]);
                }

                if (j > 0) {
                    neighbors.add(grid[i][j - 1][k]);
                }

                if (j < grid[i].length - 1) {
                    neighbors.add(grid[i][j + 1][k]);
                }

                if (k > 0) {
                    neighbors.add(grid[i][j][k - 1]);
                }

                if (k < grid[i][j].length - 1) {
                    neighbors.add(grid[i][j][k + 1]);
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(i + " " + j + " " + k);
            }

        }
    }

    public static int heuristicPath(Node a, Node b) {
        return Math.abs(a.i - b.i) + Math.abs(a.j - b.j) + Math.abs(a.k - b.k);
    }

    public static int heuristicTime(Node a, Node b) {
        return 2 * Math.abs(a.i - b.i) + Math.abs(a.j - b.j) + Math.abs(a.k - b.k);
    }

    public static void findShortestPath(Node[][][] grid, Node start, Node end) {
        boolean pathDone = false;
        boolean timeDone = false;
        ArrayList<Node> openSet = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();
        openSet.add(start);
        start.fTime = heuristicTime(start, end);
        start.fPath = heuristicPath(start, end);
        start.gPath = 1;
        start.gTime = 0;
        Node current = null;

        while (!openSet.isEmpty()) {
            current = openSet.remove(0);
            closedSet.add(current);

            int lowest = current.fPath;
            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).fPath < lowest) {
                    current = openSet.get(i);
                    lowest = current.fPath;

                }
            }

            if (current == end) {
                // System.out.println("PATH DONE");
                pathDone = true;
                break;
            }
            // System.out.println(current.neighbors);
            for (Node neighbor : current.neighbors) {

                if (neighbor.wall || closedSet.contains(neighbor)) {
                    continue;
                }

                int tempG = current.gPath + 1;
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tempG >= neighbor.gPath) {
                    continue;
                }
                neighbor.prev = current;
                neighbor.gPath = tempG;
                neighbor.fPath = neighbor.gPath + heuristicPath(neighbor, end);

            }

        }

        if (pathDone) {
            ArrayList<Node> path = new ArrayList<Node>();
            Node temp = current;
            while (temp.prev != null) {
                path.add(temp.prev);
                temp = temp.prev;
            }
            System.out.printf("Shortest Path: %d spaces\n", path.size());
        }

    }

    public static void findShortestTime(Node[][][] grid, Node start, Node end) {

        boolean timeDone = false;
        ArrayList<Node> openSet = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();
        openSet.add(start);
        start.fTime = heuristicTime(start, end);
        start.gTime = 1;
        Node current = null;

        while (!openSet.isEmpty()) {
            current = openSet.remove(0);
            closedSet.add(current);

            int lowest = current.fTime;
            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).fTime < lowest) {
                    current = openSet.get(i);
                    lowest = current.fTime;

                }
            }

            if (current == end) {
                // System.out.println("PATH DONE");
                timeDone = true;
                break;
            }
            // System.out.println(current.neighbors);
            for (Node neighbor : current.neighbors) {

                if (neighbor.wall || closedSet.contains(neighbor)) {
                    continue;
                }
                boolean diffLvl = neighbor.i != current.i;
                int tempG = 0;
                if (neighbor.trap && diffLvl) {
                    tempG = current.gTime + 6;
                } else if (diffLvl) {
                    tempG = current.gTime + 2;
                } else if (neighbor.trap) {
                    tempG = current.gTime + 3;
                } else {
                    tempG = current.gTime + 1;
                }

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tempG >= neighbor.gTime) {
                    continue;
                }
                neighbor.prev = current;
                neighbor.gTime = tempG;
                neighbor.fTime = neighbor.gTime + heuristicPath(neighbor, end);

            }

        }

        if (timeDone) {
            // ArrayList<Node> path = new ArrayList<Node>();
            // path.add(current);
            Node temp = current;
            int time = 0;
            while (temp.prev != null) {
                boolean diffLvl = temp.i != temp.prev.i;
                // path.add(temp.prev);

                if (temp.prev.trap && diffLvl)
                    time += 6;
                else if (diffLvl)
                    time += 2;
                else if (temp.prev.trap)
                    time += 3;
                else {
                    time += 1;
                }

                temp = temp.prev;
            }
            System.out.printf("Quickest Path: %d seconds\n", time);
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("rat.dat"));
        int n = in.nextInt();
        while (n-- > 0) {

            int l = in.nextInt();
            int r = in.nextInt();
            int c = in.nextInt();
            Node[][][] grid = new Node[l][r][c];
            in.nextLine();

            // make grid
            ArrayList<String> rows = new ArrayList<String>();
            for (int i = 0; i < l; i++) {

                for (int j = 0; j < r; j++) {
                    String row = in.nextLine();
                    // System.out.println(row);
                    for (int k = 0; k < row.length(); k++)
                        grid[i][j][k] = new Node(i, j, k, row.charAt(k));
                }
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    for (int k = 0; k < grid[i][j].length; k++) {
                        // System.out.print(grid[i][j][k].val);
                        grid[i][j][k].getNeighbors(grid);
                    }

                }

            }

            Node start = null;
            Node end = null;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    for (int k = 0; k < grid[i][j].length; k++) {
                        if (grid[i][j][k].start) {
                            start = grid[i][j][k];
                        }

                        if (grid[i][j][k].end) {
                            end = grid[i][j][k];
                        }
                    }

                }
            }

            if (start == null) {
                System.out.println("No start point?");
                return;
            } else if (end == null) {
                System.out.println("No end point?");
                return;
            }

            findShortestPath(grid, start, end);
            findShortestTime(grid, start, end);
        }

    }
}