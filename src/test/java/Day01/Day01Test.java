package Day01;

import java.util.LinkedList;
import Functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

	@Test
	void testPart1() {
		LinkedList<Long> sonarReports = DataReader.readLongArray("src\\test\\resources\\Day01\\day01test.txt");
		assertEquals(7, Day01.part1(sonarReports));
	}

	@Test
	void testPart2() {
		LinkedList<Long> sonarReports = DataReader.readLongArray("src\\test\\resources\\Day01\\day01test.txt");
		assertEquals(5, Day01.part2(sonarReports));
	}
}