import java.util.Scanner;

int rows;
int cols;
int startX;
int startY;
int endX;
int endY;
Spot[][] grid;
ArrayList<Spot> closedSet;
ArrayList<Spot> openSet;
Spot start;
Spot end;
Spot current;
ArrayList<Spot> path;
boolean done;
String map;
int heuristic(Spot a, Spot b) {
  // Manhattan distance
  //return Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
  return 2;
}
void setup() {
  size(400, 400);

  /********************* CASE #1 ***********************/
  rows = 4;
  cols = 4;
  map = "E SW SW W\nN S NE SW\nNW SE SE N\nNE NW E SW";
  startX = 0;
  startY = 0;
  endX = 3;
  endY = 3;
  /*****************************************************/
  /********************* CASE #2 ************************
   rows = 2;
   cols = 3;
   map = "S E S\nW N NE";
   startX = 0;
   startY = 1;
   endX = 1;
   endY = 0;
   ******************************************************/
  /********************* CASE #3 ************************
   rows = 4;
   cols = 3;
   map = "N NE S\nSW E NW\nW E SE\nS E N";
   startX = 2;
   startY = 1;
   endX = 2;
   endY = 1;
   ******************************************************/
   
   
   
  done = false;
  openSet = new ArrayList<Spot>();
  closedSet = new ArrayList<Spot>();
  path = new ArrayList<Spot>();
  grid = new Spot[rows][cols];

  Scanner in = new Scanner(map);
  // Create grid of spots
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      String n = in.next();
      println(n);
      grid[i][j] = new Spot(i, j, n, width/rows, width/cols);
    }
  }
  // Create get neigboards
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      grid[i][j].getNeighbors(grid);
    }
  }

  // print neighbors
  for (int i = 0; i < grid.length; i++) {
    for (int j=0; j< grid[i].length; j++) {
      println("Box: " + j + ", " + i);
      for (Spot n : grid[i][j].neighbors) {
        println(n.j + ", " + n.i);
      }
    }
  }
  // draw grid
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      grid[i][j].drawSpot(color(255, 255, 255));
    }
  }

  start = grid[startY][startX];
  end = grid[endY][endX];
  start.wall = false;
  end.wall = false;
  start.g = 0;
  start.f = heuristic(start, end);

  openSet.add(start);
}


void draw() {
  if (openSet.size() == 0) {
    println("no solution");
    noLoop();
    return;
  } else {

    // find lowest fscore in openSet
    current = openSet.get(0);
    int lowest = current.f;
    for (int i=1; i<openSet.size(); i++) {
      if (openSet.get(i).f < lowest) {
        current = openSet.get(i);
        lowest = current.f;
      }
    }

    if (current == end) {
      println("DONE!");
      done = true;
      noLoop();


      //return;
    }
    closedSet.add(current);
    openSet.remove(current);

    path = new ArrayList<Spot>();
    Spot temp = current;
    path.add(temp);
    while (temp.prev != null) {
      path.add(temp.prev);
      temp = temp.prev;
    }




    for (Spot neighbor : current.neighbors) {
      println(current.j + ", " + current.i + ": " + neighbor.j + ", " + neighbor.i);
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
      neighbor.f = neighbor.g + heuristic(neighbor, end);
    }
    for (Spot s : openSet) {
      s.drawSpot(color(0, 255, 0));
    }

    for (Spot s : closedSet) {
      s.drawSpot(color(255, 0, 0));
    }

    for (Spot s : path) {
      s.drawSpot(color(0, 0, 255));
    }

    if (done) {
      println(path.size()-1);
    }
  }
}
