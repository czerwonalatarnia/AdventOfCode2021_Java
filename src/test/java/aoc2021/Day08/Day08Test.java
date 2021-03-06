package aoc2021.Day08;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day08");

	@Test
	void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day08test.txt"));
		assertEquals(26, new Day08().part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day08test.txt"));
		assertEquals(61229, new Day08().part2(dataTest));
	}
}