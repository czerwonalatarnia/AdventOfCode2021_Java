package aoc2021.Day17;

import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day17 implements IDay {
	public void day() {
		String data = DataReader.readSimpleString(DataReader.createFilePath(17));
		System.out.println("Day 17\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(String data) {
		StringBuilder yPos = new StringBuilder();
		for (int i = data.indexOf('y') + 3; data.charAt(i) >= '0' && data.charAt(i) <= '9'; i++)
			yPos.append(data.charAt(i));
		int yMinAbs = Integer.parseInt(String.valueOf(yPos));
		return (yMinAbs - 1) * yMinAbs / 2;
		// What goes up, goes down. And when it goes down below 0 it goes faster than it started going up. So the optimal initial speed is one lower than the absolute value of the bottom row of target.
	}

	int part2(String data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = data.indexOf('x') + 2; data.charAt(i) >= '0' && data.charAt(i) <= '9'; i++)
			stringBuilder.append(data.charAt(i));
		Point.xMin = Integer.parseInt(String.valueOf(stringBuilder));
		stringBuilder = new StringBuilder();
		for (int i = data.indexOf('.') + 2; data.charAt(i) >= '0' && data.charAt(i) <= '9'; i++)
			stringBuilder.append(data.charAt(i));
		Point.xMax = Integer.parseInt(String.valueOf(stringBuilder));
		stringBuilder = new StringBuilder();
		for (int i = data.indexOf('y') + 3; data.charAt(i) >= '0' && data.charAt(i) <= '9'; i++)
			stringBuilder.append(data.charAt(i));
		Point.yMin = -Integer.parseInt(String.valueOf(stringBuilder));
		stringBuilder = new StringBuilder();
		for (int i = data.lastIndexOf('-') + 1; i < data.length(); i++)
			stringBuilder.append(data.charAt(i));
		Point.yMax = -Integer.parseInt(String.valueOf(stringBuilder));
		int counter = 0;
		for (int x = 0; x <= Point.xMax; x++) {
			for (int y = Point.yMin; y <= -Point.yMin; y++) {
				Point point = new Point(x, y);
				if (point.shot())
					counter++;
			}
		}
		return counter;
	}
}