package aoc2021.Day10;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day10");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day10test.txt"));
		assertEquals(26397L, new Day10().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day10test.txt"));
		new Day10().part1(dataTest);
		assertEquals(288957L, new Day10().part2(dataTest));
	}
}