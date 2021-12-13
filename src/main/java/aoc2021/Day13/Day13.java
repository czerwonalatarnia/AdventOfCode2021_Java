package aoc2021.Day13;

import java.util.HashSet;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day13 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(13));
		System.out.println("Day 13\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		HashSet<Point> points = new HashSet<>();
		LinkedList<String> instructions = new LinkedList<>();
		for (var el : data) {
			if (el.charAt(0) >= '0' && el.charAt(0) <= '9') {
				String[] split = el.split(",");
				points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			} else
				instructions.add(el);
		}
		for (int i = 0; i < 1; i++) {
			String[] split = instructions.get(i)
			                             .split("=");
			char axis = split[0].charAt(split[0].length() - 1);
			int pos = Integer.parseInt(split[1]);
			HashSet<Point> temp = new HashSet<>(points);
			points.clear();
			for (var el : temp) {
				el.fold(axis, pos);
				points.add(el);
			}
		}
		return points.size();
	}

	int part2(LinkedList<String> data) {HashSet<Point> points = new HashSet<>();
		LinkedList<String> instructions = new LinkedList<>();
		for (var el : data) {
			if (el.charAt(0) >= '0' && el.charAt(0) <= '9') {
				String[] split = el.split(",");
				points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			} else
				instructions.add(el);
		}
		for (String instruction : instructions) {
			String[] split = instruction.split("=");
			char axis = split[0].charAt(split[0].length() - 1);
			int pos = Integer.parseInt(split[1]);
			HashSet<Point> temp = new HashSet<>(points);
			points.clear();
			for (var el : temp) {
				el.fold(axis, pos);
				points.add(el);
			}
		}
		char[][] tab = new char[50][10];
		for (var el: points) {
			tab[el.getX()][el.getY()] = '#';
		}
		for (int i = 49; i >=0; i--) {
			for (var c: tab[i]) {
				if (c != '#')
					c = ' ';
				System.out.print(c);
			}
			System.out.println();
		}
		return points.size();
	}
}