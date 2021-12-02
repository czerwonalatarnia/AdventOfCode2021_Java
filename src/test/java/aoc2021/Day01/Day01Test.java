package aoc2021.Day01;

import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

	@Test
	void testPart1() {
		var sonarReports = DataReader.readLongArray("src\\test\\resources\\aoc2021.Day01\\day01test.txt");
		assertEquals(7, Day01.part1(sonarReports));
	}

	@Test
	void testPart2() {
		var sonarReports = DataReader.readLongArray("src\\test\\resources\\aoc2021.Day01\\day01test.txt");
		assertEquals(5, Day01.part2(sonarReports));
	}
}