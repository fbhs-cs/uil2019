import java.util.*;

LinkedList<Spot> openSet;
HashSet<Spot> closedSet;
Spot[][] grid;
int sr;
int sc;
int rows;
int cols;
Spot start;
Spot end;
ArrayList<Spot> path;

void setup() {
  size(400, 400);
  rows = 25;
  cols = 25;
  grid = new Spot[rows][cols];
  path = new ArrayList<Spot>();
  int spotWidth = width/rows;
  int spotHeight = height/cols;
  for (int i=0; i<rows; i++) {
    for (int j =0; j<cols; j++) {
      grid[i][j] = new Spot(i, j, spotWidth, spotHeight);
    }
  }
  start = grid[sr][sc];
  end = grid[rows-1][cols-1];
  start.wall = false;
  end.wall = false;
  
  for (int i=0; i<rows; i++) {
    for (int j=0; j<cols; j++) {
      grid[i][j].getNeighbors(grid);
    }
  }

  for (int i=0; i<rows; i++) {
    for (int j=0; j<cols; j++) {
      grid[i][j].show(color(255));
    }
  }

  openSet = new LinkedList<Spot>();
  closedSet = new HashSet<Spot>();

  
  openSet.add(start);
}

void draw() {

  if (openSet.size()==0) {
    println("no solution");
    noLoop();
    return;
  }

  Spot current = openSet.remove();
  if (current == end) {
    println("DONE!");
    Spot temp = current;
    path.add(temp);
    while(current.prev != null) {
      path.add(current.prev);
      current = current.prev;
      
    }
    
    for(Spot s:path) {
      s.show(color(0,0,255));
    }
    
    
    noLoop();
    println(path.size());
    return;
  }
  if (!closedSet.contains(current) && !current.wall) {
    closedSet.add(current);
    for (Spot neighbor : current.neighbors) {
      if (!neighbor.wall) {
        neighbor.prev = current;
        neighbor.neighbors.remove(current);
        openSet.add(neighbor);
      }
    }
  }

  
  
  for (Spot s : openSet) {
    s.show(color(0, 255, 0));
  }
  for (Spot s : closedSet) {
    s.show(color(255, 0, 0));
  }
  
}
