package aoc2021.Day19;

import java.util.LinkedList;
import aoc2021.own.objects.Point;

public class SubScanner {
	private final int number;
	private final LinkedList<Point> beacons = new LinkedList<>();
	private int size = 0;

	public SubScanner(int number) {
		this.number = number;
	}

	public LinkedList<Point> getBeacons() {
		return beacons;
	}

	public void addBeacon(String beacon) {
		int x = Integer.parseInt(beacon.substring(0, beacon.indexOf(',')));
		int y = Integer.parseInt(beacon.substring(beacon.indexOf(',') + 1, beacon.lastIndexOf(',')));
		int z = Integer.parseInt(beacon.substring(beacon.lastIndexOf(',') + 1));
		beacons.add(new Point(x, y, z));
		size++;
	}

	public String getData() {
		return "Scanner " + getNumber() + ": " + getSize() + " beacons.";
	}

	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}
}
