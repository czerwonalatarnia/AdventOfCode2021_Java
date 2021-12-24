package aoc2021.Day23;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Day23Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day23");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day23test.txt"));
		assertEquals(0, new Day23().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day23test.txt"));
		assertEquals(0, new Day23().part2(dataTest));
	}
}