import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Santino {
  private static class Spot {
    int i;
    int j;
    int f;
    int g;
    int h;
    ArrayList<Spot> neighbors;
    String dir;
    Spot prev;

    private Spot(int i, int j, String dir) {
      this.i = i;
      this.j = j;
      this.f = 0;
      this.g = 0;
      this.h = 0;
      neighbors = new ArrayList<Spot>();
      this.dir = dir;
      this.prev = null;
    }

    private void getNeighbors(Spot[][] grid) {
      int x = this.j;
      int y = this.i;
      switch (this.dir) {
      case "N":
        while (y - 1 >= 0) {
          this.neighbors.add(grid[y - 1][x]);
          y--;
        }
        break;
      case "NE":
        while (y - 1 >= 0 && x + 1 < grid[y].length) {
          this.neighbors.add(grid[y - 1][x + 1]);
          y--;
          x++;
        }
        break;
      case "E":
        while (x + 1 < grid[y].length) {
          this.neighbors.add(grid[y][x + 1]);
          x++;
        }
        break;
      case "SE":
        while (y + 1 < grid.length && x + 1 < grid[y + 1].length) {
          this.neighbors.add(grid[y + 1][x + 1]);
          x++;
          y++;
        }
        break;
      case "S":
        while (y + 1 < grid.length) {
          this.neighbors.add(grid[y + 1][x]);
          y++;
        }
        break;
      case "SW":
        while (y + 1 < grid.length && x - 1 >= 0) {
          this.neighbors.add(grid[y + 1][x - 1]);
          y++;
          x--;
        }
        break;
      case "W":
        while (x - 1 >= 0) {
          this.neighbors.add(grid[y][x - 1]);
          x--;
        }
        break;
      case "NW":
        while (x - 1 >= 0 && y - 1 >= 0) {
          this.neighbors.add(grid[y - 1][x - 1]);
          x--;
          y--;
        }
        break;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(new File("santino.dat"));
    int num = in.nextInt();
    int count = 0;
    while (num-- > 0) {
      count++;
      boolean done = false;
      int r = in.nextInt();
      int c = in.nextInt();
      int startY = in.nextInt();
      int startX = in.nextInt();
      int endY = in.nextInt();
      int endX = in.nextInt();

      ArrayList<Spot> openSet = new ArrayList<Spot>();
      ArrayList<Spot> closedSet = new ArrayList<Spot>();

      Spot[][] grid = new Spot[r][c];
      ArrayList<Spot> path = new ArrayList<Spot>();

      // fill in grid
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          String n = in.next();
          grid[i][j] = new Spot(i, j, n);
        }
      }

      // fill in neighbors
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          grid[i][j].getNeighbors(grid);
        }
      }

      Spot start = grid[startY][startX];
      Spot end = grid[endY][endX];

      start.g = 0;
      start.f = 1;

      openSet.add(start);
      Spot current = null;
      // main algorithm
      while (openSet.size() > 0) {
        current = openSet.get(0);
        int lowest = current.f;
        for (int i = 0; i < openSet.size(); i++) {
          if (openSet.get(i).f < lowest) {
            current = openSet.get(i);
            lowest = current.f;
          }
        }

        if (current == end) {
          // System.out.println("DONE!");
          done = true;
          break;
        }

        closedSet.add(current);
        openSet.remove(current);

        for (Spot neighbor : current.neighbors) {

          if (closedSet.contains(neighbor)) {
            continue;
          }

          int tempG = current.g + 1;

          if (!openSet.contains(neighbor)) {
            openSet.add(neighbor);
          } else if (tempG >= neighbor.g) {
            continue;
          }

          neighbor.prev = current;
          neighbor.g = tempG;
          neighbor.f = neighbor.g + 1; // not bothering with heuristic!
        }

      }

      if (done) {
        Spot temp = current;
        path.add(temp);
        while (temp.prev != null) {
          path.add(temp.prev);
          temp = temp.prev;
        }
        System.out.printf("Case #%d: %d\n", count, path.size() - 1);
      } else {
        System.out.printf("Case #%d: Lost in the playround\n", count);
      }
    }
  }

}
