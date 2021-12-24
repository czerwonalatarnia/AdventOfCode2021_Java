package aoc2021.Day23;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day23 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(23));
		System.out.println("Day 23\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		return 0;
	}

	long part2(LinkedList<String> data) {
		return 0;
	}
}