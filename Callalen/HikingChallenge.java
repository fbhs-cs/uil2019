import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class HikingChallenge {
    public static int heuristic(Node a, Node b) {
        return (Math.abs(a.i-b.i) + Math.abs(a.j-b.j));
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

            // use split...your i and j represent ROWS then COLUMNS...the input is COLUMNS then ROWS
            Node start = grid[Integer.parseInt(first.substring(first.indexOf(",")+1, first.indexOf(")")))][Integer.parseInt(first.substring(2, first.indexOf(",")))];
            Node end = grid[Integer.parseInt(first.substring(first.lastIndexOf(",")+1, first.lastIndexOf(")")))][Integer.parseInt(first.substring(first.lastIndexOf("(")+1, first.lastIndexOf(",")))];
            start.g = start.h; // count the cost to go through the starting node
            start.f = heuristic(start, end);
            openSet.add(start);
            Node current;

            do {
                current = openSet.get(0);
                for(int i = 1; i < openSet.size(); i++) {
                    if(openSet.get(i).f < current.f) {
                        current = openSet.get(i);
                    }
                }

                openSet.remove(current);
                closedSet.add(current);

                

                for(Node neighbor : current.neighbors) {
                    if(closedSet.contains(neighbor)) {
                        continue;
                    }
                    // calculate the potential cost to get through the neighbor node
                    int tempG = current.g + neighbor.h;
                    if(!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }else if(tempG > neighbor.g) {
                        continue;
                    }
                    neighbor.prev = current;
                    neighbor.g = tempG;
                    // approximate cost to get through neighbor
                    neighbor.f = neighbor.g + heuristic(neighbor, end);
                }
            }while(current != end);


            path = new ArrayList<Node>();
                Node temp = current;
                path.add(temp);
                while(temp.prev != null) {
                    path.add(temp.prev);
                    temp = temp.prev;
                }
            for(int i = 0; i < grid.length; i++) {
                String line  = "";
                for(int j = 0; j < grid[i].length; j++) {
                    line = String.join(" ", line, grid[i][j].f+"");
                }
                out.println(line);
            }
            int time = 0;
            for(int i = 1; i < path.size(); i++) {
                time += path.get(i).h;
                out.println(path.get(i));
            }
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
        switch(type) {
            case 'T': this.h = 3; break;
            case 'W': this.h = 8; break;
            case 'O': this.h = 12; break;
            case 'S': this.h = 20; break;
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
        return(i + ", " + j);
    }
}