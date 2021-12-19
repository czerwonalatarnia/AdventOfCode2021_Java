package aoc2021.Day19;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day19");

	@Test void testPart1() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day19test.txt"));
		assertEquals(79, new Day19().part(dataTest, 1));
	}

	@Test void testPart2() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day19test.txt"));
		assertEquals(3621, new Day19().part(dataTest, 2));
	}
}