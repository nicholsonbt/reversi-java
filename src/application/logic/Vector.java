package application.logic;

public class Vector {
	public int x, y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		this(0, 0);
	}
	
	public static Vector Add(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y);
	}
}
