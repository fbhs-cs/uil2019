import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class HikingChallengeWorking {
    public static int heuristic(Node a, Node b) {
        return (Math.abs(a.i - b.i) + Math.abs(a.j - b.j));
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("hiking_challenge.dat"));
        while (in.hasNextLine()) {
            String first = in.nextLine();
            Node[][] grid = new Node[5][5];
            ArrayList<Node> openSet = new ArrayList<Node>();
            ArrayList<Node> closedSet = new ArrayList<Node>();
            ArrayList<Node> path = new ArrayList<Node>();
            for (int i = 0; i < 5; i++) {
                char[] line = in.nextLine().toCharArray();
                for (int j = 0; j < 5; j++) {
                    grid[i][j] = new Node(i, j, line[j]);
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[i][j].getNeighbors(grid);
                }
            }
            String[] coords = first.split("[{(),} ]+"); // gives string array with " " at index 0!
            Node start = grid[Integer.parseInt(coords[2])][Integer.parseInt(coords[1])];
            Node end = grid[Integer.parseInt(coords[4])][Integer.parseInt(coords[3])];
            start.g = start.h;
            start.f = heuristic(start, end);
            openSet.add(start);
            Node current = start;

            while(current != end) {
                current = openSet.get(0);
                for (int i = 1; i < openSet.size(); i++) {
                    if (openSet.get(i).f < current.f) {
                        current = openSet.get(i);
                    }
                }

                openSet.remove(current);
                closedSet.add(current);

                for (Node neighbor : current.neighbors) {
                    if (closedSet.contains(neighbor)) {
                        continue;
                    }
                    // tempG calculation was incorrect
                    int tempG = current.g + neighbor.h;
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else if (tempG > neighbor.g) {
                        continue;
                    }
                    neighbor.prev = current;
                    neighbor.g = tempG;
                    neighbor.f = neighbor.g + heuristic(neighbor, end);
                }
            } 

          

            
            
            // current nodes g value is storing the cost to get to that node!
            out.printf("Your time to beat is %d minutes.\n", current.g-current.h);
        }
    }
}

class Node {
    char type;
    int i, j, f, g, h;
    ArrayList<Node> neighbors;
    Node prev;

    public Node(int i, int j, char type) {
        this.i = i;
        this.j = j;
        this.type = type;
        switch (type) {
        case 'T':
            this.h = 3;
            break;
        case 'W':
            this.h = 8;
            break;
        case 'O':
            this.h = 12;
            break;
        case 'S':
            this.h = 20;
            break;
        }
        neighbors = new ArrayList<Node>();
    }

    public void getNeighbors(Node[][] grid) {
        if (i > 0) {
            neighbors.add(grid[i - 1][j]);
        }
        if (j > 0) {
            neighbors.add(grid[i][j - 1]);
        }
        if (i < grid.length - 1) {
            neighbors.add(grid[i + 1][j]);
        }
        if (j < grid[i].length - 1) {
            neighbors.add(grid[i][j + 1]);
        }
    }

    public String toString() {
        return (i + ", " + j);
    }
}