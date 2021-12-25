package aoc2021.Day25;

import java.nio.file.Path;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
	public static final Path resourcePath = Path.of("src", "test", "resources", "aoc2021", "Day25");

	@Test void testPart() {
		var dataTest = DataReader.readAlchemyString(resourcePath.resolve("day25test.txt"));
		assertEquals(58, new Day25().part(dataTest));
	}
}