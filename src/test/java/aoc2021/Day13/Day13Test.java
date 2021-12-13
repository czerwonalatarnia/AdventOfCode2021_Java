package aoc2021.Day13;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day13");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day13test.txt"));
		assertEquals(17, new Day13().part1(dataTest));
	}

	@Disabled @Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day13test.txt"));
		assertEquals(0, new Day13().part2(dataTest));
	}
}