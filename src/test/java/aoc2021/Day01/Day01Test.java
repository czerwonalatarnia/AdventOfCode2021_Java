package aoc2021.Day01;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day01");


	@Test
	void testPart1() {
		var sonarReports = DataReader.readLongArray(resourcePath.resolve("day01test.txt"));
		assertEquals(7, new Day01().part1(sonarReports));
	}
	@Test
	void testPart2() {
		var sonarReports = DataReader.readLongArray(resourcePath.resolve("day01test.txt"));
		assertEquals(5, new Day01().part2(sonarReports));
	}
}