package aoc2021.Day10;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day10 implements IDay {
	public void day() {
		LinkedList<Long> sonarReports = DataReader.readLongArray(DataReader.createFilePath(10));
		System.out.println("Day 10\nThe answer to part 1 is " + part1() + "\nThe answer to part 2 is " + part2());
	}

	int part1() {
		return 0;
	}

	int part2() {
		return 0;
	}
}