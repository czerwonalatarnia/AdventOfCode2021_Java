package aoc2021.Day03;

import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

	@Test
	void testPart1() {
		var dataTest = DataReader.readAlchemyString("src\\test\\resources\\aoc2021\\Day03\\day03test.txt");
		assertEquals(198, Day03.part1(dataTest));
	}

	@Test
	void testPart2() {
		var dataTest = DataReader.readAlchemyString("src\\test\\resources\\aoc2021\\Day03\\day03test.txt");
		assertEquals(230, Day03.part2(dataTest));
	}
}