import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Zhenya {
	public static void main(String [] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("zhenya.dat"));
		int n = in.nextInt();
		in.nextLine();
		while(n-- > 0) {
			Scanner in2 = new Scanner(in.nextLine());
			ArrayList<Rectangle> arr = new ArrayList<Rectangle>();
			while(in2.hasNextInt()) {
				arr.add(new Rectangle(in2.nextInt(), in2.nextInt(), in2.nextInt(), in2.nextInt()));
			}
			in2.close();
			boolean intersects = true;
			for(int i = 0; i < arr.size(); i++) {
				Rectangle r = arr.get(i);
				for(int j = 0; j < arr.size(); j ++) {
					Rectangle s = arr.get(j);
					if(!r.intersects(s) && !s.intersects(r)) {
						intersects = false;
						break;
					}
				}
			}
			if(intersects) {
				out.println("ALL STACKED");
			}else {
				out.println("NOT STACKED");
			}
		}
		in.close();
	}
}

class Rectangle {
	public Point bl, br, tr, tl;
	
	public Rectangle(int a, int b, int c, int d) {
		this.bl = new Point(a, b);
		this.br = new Point(a+c, b);
		this.tr = new Point(a+c, b+d);
		this.tl = new Point(a, b+d);
	}
	
	public boolean coordinateIsRectangle(Point p) {
		return (p.x <= br.x && p.x >= bl.x && p.y >= bl.y && p.y <= tl.y);
	}
	
	public boolean horizontalEdgeGoesThroughRectangle(Point a, Point b) {
		if(a.y != b.y) {
			return false;
		}else if(b.x >= br.x && a.x <= br.x && a.y >= br.y && a.y <= tr.y) {
			return true;
		}else if(a.x <= br.x && b.x >= br.x && a.y >= br.y && a.y <= tr.y) {
			return true;
		}
		return false;
	}
	
	public boolean verticalEdgeGoesThroughRectangle(Point a, Point b) {
		if(a.x != b.x) {
			return false;
		}else if(b.y >= tr.y && a.y <= tr.y && a.x >= bl.x && a.x <= br.x) {
			return true;
		}else if(a.y <= br.y && b.y >= br.y && a.x >= bl.x && a.x <= br.x) {
			return true;
		}
		return false;
	}
	
	public boolean intersects(Rectangle r) {
		if(this.coordinateIsRectangle(r.bl) || this.coordinateIsRectangle(r.br) || this.coordinateIsRectangle(r.tr) || this.coordinateIsRectangle(r.tl)) {
			return true;
		}else if(this.verticalEdgeGoesThroughRectangle(r.br, r.tr) || this.verticalEdgeGoesThroughRectangle(r.bl, r.tl)) {
			return true;
		}else if(this.horizontalEdgeGoesThroughRectangle(r.bl, r.br) || this.horizontalEdgeGoesThroughRectangle(r.tl, r.tr)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return(br + ", " + bl + ", " + tl + ", " + tr);
	}
}

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return("(" + x + ", " + y + ")");
	}
}