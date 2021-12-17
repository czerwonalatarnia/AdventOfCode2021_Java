package aoc2021.Day17;

public class Point {
	static int xMin;
	static int xMax;
	static int yMin;
	static int yMax;
	int x = 0;
	int y = 0;
	int vX;
	int vY;
	boolean isInTarget = false;

	public Point(int vX, int vY) {
		this.vX = vX;
		this.vY = vY;
	}

	public boolean shot() {
		while (!checkIfOverBounds()) {
			x += vX;
			if (vX > 0)
				vX--;
			y += vY;
			vY--;
			checkIfInTarget();
			if (isInTarget())
				break;
		}
		return isInTarget();
	}

	private boolean checkIfOverBounds() {
		return x > xMax || y < yMin;
	}

	private void checkIfInTarget() {
		isInTarget = x >= xMin && x <= xMax && y >= yMin && y <= yMax;
	}

	public boolean isInTarget() {
		return isInTarget;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
