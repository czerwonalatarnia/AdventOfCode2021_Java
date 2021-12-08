package aoc2021.Day05;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day05");

	@Test
	void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day05test.txt"));
		assertEquals(5, new Day05().part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day05test.txt"));
		assertEquals(12, new Day05().part2(dataTest));
	}
}