package aoc2021.Day02;

import java.util.LinkedList;
import aoc2021.own.functions.DataReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

	@Test
	void testPart1() {
		LinkedList<String> movementTest = DataReader.readAlchemyString("src\\test\\resources\\aoc2021.Day02\\day02test.txt");
		assertEquals(150, Day02.part1(movementTest));
	}

	@Test
	void testPart2() {
		LinkedList<String> movementTest = DataReader.readAlchemyString("src\\test\\resources\\aoc2021.Day02\\day02test.txt");
		assertEquals(900, Day02.part2(movementTest));
	}
}