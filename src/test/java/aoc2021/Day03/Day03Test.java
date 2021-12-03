package aoc2021.Day03;

import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day03Test {
	Path resourcePath = Path.of("src", "test", "resource", "aoc2021", "Day03");
	@Test
	void testPart1() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day03test.txt"));
		assertEquals(198, new Day03().part1(dataTest));
	}

	@Test
	void testPart2() throws FileIsEmpty, IOException {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day03test.txt"));
		assertEquals(230, new Day03().part2());
	}
}
