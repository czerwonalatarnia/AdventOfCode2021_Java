package aoc2021.Day06;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day06");

	@Test
	void testPart_18() {
		var dataTest = DataReader.readLongArray(resourcePath.resolve("day06test.txt"));
		assertEquals(26, new Day06().breedTheFish(dataTest, 18));
	}

	@Test
	void testPart_80() {
		var dataTest = DataReader.readLongArray(resourcePath.resolve("day06test.txt"));
		assertEquals(5934, new Day06().breedTheFish(dataTest, 80));
	}

	/*@Test
	void testPart2() {
		var dataTest = DataReader.readLongArray(resourcePath.resolve("day06test.txt"));
		assertEquals((long) 26984457539, new Day06().breedTheFish(dataTest, 256));
	}*/
}