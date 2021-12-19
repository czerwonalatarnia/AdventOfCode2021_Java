package aoc2021.Day19;

import java.util.Arrays;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

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

	private LinkedList<BeaconPair> compareScanners(SubScanner scan1, SubScanner scan2) {
		LinkedList<BeaconPair> linked = new LinkedList<>();
		for (int i = 0; i < scan1.getSize(); i++) {
			for (int j = 0; j < scan2.getSize(); j++) {
				int counter = 0;
				for (int it = 0; it < scan1.distanceBeacon(i).length; it++) {
					for (int jt = 0; jt < scan2.distanceBeacon(j).length; jt++) {
						if (scan1.distanceBeacon(i)[it] == scan2.distanceBeacon(j)[jt]) {
							counter++;
							break;
						}
					}
					if (counter > 6) {
						linked.add(new BeaconPair(scan1.getNumber(), i, scan1.getNumber(), j));
						System.out.println("LINKED: scanner " + scan1.getNumber() + ": " + i + " and scanner" +  scan2.getNumber()+ ": " +j);
						System.out.println("\tAmount of linked beacons: " + linked.size());
						break;
					}
				}
				//System.out.println(Arrays.toString(scan1.distanceBeacon(i)));
				//System.out.println(Arrays.toString(scan2.distanceBeacon(j)));
				//System.out.println();
			}
		}
		if (linked.size() >= 12) {
			scan1.addLinked(scan2.getNumber());
			scan2.addLinked(scan1.getNumber());
		}
		return linked;
	}
}