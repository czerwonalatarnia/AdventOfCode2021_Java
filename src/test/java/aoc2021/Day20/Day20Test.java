package aoc2021.Day20;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day20");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day20test.txt"));
		assertEquals(35, new Day20().part(dataTest, 2));
	}

	@Test void realTestPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day20realTest.txt"));
		assertEquals(5326, new Day20().part(dataTest, 2));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day20test.txt"));
		assertEquals(3351, new Day20().part(dataTest, 50));
	}
}