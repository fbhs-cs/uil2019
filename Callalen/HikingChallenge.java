import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class HikingChallenge {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("hiking_challenge.dat"));
        while (in.hasNextLine()) {
            String first = in.nextLine();
            Node[][] grid = new Node[5][5];
            ArrayList<Node> openSet = new ArrayList<Node>();
            ArrayList<Node> closedSet = new ArrayList<Node>();
            ArrayList<Node> path;

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

            Node start = grid[Integer.parseInt(first.substring(first.indexOf(",") + 1, first.indexOf(")")))][Integer.parseInt(first.substring(2, first.indexOf(",")))];
            Node end = grid[Integer.parseInt(first.substring(first.lastIndexOf(",") + 1, first.lastIndexOf(")")))][Integer.parseInt(first.substring(first.lastIndexOf("(") + 1, first.lastIndexOf(",")))];
            start.f = heuristic(start, end) + start.h;
            start.g = 0;
            openSet.add(start);
            Node current;

            do {
                current = openSet.get(0);
                int lowest = current.f;
                for (int i = 1; i < openSet.size(); i++) {
                    if (openSet.get(i).f < lowest) {
                        current = openSet.get(i);
                        lowest = current.f;
                    }
                }

                closedSet.add(current);
                openSet.remove(current);

                for (Node neighbor : current.neighbors) {
                    if (closedSet.contains(neighbor)) {
                        continue;
                    }

                    int tempG = current.g + neighbor.h + 1;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else if (tempG >= neighbor.g) {
                        continue;
                    }
                    neighbor.prev = current;
                    neighbor.g = tempG;
                    neighbor.f = neighbor.g + heuristic(neighbor, end);
                }

                path = new ArrayList<Node>();
                Node temp = current;
                path.add(temp);
                while (temp.prev != null) {
                    path.add(temp.prev);
                    temp = temp.prev;
                }
            }while(current != end);

            int time = 0;
            for (int i = 1; i < path.size(); i++) {
                time += path.get(i).h;
            }
            out.printf("Your time to beat is %d minutes.\n", time);
        }
    }

    public static int heuristic(Node a, Node b) {
        return (Math.abs(a.i - b.i) + Math.abs(a.j - b.j));
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
        case 'T': this.h = 3; break;
        case 'W': this.h = 8; break;
        case 'O': this.h = 12; break;
        default: this.h = 20; break;
        }
        this.neighbors = new ArrayList<Node>();
    }

    public void getNeighbors(Node[][] grid) {
        if (this.i > 0) {
            neighbors.add(grid[i - 1][j]);
        }
        if (this.j > 0) {
            neighbors.add(grid[i][j - 1]);
        }
        if (this.i < grid.length - 1) {
            neighbors.add(grid[i + 1][j]);
        }
        if (this.j < grid[i].length - 1) {
            neighbors.add(grid[i][j + 1]);
        }
    }
}
