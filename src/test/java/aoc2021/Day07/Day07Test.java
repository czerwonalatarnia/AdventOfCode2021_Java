package aoc2021.Day07;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class Day07Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day07");

	@Test
	void testPart1() {
		var dataTest = DataReader.readLongArray(resourcePath.resolve("day07test.txt"));
		assertEquals(37, new Day07().part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readLongArray(resourcePath.resolve("day07test.txt"));
		assertEquals(168, new Day07().part2(dataTest));
	}
}