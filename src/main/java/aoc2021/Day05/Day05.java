package aoc2021.Day05;

import java.util.LinkedList;
import java.util.stream.Stream;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day05 implements IDay {
	public void day() {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(5));
		System.out.println("Day 5\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		int[][] map = new int[1000][1000];
		fillMap(map, data, 1);
		return countTheDanger(map);
	}

	long part2(LinkedList<String> data) {
		int[][] map = new int[1000][1000];
		fillMap(map, data, 2);
		return countTheDanger(map);
	}

	private void fillMap(int[][] map, LinkedList<String> data, int part) {
		for (String datum : data) {
			int[] placement = Stream.of(datum.split(",|\\s+->\\s+"))
			                        .mapToInt(Integer::parseInt)
			                        .toArray();
			if (placement[0] == placement[2]) {
				for (int it = Math.min(placement[1], placement[3]); it <= Math.max(placement[1], placement[3]); it++) {
					map[placement[0]][it]++;
				}
			} else if (placement[1] == placement[3]) {
				for (int it = Math.min(placement[0], placement[2]); it <= Math.max(placement[0], placement[2]); it++) {
					map[it][placement[1]]++;
				}
			} else if (part == 2) {
				if ((placement[0] < placement[2] && placement[1] < placement[3]) || (placement[0] > placement[2] && placement[1] > placement[3])) {
					for (int it = 0; it <= Math.abs(placement[0] - placement[2]); it++) {
						map[Math.min(placement[0], placement[2]) + it][Math.min(placement[1], placement[3]) + it]++;
					}
				} else {
					for (int it = 0; it <= Math.abs(placement[0] - placement[2]); it++) {
						map[Math.min(placement[0], placement[2]) + it][Math.max(placement[1], placement[3]) - it]++;
					}
				}
			}
		}
	}

	private long countTheDanger(int[][] map) {
		long sum = 0;
		for (var level : map) {
			for (var position : level) {
				if (position > 1)
					sum++;
			}
		}
		return sum;
	}
}