package aoc2021.own.objects;

import java.util.Objects;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void fold(char axis, int pos) {
		if (axis == 'x' && x > pos) {
			x = 2 * pos - x;
		} else if (axis == 'y' && y > pos)
			y = 2 * pos - y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Point point = (Point) o;
		return x == point.x && y == point.y;
	}
}