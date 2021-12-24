package aoc2021.Day24;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day24 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(24));
		System.out.println(
				"Day 24\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		int[] dimensions = new int[]{0, 0, 0, 1};
		long monad = 100_000_000_000_000L;
		String MONAD;
		int from;
		int to;
		while (dimensions[3] != 0) {
			dimensions = new int[]{0, 0, 0, 0};
			int error = 0;
			int charAt = 0;
			monad--;
			MONAD = String.valueOf(monad);
			while (MONAD.contains("0")) {
				monad--;
				MONAD = String.valueOf(monad);
			}
			System.out.println(monad);
			for (var line : data) {
				switch (line.substring(0, 3)) {
					case "inp" -> {
						switch (line.charAt(4)) {
							case 'w' -> dimensions[0] = MONAD.charAt(charAt) - '0';
							case 'x' -> dimensions[1] = MONAD.charAt(charAt) - '0';
							case 'y' -> dimensions[2] = MONAD.charAt(charAt) - '0';
							case 'z' -> dimensions[3] = MONAD.charAt(charAt) - '0';
						}
						charAt++;
					}
					case "add" -> {
						to = getTo(line);
						from = getFrom(line);
						if (from < 0)
							dimensions[to] += Integer.parseInt(line.substring(6));
						else
							dimensions[to] += dimensions[from];
					}
					case "mul" -> {
						to = getTo(line);
						from = getFrom(line);
						if (from < 0)
							dimensions[to] *= Integer.parseInt(line.substring(6));
						else
							dimensions[to] *= dimensions[from];
					}
					case "div" -> {
						to = getTo(line);
						from = getFrom(line);
						if (from < 0)
							from = Integer.parseInt(line.substring(6));
						else
							from = dimensions[from];
						if (from == 0)
							error = -1;
						else
							dimensions[to] /= from;
					}
					case "mod" -> {
						to = getTo(line);
						from = getFrom(line);
						if (from < 0)
							from = Integer.parseInt(line.substring(6));
						else
							from = dimensions[from];
						if (from <= 0)
							error = -22;
						else if (dimensions[to] < 0)
							error = -21;
						else
							dimensions[to] /= from;
					}
					case "eql" -> {
						to = getTo(line);
						from = getFrom(line);
						if (from < 0)
							dimensions[to] = dimensions[to] == Integer.parseInt(line.substring(6)) ? 1 : 0;
						else
							dimensions[to] = dimensions[to] == dimensions[from] ? 1 : 0;
					}
				}
				if (error < 0)
					break;
			}
			if (error < 0)
				dimensions[3] = 1;
		}
		return monad;
	}

	long part2(LinkedList<String> data) {
		return 0;
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
}