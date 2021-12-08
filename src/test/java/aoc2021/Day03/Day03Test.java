package aoc2021.Day03;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day03");

	@Test
	void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day03test.txt"));
		assertEquals(198, new Day03().part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day03test.txt"));
		assertEquals(230, new Day03().part2(dataTest));
	}
}
