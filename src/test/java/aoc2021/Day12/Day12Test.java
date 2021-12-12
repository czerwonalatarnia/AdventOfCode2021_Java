package aoc2021.Day12;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day12");

	@Test void testPart1A() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day12testA.txt"));
		assertEquals(10, new Day12().part1(dataTest));
	}

	@Test void testPart1B() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day12testB.txt"));
		assertEquals(19, new Day12().part1(dataTest));
	}

	@Test void testPart1C() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day12testC.txt"));
		assertEquals(226, new Day12().part1(dataTest));
	}

	@Test void testPart1Answer() {
		Path codeFix = Path.of("src", "main", "resources");
		var dataTest = DataReader.readAlchemyString(codeFix.resolve("day12.txt"));
		assertEquals(4549, new Day12().part1(dataTest));
	}

	@Test void testPart2A() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day12testA.txt"));
		assertEquals(36, new Day12().part2(dataTest));
	}

	@Test void testPart2B() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day12testB.txt"));
		assertEquals(103, new Day12().part2(dataTest));
	}

	@Test void testPart2Answer() {
		Path codeFix = Path.of("src", "main", "resources");
		var dataTest = DataReader.readAlchemyString(codeFix.resolve("day12.txt"));
		assertEquals(120535, new Day12().part2(dataTest));
	}
}