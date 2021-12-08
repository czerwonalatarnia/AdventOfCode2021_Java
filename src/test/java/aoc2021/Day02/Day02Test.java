package aoc2021.Day02;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day02");

	@Test
	void testPart1() {
		var movementTest = DataReader.readAlchemyString(resourcePath.resolve("day02test.txt"));
		assertEquals(150, new Day02().part1(movementTest));
	}

	@Test
	void testPart2() {
		var movementTest = DataReader.readAlchemyString(resourcePath.resolve("day02test.txt"));
		assertEquals(900, new Day02().part2(movementTest));
	}
}