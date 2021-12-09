package aoc2021.Day09;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day09 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(9));
		System.out.println("Day 9\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		int width = data.getFirst()
		                .length();
		int height = data.size();
		int[][] map = new int[width][height];
		fillTheMap(width, height, map, data);
		int sum = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				try {
					if (map[x - 1][y] <= map[x][y])
						continue;
				} catch (IndexOutOfBoundsException ignored) {
				}
				try {
					if (map[x + 1][y] <= map[x][y])
						continue;
				} catch (IndexOutOfBoundsException ignored) {
				}
				try {
					if (map[x][y + 1] <= map[x][y])
						continue;
				} catch (IndexOutOfBoundsException ignored) {
				}
				try {
					if (map[x][y - 1] <= map[x][y])
						continue;
				} catch (IndexOutOfBoundsException ignored) {
				}
				sum += (map[x][y] + 1);
			}
		}
		return sum;
	}

	int part2(LinkedList<String> data) {
		int width = data.getFirst()
		                .length();
		int height = data.size();
		int[][] map = new int[width][height];
		boolean[][] wasChecked = new boolean[width][height];
		fillTheMap(width, height, map, data, wasChecked);
		int[] max = new int[3];
		int size;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!wasChecked[x][y]) {
					size = fillTheBasin(x, y, map, wasChecked, 0);
					if (size > max[2]) {
						max[2] = size;
						if (size > max[1]) {
							max[2] = max[1];
							max[1] = size;
							if (size > max[0]) {
								max[1] = max[0];
								max[0] = size;
							}
						}
					}
				}
			}
		}
		return max[0] * max[1] * max[2];
	}

	private void fillTheMap(int width, int height, int[][] map, LinkedList<String> data) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = data.get(y)
				                .charAt(x) - '0';
			}
		}
	}

	private void fillTheMap(int width, int height, int[][] map, LinkedList<String> data, boolean[][] wasChecked) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = data.get(y)
				                .charAt(x) - '0';
				wasChecked[x][y] = map[x][y] == 9;
			}
		}
	}

	private int fillTheBasin(int x, int y, int[][] map, boolean[][] wasChecked, int returnSize) {
		returnSize++;
		wasChecked[x][y] = true;
		try {
			if (map[x - 1][y] < 9 && !wasChecked[x - 1][y])
				returnSize = fillTheBasin(x - 1, y, map, wasChecked, returnSize);
		} catch (IndexOutOfBoundsException ignored) {
		}
		try {
			if (map[x + 1][y] < 9 && !wasChecked[x + 1][y])
				returnSize = fillTheBasin(x + 1, y, map, wasChecked, returnSize);
		} catch (IndexOutOfBoundsException ignored) {
		}
		try {
			if (map[x][y + 1] < 9 && !wasChecked[x][y + 1])
				returnSize = fillTheBasin(x, y + 1, map, wasChecked, returnSize);
		} catch (IndexOutOfBoundsException ignored) {
		}
		try {
			if (map[x][y - 1] < 9 && !wasChecked[x][y - 1])
				returnSize = fillTheBasin(x, y - 1, map, wasChecked, returnSize);
		} catch (IndexOutOfBoundsException ignored) {
		}
		return returnSize;
	}
}