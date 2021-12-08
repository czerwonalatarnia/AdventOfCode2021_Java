package aoc2021.Day08;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import aoc2021.IDay;
import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.Calculator;
import aoc2021.own.functions.DataReader;

public class Day08 implements IDay {
	public void day() throws FileIsEmpty, IOException {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(8));
		LinkedList<String> data2 = DataReader.readAlchemyString(DataReader.createFilePath(8));
		System.out.println("Day 8\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data2));
	}

	long part1(LinkedList<String> data) {
		List<String> dataStream = data.stream().map(string -> string.substring(string.lastIndexOf('|') + 1).trim()).collect(Collectors.toList());
		data.clear();
		data = new LinkedList<>();
		for (var el : dataStream) {
			String[] strings = el.split(" ", 4);
			data.addAll(Arrays.asList(strings));
		}
		return data.stream().filter(el -> el.length() < 5 || el.length() > 6).count();
	}

	long part2(LinkedList<String> data) {
		LinkedList<Long> number = new LinkedList<>();
		for (var line : data) {
			Display display = new Display();
			String[] numbers = line.substring(0, line.indexOf('|')).trim().split("\\s+");
			String[] decode = line.substring(line.indexOf('|') + 1).trim().split("\\s+");
			for (String el : numbers) {
				if (el.length() == 2)
					display.matchStringAndNumber(el, 1);
				else if (el.length() == 3)
					display.matchStringAndNumber(el, 7);
				else if (el.length() == 4)
					display.matchStringAndNumber(el, 4);
				else if (el.length() == 7)
					display.matchStringAndNumber(el, 8);
			}
			for (String el : numbers) {
				char[] one = display.getDisplayParts(1);
				char[] four = display.getDisplayParts(4);
				if (el.length() == 5) {
					if (el.contains("" + one[0]) && el.contains("" + one[1]))
						display.matchStringAndNumber(el, 3);
				} else if (el.length() == 6) {
					if (!(el.contains("" + one[0]) && el.contains("" + one[1]))) {
						display.matchStringAndNumber(el, 6);
					} else if (el.contains("" + four[0]) && el.contains("" + four[1]) && el.contains("" + four[2]) && el.contains("" + four[3]))
						display.matchStringAndNumber(el, 9);
					else
						display.matchStringAndNumber(el, 0);
				}
			}
			for (String el : numbers) {
				char[] six = display.getDisplayParts(6);
				String three = new String(display.getDisplayParts(3));
				int count = 0;
				if (el.length() == 5) {
					for (char c : six) {
						if (el.contains("" + c))
							count++;
					}
					if (count == 5)
						display.matchStringAndNumber(el, 5);
					else if (!three.equals(el))
						display.matchStringAndNumber(el, 2);
				}
			}
			long temp = 0;
			for (int i = 0; i < 4; i++) {
				temp += display.getNumber(decode[i]) * Math.pow(10, 3 - i);
			}
			number.add(temp);
			System.out.println(temp);
		}
		return Calculator.arrayLongSum(number);
	}
}
