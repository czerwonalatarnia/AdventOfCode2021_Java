package aoc2021.Day06;

import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
class Day06Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day06");

	@Test
	void testPart1() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day06test.txt"));
		assertEquals(0, new Day06().part1());
	}

	@Test
	void testPart2() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day06test.txt"));
		assertEquals(0, new Day06().part2());
	}
}