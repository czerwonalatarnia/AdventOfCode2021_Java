package aoc2021.Day17;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day17");

	@Test void testPart1() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day17test.txt"));
		assertEquals(45, new Day17().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day17test.txt"));
		assertEquals(112, new Day17().part2(dataTest));
	}
}