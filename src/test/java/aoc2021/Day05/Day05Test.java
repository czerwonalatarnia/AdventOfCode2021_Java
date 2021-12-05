package aoc2021.Day05;

import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
class Day05Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day05");

	@Test
	void testPart1() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day05test.txt"));
		assertEquals(5, new Day05().part1(dataTest));
	}

	@Test
	void testPart2() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day05test.txt"));
		assertEquals(12, new Day05().part2(dataTest));
	}
}