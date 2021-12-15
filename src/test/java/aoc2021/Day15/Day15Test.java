package aoc2021.Day15;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day15");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day15test.txt"));
		assertEquals(40, new Day15().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day15test.txt"));
		assertEquals(0, new Day15().part2(dataTest));
	}
}