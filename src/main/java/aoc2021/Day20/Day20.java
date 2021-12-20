package aoc2021.Day20;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import aoc2021.IDay;
import aoc2021.own.functions.Calculator;
import aoc2021.own.functions.DataReader;

public class Day20 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(20));
		LinkedList<String> data2 = DataReader.readAlchemyString(DataReader.createFilePath(20));
		System.out.println("Day 20\nThe answer to part 1 is " + part(data, 2) + "\nThe answer to part 2 is " + part(data2, 50));
	}

	long part(LinkedList<String> data, int repeats) {
		int[] cypher = decodeLine(Objects.requireNonNull(data.poll()));
		HashMap<String, Integer> picture = new HashMap<>();
		int minX = 0;
		int maxX = data.get(0)
		               .length();
		int minY = 0;
		int maxY = data.size();
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				StringBuilder key = new StringBuilder();
				key.append(x)
				   .append(',')
				   .append(y);
				if (data.get(y)
				        .charAt(x) == '#')
					picture.put(String.valueOf(key), 1);
				else
					picture.put(String.valueOf(key), 0);
			}
		}
		minX -= repeats * 2;
		minY -= repeats * 2;
		maxX += repeats * 2;
		maxY += repeats * 2;
		for (int it = 0; it < repeats; it++) {
			HashMap<String, Integer> temp = new HashMap<>();
			for (int y = minY; y < maxY; y++) {
				for (int x = minX; x < maxX; x++) {
					StringBuilder binary = new StringBuilder();
					for (int rY = -1; rY < 2; rY++) {
						for (int rX = -1; rX < 2; rX++) {
							String subKey = (x + rX) + "," + (y + rY);
							if (picture.containsKey(subKey) && picture.get(subKey) >= 1)
								binary.append(1);
							else
								binary.append(0);
						}
					}
					String key = x + "," + y;
					temp.put(key, cypher[Calculator.binaryToInt(String.valueOf(binary))]);
				}
			}
			picture.clear();
			picture.putAll(temp);
		}
		for (int it = 0; it < 1; it++) {
			HashMap<String, Integer> temp = new HashMap<>();
			for (int y = minY + repeats; y < maxY - repeats; y++) {
				for (int x = minX + repeats; x < maxX - repeats; x++) {
					String key = x + "," + y;
					temp.put(key, picture.get(key));
				}
			}
			picture.clear();
			picture.putAll(temp);
		}
		int counter = 0;
		for (var el : picture.values()) {
			if (el > 0)
				counter++;
		}
		return counter;
	}

	private int[] decodeLine(String poll) {
		if (poll.length() != 512) {
			System.out.println("ERROR");
			return new int[]{0};
		} else {
			int[] cypher = new int[512];
			for (int i = 0; i < 512; i++) {
				if (poll.charAt(i) == '#')
					cypher[i] = 1;
				else
					cypher[i] = 0;
			}
			return cypher;
		}
	}
}