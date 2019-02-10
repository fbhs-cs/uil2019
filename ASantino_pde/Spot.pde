class Spot {
  int i;
  int j;
  int f;
  int g;
  int h;
  int spotWidth;
  int spotHeight;
  ArrayList<Spot> neighbors;
  Spot prev;
  boolean wall = false;
  String dir;
  int direction;

  public Spot(int i, int j, String dir, int spotWidth, int spotHeight) {
    this.i = i;
    this.j = j;
    this.f = 0;
    this.g = 0;
    this.h = 0;
    this.dir = dir;
    switch(dir) {
     case "N": this.direction = 0; break;
     case "NE": this.direction = 1; break;
     case "E": this.direction = 2; break;
     case "SE": this.direction = 3; break;
     case "S": this.direction = 4; break;
     case "SW": this.direction = 5; break;
     case "W": this.direction = 6; break;
     case "NW": this.direction = 7; break;
    }
    this.spotWidth = spotWidth;
    this.spotHeight = spotHeight;
    this.neighbors = new ArrayList<Spot>();
    this.prev = null;
    if (Math.random()<0.2) {
      this.wall = true;
    }
  }

  // directions:
  // 0=N,1=NE,2=E,3=SE,4=S,5=SW,6=W,7=NW
  void getNeighbors(Spot[][] grid) {
    int x = this.j;
    int y = this.i;
    switch(this.dir) {
    case "N":
      while (y-1 > 0) {
        this.neighbors.add(grid[y-1][x]);
        y--;
      }
      break;
    case "NE":
      while (y-1>0 && x+1 < grid[y].length) {
        this.neighbors.add(grid[y-1][x+1]);
        y--;
        x++;
      }
      break;
    case "E":
      while (x+1 < grid[y].length) {
        this.neighbors.add(grid[y][x+1]);
        x++;
      }
      break;
    case "SE":
      while (y+1<grid.length && x+1 < grid[y+1].length) {
        this.neighbors.add(grid[y+1][x+1]);
        x++;
        y++;
      }
      break;
    case "S":
      while (y+1<grid.length) {
        this.neighbors.add(grid[y+1][x]);
        y++;
      }
      break;
    case "SW":
      while (y+1<grid.length && x-1>0) {
        this.neighbors.add(grid[y+1][x-1]);
        y++;
        x--;
      }
      break;
    case "W":
      while (x-1 > 0) {
        this.neighbors.add(grid[y][x-1]);
        x--;
      }
      break;
    case "NW":
      while (x-1>0 && y-1 >0) {
        this.neighbors.add(grid[y-1][x-1]);
        x--;
        y--;
      }
      break;
    }
  }
  void drawSpot(color col) {

    
    textAlign(CENTER);
    noFill();
    rect(this.i*this.spotWidth, this.j*this.spotHeight, this.spotWidth, this.spotHeight);
    fill(col);
    text(this.dir,this.j*this.spotWidth+this.spotWidth/2,this.i*this.spotHeight+this.spotHeight/2);
    
  }
}
