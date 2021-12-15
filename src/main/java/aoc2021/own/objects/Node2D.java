package aoc2021.own.objects;

import java.util.Objects;

public class Node2D implements Comparable<Node2D> {
	private final Point point;
	private final int weight;

	public Node2D(int x, int y, int weight) {
		this.weight = weight;
		point = new Point(x, y);
	}

	public int getX() {
		return point.getX();
	}

	public int getY() {
		return point.getY();
	}

	public int getWeight() {
		return weight;
	}

	@Override public int hashCode() {
		return Objects.hash(point, weight);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Node2D node2D = (Node2D) o;
		return point.equals(node2D.point);
	}

	@Override public int compareTo(Node2D o) {
		if (weight > o.weight)
			return 1;
		else if (weight < o.weight)
			return -1;
		return 0;
	}
}
