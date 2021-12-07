package aoc2021.Day07;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.Calculator;
import aoc2021.own.functions.DataReader;

public class Day07 implements IDay {
	public void day() {
		LinkedList<Long> data = DataReader.readLongArray(DataReader.createFilePath(7));
		System.out.println("Day 7\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<Long> data) {
		long max = Calculator.arrayLongMax(data);
		long min = Calculator.arrayLongMin(data);
		long sum = Long.MAX_VALUE;
		for (long it = min; it <= max ; it++) {
			LinkedList<Long> current = new LinkedList<>();
			for (var el: data) {
				current.add(Math.abs(el - it));
			}
			long pointSum = Calculator.arrayLongSum(current);
			if (pointSum < sum)
				sum = pointSum;
		}
		return sum;
	}

	long part2(LinkedList<Long> data) {
		long max = Calculator.arrayLongMax(data);
		long min = Calculator.arrayLongMin(data);
		long sum = Long.MAX_VALUE;
		for (long it = min; it <= max ; it++) {
			LinkedList<Long> current = new LinkedList<>();
			long pointSum = 0;
			for (var el: data) {
				current.add(Math.abs(el - it));
			}
			for (var el: current) {
				for (long i = 1; i <= el; i++) {
					pointSum += i;
				}
			}
			if (pointSum < sum)
				sum = pointSum;
		}
		return sum;
	}
}
