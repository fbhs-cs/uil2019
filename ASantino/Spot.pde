class Spot {
  int i;
  int j;
  int f;
  int g;
  int h;
  int dir;
  int spotWidth;
  int spotHeight;
  ArrayList<Spot> neighbors;
  Spot prev;
  boolean wall = false;
  // directions:
  // N=0,NE=1,E=2,SE=3,S=4,SW=5,W=6,NW=7
  public Spot(int i, int j, int dir, int spotWidth, int spotHeight) {
    this.i = i;
    this.j = j;
    this.f = 0;
    this.g = 0;
    this.h = 0;
    this.dir = dir;
    this.spotWidth = spotWidth;
    this.spotHeight = spotHeight;
    this.neighbors = new ArrayList<Spot>();
    this.prev = null;
    if (Math.random()<0.2) {
      this.wall = true;
    }
  }

  void getNeighbors(Spot[][] grid) {
    switch(direction) {
    case 0: 
      int y = j-1;
      while (y>0) {
        neighbors.add(grid[i][y]);
        y--;
      } 
      break;
    case 1:
      int x = 
        if (this.i > 0) {
        neighbors.add(grid[i-1][j]);
      }
      if (this.j > 0) {
        neighbors.add(grid[i][j-1]);
      }
      if (this.i < grid.length-1) {
        neighbors.add(grid[i+1][j]);
      }
      if (this.j < grid[i].length-1) {
        neighbors.add(grid[i][j+1]);
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
