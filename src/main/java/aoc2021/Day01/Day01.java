package aoc2021.Day01;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import static aoc2021.own.functions.Calculator.arrayLongSum;

public class Day01 implements IDay {
	public void day() {
		LinkedList<Long> sonarReports = DataReader.readLongArray(DataReader.createFilePath(1));
		System.out.println("Day 1\nThe answer to part 1 is " + part1(sonarReports) + "\nThe answer to part 2 is " + part2(sonarReports));
	}

	int part1(LinkedList<Long> sonarReports) {
		int increases = 0;
		for (int i = 1; i < sonarReports.size(); i++) {
			if (sonarReports.get(i) > sonarReports.get(i - 1))
				increases++;
		}
		return increases;
	}

	int part2(LinkedList<Long> sonarReports) {
		int tripleIncreases = 0;
		for (int i = 3; i < sonarReports.size(); i++) {
			if (sonarReports.get(i) > sonarReports.get(i - 3))
				tripleIncreases++;
		}
		return tripleIncreases;
	}
}