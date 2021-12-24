package aoc2021.Day22;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day22");

	@Test void testPart1A() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22testA.txt"));
		assertEquals(39, new Day22().part(dataTest, 1));
	}

	@Test void testPart1B() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22testB.txt"));
		assertEquals(590784, new Day22().part(dataTest, 1));
	}

	@Test void testPart1C() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22testC.txt"));
		assertEquals(474140, new Day22().part(dataTest, 1));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22testC.txt"));
		assertEquals(2758514936282235L, new Day22().part(dataTest, 2));
	}

	@Test void testPart1Puzzle() {
		Path resourcePath = Path.of("src", "main", "resources");
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22.txt"));
		assertEquals(647062, new Day22().part(dataTest, 1));
	}

	@Test void testPart2Puzzle() {
		Path resourcePath = Path.of("src", "main", "resources");
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day22.txt"));
		assertEquals(1319618626668022L, new Day22().part(dataTest, 2));
	}
}