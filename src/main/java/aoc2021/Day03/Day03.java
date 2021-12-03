package aoc2021.Day03;

import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day03 implements IDay  {
	public void day() throws Exception {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(3));
		System.out.println("Day 3\nThe answer to part 1 is " + part1() + "\nThe answer to part 2 is " + part2());
	}

	int part1() {
		return 0;
	}

	int part2() {
		return 0;
	}
}