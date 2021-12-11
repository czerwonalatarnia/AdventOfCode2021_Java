package aoc2021.Day11;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day11");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day11test.txt"));
		assertEquals(1656L, new Day11().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day11test.txt"));
		assertEquals(195, new Day11().part2(dataTest));
	}
}