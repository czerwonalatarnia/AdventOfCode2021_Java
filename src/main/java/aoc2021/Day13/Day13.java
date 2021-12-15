package aoc2021.Day13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import aoc2021.own.objects.Point;

public class Day13 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(13));
		System.out.println("Day 13");
		part(data);
	}

	int part(LinkedList<String> data) {
		int part1Answer = 0;
		Set<Point> points = new HashSet<>();
		LinkedList<String> instructions = new LinkedList<>();
		for (var el : data) {
			if (el.charAt(0) >= '0' && el.charAt(0) <= '9') {
				String[] split = el.split(",");
				points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			} else
				instructions.add(el);
		}
		for (int it = 0; it < instructions.size(); it++) {
			points = foldThePaper(points, instructions.get(it));
			if (it == 0) {
				part1Answer = points.size();
				System.out.println("The answer to part 1 is " + part1Answer + "\nThe answer to part 2 are the letters from the picture");
			}
		}
		printPicture(points);
		return part1Answer;
	}

	private Set<Point> foldThePaper(Set<Point> points, String instructions) {
		String[] split = instructions.split("=");
		char axis = split[0].charAt(split[0].length() - 1);
		int pos = Integer.parseInt(split[1]);
		Set<Point> folded = new HashSet<>();
		for (var el : points) {
			el.fold(axis, pos);
			folded.add(el);
		}
		return folded;
	}

	private void printPicture(Set<Point> points) {
		int relativeWidth = 0;
		int relativeHeight = 0;
		int maxWidth = 0;
		int maxHeight = 0;
		for (var el : points) {
			if (el.getY() > maxHeight)
				maxHeight = el.getY();
			else if (el.getY() < relativeHeight)
				relativeHeight = el.getY();
			if (el.getX() > maxWidth)
				maxWidth = el.getX();
			else if (el.getX() < relativeWidth)
				relativeWidth = el.getX();
		}
		int width = maxWidth - relativeWidth + 1;
		int height = maxHeight - relativeHeight + 1;
		boolean[][] tab = new boolean[width][height];
		for (var el : points) {
			tab[el.getX() - relativeWidth][el.getY() - relativeHeight] = true;
		}
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (tab[i][j])
					System.out.print('\u2588');
				else
					System.out.print(' ');
			}
			System.out.println();
		}
	}
}