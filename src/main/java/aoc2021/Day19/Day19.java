package aoc2021.Day19;

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
				if (el.charAt(el.indexOf('r') + 1) == ' ')
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)))));
				else
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)) + el.charAt(index + 1))));
			} else {
				assert scanners.peekLast() != null;
				scanners.peekLast()
				        .addBeacon(el);
			}

		}
		for (var el : scanners) {
			System.out.println(el.getData());
		}
		compareScanners(scanners.get(0), scanners.get(1));
		return 0;
	}

	long part2(LinkedList<String> data) {
		return 0;
	}

	private void compareScanners(SubScanner scan1, SubScanner scan2) {
		PriorityQueue<Point> firstBeacons = new PriorityQueue<>();
		PriorityQueue<Point> secondBeacons = new PriorityQueue<>();
		for (int i = 0; i < scan1.getSize(); i++)
			firstBeacons.offer(scan1.getBeacons()
			                        .get(i));
		for (int i = 0; i < scan2.getSize(); i++)
			secondBeacons.offer(scan2.getBeacons()
			                         .get(i));
		PriorityQueue<Point> substract = new PriorityQueue<>();
		for (int i = 0; i < scan1.getSize() && i < scan2.getSize(); i++) {
			Point A = firstBeacons.poll();
			Point B = secondBeacons.poll();
			substract.offer(new Point(A.getX() - B.getX(), A.getY() + B.getY(), A.getZ() - B.getZ()));
		}
		for (int i = 0; i < substract.size(); )
			System.out.println(substract.poll());
	}
}