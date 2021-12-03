package aoc2021.Day04;

import java.io.IOException;
import java.nio.file.Path;
import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {
	public final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day04");

	@Test
	void testPart1() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day04test.txt"));
		assertEquals(0, new Day04().part1());
	}

	@Test
	void testPart2() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day04test.txt"));
		assertEquals(0, new Day04().part2());
	}
}