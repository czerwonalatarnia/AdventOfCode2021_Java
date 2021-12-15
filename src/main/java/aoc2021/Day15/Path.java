package aoc2021.Day15;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import aoc2021.Day13.Point;

public class Path {
	private final int xPos;
	private final int yPos;
	private final int pathDanger;
	private Set<Point> visited = new HashSet<>();

	public Path(int xPos, int yPos, int pathDanger) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.pathDanger = pathDanger;
		visited.add(new Point(xPos, yPos));
	}

	public boolean isNew(int xPos, int yPos) {
		return !visited.contains(new Point(xPos, yPos));
	}

	public Path(int xPos, int yPos, int pathDanger, Path previous) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.pathDanger = pathDanger + previous.pathDanger;
		visited = new HashSet<>(previous.visited);
		visited.add(new Point(xPos, yPos));
	}

	public int getPathDanger() {
		return pathDanger;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	@Override public int hashCode() {
		return Objects.hash(xPos, yPos, pathDanger);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Path path = (Path) o;
		return xPos == path.xPos && yPos == path.yPos && pathDanger == path.pathDanger;
	}
}