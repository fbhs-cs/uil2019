class Spot {
  int i;
  int j;
  int spotWidth;
  int spotHeight;
  ArrayList<Spot> neighbors;
  boolean wall;
  boolean visited;
  Spot prev;
  
  public Spot(int i, int j, int spotWidth, int spotHeight) {
    this.visited = false;
    this.i = i;
    this.j = j;
    this.spotWidth = spotWidth;
    this.spotHeight = spotHeight;
    this.neighbors = new ArrayList<Spot>();
    if(Math.random() < 0.2) {
      this.wall = true;
    }
    this.prev = null;
  }

  void show(color col) {
    fill(col);
    if(this.wall) {
      fill(0);
    }
    rect(this.j*spotWidth, this.i*spotHeight, spotWidth, spotHeight);
  }

  void getNeighbors(Spot[][] grid) {
    if (this.i > 0) {
      this.neighbors.add(grid[i-1][j]);
    }
    if (this.j > 0) {
      this.neighbors.add(grid[i][j-1]);
    }
    if (this.i < grid.length-1) {
      this.neighbors.add(grid[i+1][j]);
    }
    if (this.j < grid[i].length-1) {
      this.neighbors.add(grid[i][j+1]);
    }
  }
}
