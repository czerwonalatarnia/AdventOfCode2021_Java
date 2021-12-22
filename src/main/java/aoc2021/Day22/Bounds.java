package aoc2021.Day22;

import java.util.Arrays;

public class Bounds implements Comparable<Bounds> {
	private final boolean turn;
	private final int[] bounds;

	public Bounds(int[] bounds, boolean turn) {
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
		return (Math.min(bounds[1], 50) - Math.max(bounds[0], -50) + 1L) * (Math.min(bounds[3], 50) - Math.max(bounds[2], -50) + 1L) * (Math.min(bounds[5], 50) - Math.max(bounds[4], -50) + 1L);
	}

	public int[] getBounds() {
		return bounds;
	}

	public void setBoundsI(int boundsI, int i) {
		this.bounds[i] = boundsI;
	}

	public boolean isTurn() {
		return turn;
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
							return Integer.compare(bounds[5], o.bounds[5]);
						}
					}
				}
			}
		}
	}

	@Override public String toString() {
		return (turn ? "ON" : "OFF") + " [" + bounds[0] + ", " + bounds[1] + "] x [" + bounds[2] + ", " + bounds[3] + "] x [" + bounds[4] + ", " + bounds[5] + "]";
	}
}