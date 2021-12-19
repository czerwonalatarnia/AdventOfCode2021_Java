package aoc2021.Day19;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import aoc2021.own.objects.Point;

public class Day19 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(19));
		System.out.println("Day 19\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		LinkedList<SubScanner> scanners = new LinkedList<>();
		for (var el : data) {
			int index = el.indexOf('r') + 2;
			if (el.charAt(1) == '-') {
				if (el.charAt(index + 1) == ' ')
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)))));
				else
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)) + el.charAt(index + 1))));
			} else {
				assert scanners.peekLast() != null;
				scanners.peekLast()
				        .addBeacon(el);
			}

		}
		for (var el : scanners)
			el.fillMatrix();
		compareScanners(scanners.get(0), scanners.get(1));
		return 0;
	}

	long part2(LinkedList<String> data) {
		return 0;
	}

	private void compareScanners(SubScanner scan1, SubScanner scan2) {
		for (int i = 0; i < scan1.getSize(); i++) {
			for (int j = 0; j < scan2.getSize(); j++) {
				System.out.println(Arrays.toString(scan1.distanceBeacon(i)));
				System.out.println(Arrays.toString(scan2.distanceBeacon(j)));
				System.out.println();
			}
		}
	}
}