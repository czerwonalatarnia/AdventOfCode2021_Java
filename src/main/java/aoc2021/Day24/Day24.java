package aoc2021.Day24;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day24 implements IDay {

	// the logic was derived from manual analysis of input based on ideas from r/adventofcode subreddit

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(24));
		System.out.println(
				"Day 24\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	String part1(LinkedList<String> data) {
		int[] digits = new int[14];
		for (int i = 0; i < 14; i++)
			digits[i] = 9;
		digits[13] = digits[0] - 3;
		digits[12] = digits[1] - 4;
		digits[5] = digits[2] - 2;
		digits[4] = digits[3];
		digits[9] = digits[6] - 8;
		digits[7] = digits[8] - 4;
		digits[11] = digits[10] - 5;
		StringBuilder MONAD = new StringBuilder();
		for (int i = 0; i < 14; i++)
			MONAD.append(digits[i]);
		return MONAD.toString();
	}

	String part2(LinkedList<String> data) {
		int[] digits = new int[14];
		for (int i = 0; i < 14; i++)
			digits[i] = 1;
		digits[0] = 3 + digits[13];
		digits[1] = 4 + digits[12];
		digits[2] = 2 + digits[5];
		digits[4] = digits[3];
		digits[6] = 8 + digits[9];
		digits[8] = 4 + digits[7];
		digits[10] = 5 + digits[11];
		StringBuilder MONAD = new StringBuilder();
		for (int i = 0; i < 14; i++)
			MONAD.append(digits[i]);
		return MONAD.toString();
	}

	private boolean ALU(LinkedList<String> data, long[] dimensions, String MONAD) {
		int charAt = 0;
		for (var line : data) {
			switch (line.substring(0, 3)) {
				case "inp" -> {
					input(dimensions, MONAD, charAt, line);
					charAt++;
				}
				case "add" -> addition(dimensions, line);
				case "mul" -> multiply(dimensions, line);
				case "div" -> {
					if (division(dimensions, line))
						return true;
				}
				case "mod" -> {
					if (modulo(dimensions, line))
						return true;
				}
				case "eql" -> equals(dimensions, line);
				default -> {
					System.out.println("ERROR ERROR ERROR ERROR");
					return true;
				}
			}
		}
		return false;
	}

	private void input(long[] dimensions, String MONAD, int charAt, String line) {
		switch (line.charAt(4)) {
			case 'w' -> dimensions[0] = MONAD.charAt(charAt) - '0';
			case 'x' -> dimensions[1] = MONAD.charAt(charAt) - '0';
			case 'y' -> dimensions[2] = MONAD.charAt(charAt) - '0';
			case 'z' -> dimensions[3] = MONAD.charAt(charAt) - '0';
		}
	}

	private void addition(long[] dimensions, String line) {
		int from = getFrom(line);
		int to = getTo(line);
		if (from < 0)
			dimensions[to] += Long.parseLong(line.substring(6));
		else
			dimensions[to] += dimensions[from];
	}

	private void multiply(long[] dimensions, String line) {
		int from = getFrom(line);
		int to = getTo(line);
		if (from < 0)
			dimensions[to] *= Long.parseLong(line.substring(6));
		else
			dimensions[to] *= dimensions[from];
	}

	private boolean division(long[] dimensions, String line) {
		long substitute;
		int from = getFrom(line);
		int to = getTo(line);
		if (from < 0)
			substitute = Long.parseLong(line.substring(6));
		else
			substitute = dimensions[from];
		if (substitute == 0)
			return true;
		else
			dimensions[to] /= substitute;
		return false;
	}

	private boolean modulo(long[] dimensions, String line) {
		int from = getFrom(line);
		int to = getTo(line);
		long substitute;
		if (from < 0)
			substitute = Long.parseLong(line.substring(6));
		else
			substitute = dimensions[from];
		if (substitute <= 0)
			return true;
		else if (dimensions[to] < 0)
			return true;
		else
			dimensions[to] %= substitute;
		return false;
	}

	private void equals(long[] dimensions, String line) {
		int from = getFrom(line);
		int to = getTo(line);
		if (from < 0)
			dimensions[to] = dimensions[to] == Long.parseLong(line.substring(6)) ? 1 : 0;
		else
			dimensions[to] = dimensions[to] == dimensions[from] ? 1 : 0;
	}

	private int getFrom(String line) {
		int from = -1;
		switch (line.charAt(6)) {
			case 'w' -> from = 0;
			case 'x' -> from = 1;
			case 'y' -> from = 2;
			case 'z' -> from = 3;
		}
		return from;
	}

	private int getTo(String line) {
		int to = -1;
		switch (line.charAt(4)) {
			case 'w' -> to = 0;
			case 'x' -> to = 1;
			case 'y' -> to = 2;
			case 'z' -> to = 3;
		}
		return to;
	}
}