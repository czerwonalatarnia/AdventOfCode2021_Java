package aoc2021.Day10;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day10 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(10));
		System.out.println("Day 10\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		long sum = 0;
		for (int lines = 0; lines < data.size(); lines++) {
			String line = data.get(lines);
			char nextProblem = ' ';
			line = connectBrackets(line);
			for (int it = 1; it < line.length(); it++) {
				if (line.charAt(it) == ')' || line.charAt(it) == '}' || line.charAt(it) == ']' || line.charAt(it) == '>') {
					nextProblem = line.charAt(it);
					data.remove(lines);
					lines--;
					break;
				}
			}
			switch (nextProblem) {
				case ')' -> sum += 3;
				case ']' -> sum += 57;
				case '}' -> sum += 1197;
				case '>' -> sum += 25137;
			}
		}
		return sum;
	}

	long part2(LinkedList<String> data) {
		LinkedList<Long> sum = new LinkedList<>();
		for (var line : data) {
			line = connectBrackets(line);
			long sumLine = 0;
			for (int it = line.length() - 1; it >= 0; it--) {
				switch (line.charAt(it)) {
					case '(' -> sumLine = sumLine * 5 + 1L;
					case '[' -> sumLine = sumLine * 5 + 2L;
					case '{' -> sumLine = sumLine * 5 + 3L;
					case '<' -> sumLine = sumLine * 5 + 4L;
				}
			}
			sum.add(sumLine);
		}
		sum.sort(Long::compareTo);
		return sum.get(sum.size() / 2);
	}

	private String connectBrackets(String line) {
		for (int it = 1; it < line.length(); it++) {
			if (Math.abs((line.charAt(it) - line.charAt(it - 1))) <= 2 && (line.charAt(it) > line.charAt(it - 1)) && (line.charAt(it) != line.charAt(it - 1))) {
				line = line.substring(0, it - 1) + line.substring(it + 1);
				it = 0;
			}
		}
		return line;
	}
}