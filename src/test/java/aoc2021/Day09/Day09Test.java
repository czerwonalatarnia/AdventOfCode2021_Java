package aoc2021.Day09;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day09");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day09test.txt"));
		assertEquals(15, new Day09().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day09test.txt"));
		assertEquals(1134, new Day09().part2(dataTest));
	}
}