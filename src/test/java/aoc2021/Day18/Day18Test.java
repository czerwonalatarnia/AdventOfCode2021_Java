package aoc2021.Day18;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day18");

	@Test void testPart0A() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test0A.txt"));
		assertEquals("[[[[1,1],[2,2]],[3,3]],[4,4]]", new Day18().part1(dataTest));
	}

	@Test void testPart0B() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test0B.txt"));
		assertEquals("[[[[3,0],[5,3]],[4,4]],[5,5]]", new Day18().part1(dataTest));
	}

	@Test void testPart0C() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test0C.txt"));
		assertEquals("[[[[5,0],[7,4]],[5,5]],[6,6]]", new Day18().part1(dataTest));
	}

	@Test void testPart0D() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test0D.txt"));
		assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", new Day18().part1(dataTest));
	}

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test.txt"));
		assertEquals(4140, new Day18().part1(dataTest));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day18test.txt"));
		assertEquals(0, new Day18().part2(dataTest));
	}
}