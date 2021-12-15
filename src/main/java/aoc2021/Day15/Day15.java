package aoc2021.Day15;

import java.util.LinkedList;
import java.util.Stack;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day15 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(15));
		System.out.println("Day 9\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		int maxWidth = data.get(0)
		                   .length();
		int maxHeight = data.size();
		int exitX = maxWidth - 1;
		int exitY = maxHeight - 1;
		int minDanger = Integer.MAX_VALUE;
		Stack<Path> paths = new Stack<>();
		int[][] map = new int[maxWidth][maxHeight];
		for (int jt = 0; jt < maxHeight; jt++) {
			for (int it = 0; it < maxWidth; it++)
				map[it][jt] = data.get(jt)
				                  .charAt(it) - '0';
		}
		paths.push(new Path(0, 0, 0));
		while (!paths.isEmpty()) {
			Path here = paths.pop();
			int x = here.getXPos();
			int y = here.getYPos();
			if (here.getPathDanger() >= minDanger)
				continue;
			if (x == exitX && y == exitY) {
				minDanger = here.getPathDanger();
				continue;
			}
			/*if (x - 1 > 0) {
				if (here.isNew(x - 1, y))
					paths.push(new Path(x - 1, y, map[x - 1][y], here));
			}
			if (y - 1 > 0) {
				if (here.isNew(x, y - 1))
					paths.push(new Path(x, y - 1, map[x][y - 1], here));
			}*/
			if (y < exitY && x < exitX) {
				if (map[x][y + 1] > map[x + 1][y]) {
					if (x + 1 < maxWidth) {
						if (here.isNew(x + 1, y))
							paths.push(new Path(x + 1, y, map[x + 1][y], here));
					}
					if (y + 1 < maxWidth) {
						if (here.isNew(x, y + 1))
							paths.push(new Path(x, y + 1, map[x][y + 1], here));
					}
				} else {
					if (y + 1 < maxWidth) {
						if (here.isNew(x, y + 1))
							paths.push(new Path(x, y + 1, map[x][y + 1], here));
					}
					if (x + 1 < maxWidth) {
						if (here.isNew(x + 1, y))
							paths.push(new Path(x + 1, y, map[x + 1][y], here));
					}
				}
			} else {
				if (x + 1 < maxWidth) {
					if (here.isNew(x + 1, y))
						paths.push(new Path(x + 1, y, map[x + 1][y], here));
				}
				if (y + 1 < maxWidth) {
					if (here.isNew(x, y + 1))
						paths.push(new Path(x, y + 1, map[x][y + 1], here));
				}

			}
			System.out.println("(" + x + ", " + y + ") = " + here.getPathDanger() + " --------- " + minDanger);
		}
		return minDanger;
	}

	int part2(LinkedList<String> data) {
		return 0;
	}
}