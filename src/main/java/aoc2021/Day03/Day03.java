package aoc2021.Day03;

import aoc2021.Day;
import aoc2021.own.functions.DataReader;

public abstract class Day03 implements Day {
	public static void day() {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(3));
		System.out.println("\nThe answer to part 1 is " + part1());
		System.out.println("\nThe answer to part 2 is " + part2());
	}

	static int part1() {
		return 0;
	}

	static int part2() {
		return 0;
	}
}