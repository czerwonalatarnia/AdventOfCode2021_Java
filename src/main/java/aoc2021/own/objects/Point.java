package aoc2021.own.objects;

import java.util.Objects;

public class Point implements Comparable<Point> {
	private final boolean isZ;
	private int x;
	private int y;
	private int z;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.isZ = false;
	}

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.isZ = true;
	}

	public Point(int x, int y, int z, boolean isZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.isZ = isZ;
	}

	public void fold(char axis, int pos) {
		if (axis == 'x' && x > pos)
			x = 2 * pos - x;
		else if (axis == 'y' && y > pos)
			y = 2 * pos - y;
		else if (axis == 'z' && z > pos)
			z = 2 * pos - z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Point minus(Point other) {
		return new Point(x - other.x, y - other.y, z - other.z, isZ);
	}

	public Point plus(Point other) {
		return new Point(x + other.x, y + other.y, z + other.z, isZ);
	}

	@Override public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Point point = (Point) o;
		return x == point.x && y == point.y && z == point.z;
	}

	@Override public String toString() {
		if (isZ)
			return "(" + x + ", " + y + ", " + z + ")";
		else
			return "(" + x + ", " + y + ")";
	}

	@Override public int compareTo(Point o) {
		if (x == o.x && y == o.y && z == o.z)
			return 0;
		else if (x == o.x && y == o.y) {
			if (z > o.z)
				return 1;
			else
				return -1;
		} else if (x == o.x) {
			if (y > o.y)
				return 1;
			else
				return -1;
		} else {
			if (x > o.x)
				return 1;
			else
				return -1;
		}
	}
}