package aoc2021.Day16;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day16");

	@Test void testPart0() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16test0.txt"));
		assertEquals(6, new Day16().part1(dataTest));
	}

	@Test void testPart1A() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testA.txt"));
		assertEquals(9, new Day16().part1(dataTest));
	}

	@Test void testPart1B() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testB.txt"));
		assertEquals(14, new Day16().part1(dataTest));
	}

	@Test void testPart1C() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testC.txt"));
		assertEquals(16, new Day16().part1(dataTest));
	}

	@Test void testPart1D() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testD.txt"));
		assertEquals(12, new Day16().part1(dataTest));
	}

	@Test void testPart1E() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testE.txt"));
		assertEquals(23, new Day16().part1(dataTest));
	}

	@Test void testPart1F() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testF.txt"));
		assertEquals(31, new Day16().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readSimpleString(resourcePath.resolve("day16testA.txt"));
		assertEquals(0, new Day16().part2(dataTest));
	}
}