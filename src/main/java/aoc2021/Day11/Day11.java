package aoc2021.Day11;

import java.util.LinkedList;
import java.util.Objects;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day11 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(11));
		System.out.println("Day 11\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		int height = data.size();
		int width = Objects.requireNonNull(data.peekFirst())
		                   .length();
		int[][] preGlow = new int[width][height];
		int[][] postGlow = new int[width][height];
		boolean[][] wasGlow = new boolean[width][height];
		int flashes = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				preGlow[x][y] = data.get(y)
				                    .charAt(x) - '0';
		}
		for (int steps = 0; steps < 100; steps++) {
			simulateTheSquids(height, width, preGlow, postGlow, wasGlow);
			flashes = getFlashes(height, width, preGlow, postGlow, flashes);
		}
		return flashes;
	}

	long part2(LinkedList<String> data) {
		int height = data.size();
		int width = Objects.requireNonNull(data.peekFirst())
		                   .length();
		int[][] preGlow = new int[width][height];
		int[][] postGlow = new int[width][height];
		boolean[][] wasGlow = new boolean[width][height];
		int steps = 0;
		int flashes = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				preGlow[x][y] = data.get(y)
				                    .charAt(x) - '0';
		}
		for (; steps < 20000; steps++) {
			simulateTheSquids(height, width, preGlow, postGlow, wasGlow);
			int oldFlashes = flashes;
			flashes = getFlashes(height, width, preGlow, postGlow, flashes);
			if (flashes - oldFlashes == 100)
				return steps + 1;
		}
		return 0;
	}

	private void simulateTheSquids(int height, int width, int[][] preGlow, int[][] postGlow, boolean[][] wasGlow) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				postGlow[x][y] = preGlow[x][y] + 1;
				wasGlow[x][y] = false;
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (postGlow[x][y] >= 10 && !wasGlow[x][y]) {
						wasGlow[x][y] = true;
						try {
							postGlow[x - 1][y - 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x - 1][y]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x - 1][y + 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x][y - 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x][y]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x][y + 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x + 1][y - 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x + 1][y]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
						try {
							postGlow[x + 1][y + 1]++;
						} catch (IndexOutOfBoundsException ignored) {
						}
					}
				}
			}
		}
	}

	private int getFlashes(int height, int width, int[][] preGlow, int[][] postGlow, int flashes) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (postGlow[x][y] >= 10) {
					flashes++;
					preGlow[x][y] = 0;
				} else
					preGlow[x][y] = postGlow[x][y];
			}
		}
		return flashes;
	}
}