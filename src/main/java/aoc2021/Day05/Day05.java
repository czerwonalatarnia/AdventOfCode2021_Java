package aoc2021.Day05;

import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

import java.util.LinkedList;

import static aoc2021.own.functions.Calculator.arrayLongSum;

public class Day05 implements IDay {
	public void day() {
		LinkedList<Long> sonarReports = DataReader.readLongArray(DataReader.createFilePath(5));
		System.out.println("Day 1\nThe answer to part 1 is " + part1() + "\nThe answer to part 2 is " + part2());
	}

	int part1() { return 0; }

	int part2() { return 0; }
}
