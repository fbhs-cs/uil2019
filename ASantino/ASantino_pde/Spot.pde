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
  int direction;

  public Spot(int i, int j, int direction, int spotWidth, int spotHeight) {
    this.i = i;
    this.j = j;
    this.f = 0;
    this.g = 0;
    this.h = 0;
    this.direction = direction;
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
    int x = this.i;
    int y = this.j;
    switch(this.direction) {
    case 0:
      while (y-1 > 0) {
        this.neighbors.add(grid[y-1][x]);
        y--;
      }
      break;
    case 1:
      while (y-1>0 && x+1 < grid[y].length) {
        this.neighbors.add(grid[y-1][x+1]);
        y--;
        x++;
      }
      break;
    case 2:
      while (x+1 < grid[y].length) {
        this.neighbors.add(grid[y][x+1]);
        x++;
      }
      break;
    case 3:
      while (y+1<grid.length && x+1 < grid[y+1].length) {
        this.neighbors.add(grid[y+1][x+1]);
        x++;
        y++;
      }
      break;
    case 4:
      while (y+1<grid.length) {
        this.neighbors.add(grid[y+1][x]);
        y++;
      }
      break;
    case 5:
      while (y+1<grid.length && x-1>0) {
        this.neighbors.add(grid[y+1][x-1]);
        y++;
        x--;
      }
      break;
    case 6:
      while (x-1 > 0) {
        this.neighbors.add(grid[y][x-1]);
        x--;
      }
      break;
    case 7:
      while (x-1>0 && y-1 >0) {
        this.neighbors.add(grid[y-1][x-1]);
        x--;
        y--;
      }
      break;
    }
  }
  void drawSpot(color col) {

    fill(col);
    if (this.wall) {
      fill(color(0, 0, 0));
    }
    rect(this.i*this.spotWidth, this.j*this.spotHeight, this.spotWidth, this.spotHeight);
  }
}
