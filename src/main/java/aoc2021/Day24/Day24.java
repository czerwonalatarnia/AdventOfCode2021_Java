package aoc2021.Day24;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day24 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(24));
		ALUTestUnit(data);
		System.out.println(
				"Day 24\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		long[] dimensions = new long[]{0, 0, 0, 1};
		// monad need to be 7 digits long
		long monad = 10_000_000L;
		String MONAD;
		while (dimensions[3] != 0 && monad > 1_111_111) {
			dimensions = new long[]{0, 0, 0, 0};
			monad--;
			MONAD = getMonadString(monad);
			while (MONAD.contains("0") && monad >= 1_111_111) {
				monad--;
				MONAD = getMonadString(monad);
			}
			if (!ALU(data, dimensions, MONAD))
				dimensions[3] = 1;
			else
				System.out.println(dimensions[3]);
		}
		System.out.println(monad);
		return Long.parseLong(getMonadString(monad));
	}

	// All dependencies on each position of the string were found out by ALUTestUnit and ALUTesting.



	private void input(long[] dimensions, String MONAD, int charAt, String line) {
		switch (line.charAt(4)) {
			case 'w' -> dimensions[0] = MONAD.charAt(charAt) - '0';
			case 'x' -> dimensions[1] = MONAD.charAt(charAt) - '0';
			case 'y' -> dimensions[2] = MONAD.charAt(charAt) - '0';
			case 'z' -> dimensions[3] = MONAD.charAt(charAt) - '0';
		}
	}

	private void addition(long[] dimensions, String line) {
		int from;
		int to;
		to = getTo(line);
		from = getFrom(line);
		if (from < 0)
			dimensions[to] += Long.parseLong(line.substring(6));
		else
			dimensions[to] += dimensions[from];
	}

	private void multiply(long[] dimensions, String line) {
		int from;
		int to;
		to = getTo(line);
		from = getFrom(line);
		if (from < 0)
			dimensions[to] *= Long.parseLong(line.substring(6));
		else
			dimensions[to] *= dimensions[from];
	}

	private boolean division(long[] dimensions, String line) {
		int to;
		int from;
		long substitute;
		to = getTo(line);
		from = getFrom(line);
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
		int from;
		int to;
		long substitute;
		to = getTo(line);
		from = getFrom(line);
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
		int from;
		int to;
		to = getTo(line);
		from = getFrom(line);
		if (from < 0)
			dimensions[to] = dimensions[to] == Long.parseLong(line.substring(6)) ? 1 : 0;
		else
			dimensions[to] = dimensions[to] == dimensions[from] ? 1 : 0;
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

	long part1(LinkedList<String> data) {
		long[] dimensions = new long[]{0, 0, 0, 1};
		// monad need to be 7 digits long
		long monad = 10_000_000L;
		String MONAD;
		while (dimensions[3] != 0 && monad > 1_111_111) {
			dimensions = new long[]{0, 0, 0, 0};
			monad--;
			MONAD = getMonadString(monad);
			while (MONAD.contains("0") && monad >= 1_111_111) {
				monad--;
				MONAD = getMonadString(monad);
			}
			if (ALU(data, dimensions, MONAD))
				dimensions[3] = 1;
			else
				System.out.println(dimensions[3]);
		}
		System.out.println(monad);
		return Long.parseLong(getMonadString(monad));
	}

	private String getMonadString(long monad) {
		String monadString = String.valueOf(monad);
		return String.valueOf(monadString.charAt(0)) + monadString.charAt(1) + monadString.charAt(2) + "99" +
		       monadString.charAt(3) + monadString.charAt(4) + "99" + monadString.charAt(5) + "999" +
		       monadString.charAt(6);
	}

	long part2(LinkedList<String> data) {
		return 0;
	}

	private long ALUTesting(LinkedList<String> data, long[] dimensions, String MONAD) {
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
						System.out.println("\tDividing error");
				}
				case "mod" -> {
					if (modulo(dimensions, line))
						System.out.println("\tModulo error");
				}
				case "eql" -> equals(dimensions, line);
				default -> System.out.println("ERROR ERROR ERROR ERROR");
			}
		}
		return dimensions[3];
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
}