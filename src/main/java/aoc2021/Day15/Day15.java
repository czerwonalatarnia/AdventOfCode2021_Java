package aoc2021.Day15;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import aoc2021.own.functions.MapSearches;

public class Day15 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(15));
		System.out.println("Day 15\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		int maxWidth = data.get(0)
		                   .length();
		int maxHeight = data.size();
		int exitX = maxWidth - 1;
		int exitY = maxHeight - 1;
		int[][] map = new int[maxWidth][maxHeight];
		int[][] bestDistance = new int[maxWidth][maxHeight];
		for (int jt = 0; jt < maxHeight; jt++) {
			for (int it = 0; it < maxWidth; it++) {
				map[it][jt] = data.get(jt)
				                  .charAt(it) - '0';
				bestDistance[it][jt] = Integer.MAX_VALUE;
			}
		}
		MapSearches.mapBFSSquare(bestDistance, map, maxWidth, maxHeight, 0, 0, 0);
		return bestDistance[exitX][exitY];
	}

	int part2(LinkedList<String> data) {
		int repeatX = data.get(0)
		                  .length();
		int repeatY = data.size();
		int maxWidth = data.get(0)
		                   .length() * 5;
		int maxHeight = data.size() * 5;
		int exitX = maxWidth - 1;
		int exitY = maxHeight - 1;
		int[][] map = new int[maxWidth][maxHeight];
		int[][] bestDistance = new int[maxWidth][maxHeight];
		for (int jt = 0; jt < maxHeight; jt++) {
			for (int it = 0; it < maxWidth; it++) {
				map[it][jt] = data.get(jt % repeatY)
				                  .charAt(it % repeatX) - '0';
				map[it][jt] += ((it / repeatX) + (jt / repeatY));
				while (map[it][jt] > 9)
					map[it][jt] -= 9;
				bestDistance[it][jt] = Integer.MAX_VALUE;
			}
		}
		MapSearches.mapBFSSquare(bestDistance, map, maxWidth, maxHeight, 0, 0, 0);
		return bestDistance[exitX][exitY];
	}
}