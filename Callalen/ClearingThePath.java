import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class ClearingThePath {
    public static int heuristic(Node a, Node b) {
        return(Math.abs(a.i - b.i) + Math.abs(a.j - b.j));
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("clearing_the_path.dat"));
        while(in.hasNextLine()) {
            String first = in.nextLine();
            int bombs = Integer.parseInt(first.substring(0, first.indexOf(":")));
            int cols = Integer.parseInt(first.substring(first.indexOf(":")+1, first.indexOf("x")));
            int rows = Integer.parseInt(first.substring(first.indexOf("x")+1));
            Node[][] grid = new Node[rows][cols];
            Node start = grid[0][0];
            Node end = grid[0][0];
            for(int i = 0; i < rows; i++) {
                char[] line = in.nextLine().toCharArray();
                for(int j = 0; j < cols; j++) {
                    grid[i][j] = new Node(i, j, line[j]);
                    if(line[j] == 'S') {
                        start = grid[i][j];
                    }else if(line[j] == 'E') {
                        end = grid[i][j];
                    }
                }
            }
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    grid[i][j].getNeighbors(grid);
                }
            }
            start.g = 0;
            start.f = heuristic(start, end);
            ArrayList<Node> openSet = new ArrayList<Node>();
            ArrayList<Node> closedSet = new ArrayList<Node>();
            ArrayList<Node> path;
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
                    int tempG = current.g + 1;
                    if(!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }else if(tempG >= tempG) {
                        continue;
                    }
                    neighbor.prev = current;
                    neighbor.g = tempG;
                    neighbor.f = tempG + heuristic(neighbor, end);
                }
                path = new ArrayList<Node>();
                Node temp = current;
                path.add(temp);
                while(temp.prev != null) {
                    path.add(temp.prev);
                    temp = temp.prev;
                }
            }while(current != end);

            int needed = 0;
            for(Node n : path) {
                needed += n.h;
            }
            if(needed > bombs) {
                out.println("Delete");
            }else {
                out.println("Keep");
            }
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
        if(type == 'R') {
            this.h = 1;
        }
        this.neighbors = new ArrayList<Node>();
    }

    public void getNeighbors(Node[][] grid) {
        if(i > 0) {
            neighbors.add(grid[i-1][j]);
        }
        if(j > 0) {
            neighbors.add(grid[i][j-1]);
        }
        if(i < grid.length-1) {
            neighbors.add(grid[i+1][j]);
        }
        if(j < grid[i].length-1) {
            neighbors.add(grid[i][j+1]);
        }
    }
}
