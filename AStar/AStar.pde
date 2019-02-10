int rows = 50;
int cols = 50;
Spot[][] grid;
ArrayList<Spot> closedSet;
ArrayList<Spot> openSet;
Spot start;
Spot end;
Spot current;
ArrayList<Spot> path;
boolean done;

int heuristic(Spot a, Spot b) {
  // Manhattan distance
  return Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
}
void setup() {
  size(400, 400);
  done = false;
  openSet = new ArrayList<Spot>();
  closedSet = new ArrayList<Spot>();
  path = new ArrayList<Spot>();
  grid = new Spot[rows][cols];

  // Create grid of spots
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      grid[i][j] = new Spot(i, j, width/rows, width/cols);
    }
  }
  // Create get neigboards
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      grid[i][j].getNeighbors(grid);
      //println("neighbors added" + i + " " + j);
    }
  }
  // draw grid
  for (int i=0; i<grid.length; i++) {
    for (int j=0; j<grid[i].length; j++) {
      grid[i][j].drawSpot(color(255, 255, 255));
    }
  }

  start = grid[0][0];
  end = grid[rows-1][cols-1];
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
      if (closedSet.contains(neighbor)) {
        continue;
      }

      int tempG = current.g + 1;

      if (!openSet.contains(neighbor) && !neighbor.wall) {
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
      
      println(path.size());
    }
  }
}
