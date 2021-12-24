package aoc2021.Day22;

import java.util.Arrays;
import java.util.LinkedList;

public class Bounds implements Comparable<Bounds> {
	private final boolean turn;
	private final long[] bounds;

	public Bounds(long[] bounds, boolean turn) {
		this.bounds = bounds.clone();
		if (bounds[1] < bounds[0] || bounds[3] < bounds[2] || bounds[5] < bounds[4])
			System.out.println("\t\t\t\t\t\tError in " + Arrays.toString(bounds));
		this.turn = turn;
	}

	public long volume() {
		return (bounds[1] - bounds[0] + 1L) * (bounds[3] - bounds[2] + 1L) * (bounds[5] - bounds[4] + 1L);
	}

	public long volumeSmall() {
		if (bounds[1] < -50 || bounds[0] > 50 || bounds[3] < -50 || bounds[2] > 50 || bounds[5] < -50 || bounds[4] > 50)
			return 0;
		return (Math.min(bounds[1], 50) - Math.max(bounds[0], -50) + 1L) *
		       (Math.min(bounds[3], 50) - Math.max(bounds[2], -50) + 1L) *
		       (Math.min(bounds[5], 50) - Math.max(bounds[4], -50) + 1L);
	}

	@Override public int compareTo(Bounds o) {
		if (bounds[0] < o.bounds[0])
			return -1;
		else if (bounds[0] > o.bounds[0])
			return 1;
		else {
			if (bounds[1] < o.bounds[1])
				return -1;
			else if (bounds[1] > o.bounds[1])
				return 1;
			else {
				if (bounds[2] < o.bounds[2])
					return -1;
				else if (bounds[2] > o.bounds[2])
					return 1;
				else {
					if (bounds[3] < o.bounds[3])
						return -1;
					else if (bounds[3] > o.bounds[3])
						return 1;
					else {
						if (bounds[4] < o.bounds[4])
							return -1;
						else if (bounds[4] > o.bounds[4])
							return 1;
						else {
							return Long.compare(bounds[5], o.bounds[5]);
						}
					}
				}
			}
		}
	}

	@Override public String toString() {
		return (turn ? "ON" : "OFF") + " [" + bounds[0] + ", " + bounds[1] + "] x [" + bounds[2] + ", " + bounds[3] +
		       "] x [" + bounds[4] + ", " + bounds[5] + "]";
	}

	public boolean contains(Bounds other) {
		long[] otherBounds = other.getBounds()
		                         .clone();
		return bounds[0] <= otherBounds[0] && bounds[2] <= otherBounds[2] && bounds[4] <= otherBounds[4] &&
		       bounds[1] >= otherBounds[1] && bounds[3] >= otherBounds[3] && bounds[5] >= otherBounds[5];
	}

	public long[] getBounds() {
		return bounds;
	}

	public boolean intersect(Bounds other) {
		long[] otherBounds = other.getBounds()
		                         .clone();
		return bounds[1] >= otherBounds[0] && bounds[3] >= otherBounds[2] && bounds[5] >= otherBounds[4] &&
		       bounds[0] <= otherBounds[1] && bounds[2] <= otherBounds[3] && bounds[4] <= otherBounds[5];
	}

	public LinkedList<Bounds> dissect(Bounds other) {
		boolean otherTurn = other.isTurn();
		LinkedList<Bounds> returnToSender = new LinkedList<>();
		long[] otherBounds = other.getBounds()
		                         .clone();
		long[] xAxis;
		long[] yAxis;
		long[] zAxis;
		if (otherBounds[0] < bounds[0] && otherBounds[1] > bounds[1])
			xAxis = new long[]{otherBounds[0], bounds[0] - 1, bounds[0], bounds[1], bounds[1] + 1, otherBounds[1]};
		else if (otherBounds[0] < bounds[0])
			xAxis = new long[]{otherBounds[0], bounds[0] - 1, bounds[0], otherBounds[1]};
		else if (otherBounds[1] > bounds[1])
			xAxis = new long[]{otherBounds[0], bounds[1], bounds[1] + 1, otherBounds[1]};
		else
			xAxis = new long[]{otherBounds[0], otherBounds[1]};
		if (otherBounds[2] < bounds[2] && otherBounds[3] > bounds[3])
			yAxis = new long[]{otherBounds[2], bounds[2] - 1, bounds[2], bounds[3], bounds[3] + 1, otherBounds[3]};
		else if (otherBounds[2] < bounds[2])
			yAxis = new long[]{otherBounds[2], bounds[2] - 1, bounds[2], otherBounds[3]};
		else if (otherBounds[3] > bounds[3])
			yAxis = new long[]{otherBounds[2], bounds[3], bounds[3] + 1, otherBounds[3]};
		else
			yAxis = new long[]{otherBounds[2], otherBounds[3]};
		if (otherBounds[4] < bounds[4] && otherBounds[5] > bounds[5])
			zAxis = new long[]{otherBounds[4], bounds[4] - 1, bounds[4], bounds[5], bounds[5] + 1, otherBounds[5]};
		else if (otherBounds[4] < bounds[4])
			zAxis = new long[]{otherBounds[4], bounds[4] - 1, bounds[4], otherBounds[5]};
		else if (otherBounds[5] > bounds[5])
			zAxis = new long[]{otherBounds[4], bounds[5], bounds[5] + 1, otherBounds[5]};
		else
			zAxis = new long[]{otherBounds[4], otherBounds[5]};
		long[] temp = new long[6];
		if (xAxis.length + yAxis.length + zAxis.length == 18) {
			returnToSender.add(new Bounds(new long[]{otherBounds[0], otherBounds[1], otherBounds[2], otherBounds[3],
			                                        otherBounds[4], bounds[4] - 1}, otherTurn));
			returnToSender.add(new Bounds(new long[]{otherBounds[0], otherBounds[1], otherBounds[2], otherBounds[3],
			                                        bounds[5] + 1, otherBounds[5]}, otherTurn));
			returnToSender.add(new Bounds(new long[]{otherBounds[0], otherBounds[1], otherBounds[2], bounds[2] - 1,
			                                        bounds[4], bounds[5]}, otherTurn));
			returnToSender.add(new Bounds(new long[]{otherBounds[0], otherBounds[1], bounds[3] + 1, otherBounds[3],
			                                        bounds[4], bounds[5]}, otherTurn));
			returnToSender.add(new Bounds(new long[]{otherBounds[0], bounds[0] - 1, bounds[2], bounds[3], bounds[4],
			                                        bounds[5]}, otherTurn));
			returnToSender.add(new Bounds(new long[]{bounds[1] + 1, otherBounds[1], bounds[2], bounds[3], bounds[4],
			                                        bounds[5]}, otherTurn));
		} else {
			for (int x = 0; x < xAxis.length; x += 2) {
				for (int y = 0; y < yAxis.length; y += 2) {
					for (int z = 0; z < zAxis.length; z += 2) {
						if (xAxis[x] >= bounds[0] && xAxis[x + 1] <= bounds[1] && yAxis[y] >= bounds[2] &&
						    yAxis[y + 1] <= bounds[3] && zAxis[z] >= bounds[4] && zAxis[z + 1] <= bounds[5])
							continue;
						temp[0] = xAxis[x];
						temp[1] = xAxis[x + 1];
						temp[2] = yAxis[y];
						temp[3] = yAxis[y + 1];
						temp[4] = zAxis[z];
						temp[5] = zAxis[z + 1];
						returnToSender.add(new Bounds(temp, otherTurn));
					}
				}
			}
		}
		return returnToSender;
	}

	public boolean isTurn() {
		return turn;
	}
}