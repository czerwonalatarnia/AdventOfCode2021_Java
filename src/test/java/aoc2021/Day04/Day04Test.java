package aoc2021.Day04;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day04");

	@Test
	void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day04test.txt"));
		assertEquals(4512, new Day04().part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day04test.txt"));
		assertEquals(1924, new Day04().part2(dataTest));
	}
}