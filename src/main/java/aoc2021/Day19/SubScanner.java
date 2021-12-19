package aoc2021.Day19;

import java.util.LinkedList;
import aoc2021.own.objects.Point;

public class SubScanner {
	private final int number;
	private final LinkedList<Point> beacons = new LinkedList<>();
	private final Point scannerPosition = new Point(0, 0, 0);
	private boolean oriented = false;
	private char first = 'x';
	private boolean firstReverse = false;
	private char second = 'y';
	private boolean secondReverse = false;
	private char third = 'z';
	private boolean thirdReverse = false;
	private int size = 0;
	private int[][] distanceMatrix;

	public SubScanner(int number) {
		this.number = number;
		if (number == 0) {
			oriented = true;
		}
	}

	public void setFirst(char first) {
		this.first = first;
	}

	public void setFirstReverse(boolean firstReverse) {
		this.firstReverse = firstReverse;
	}

	public void setSecond(char second) {
		this.second = second;
	}

	public void setSecondReverse(boolean secondReverse) {
		this.secondReverse = secondReverse;
	}

	public void setThird(char third) {
		this.third = third;
	}

	public void setThirdReverse(boolean thirdReverse) {
		this.thirdReverse = thirdReverse;
	}

	public boolean isOriented() {
		return oriented;
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

	public Point getBeacon(int i) {
		return getBeacons().get(i);
	}

	public LinkedList<Point> getBeacons() {
		return beacons;
	}

	public void fillMatrix() {
		distanceMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				distanceMatrix[i][j] = Math.abs(beacons.get(i)
				                                       .getX() - beacons.get(j)
				                                                        .getX()) + Math.abs(beacons.get(i)
				                                                                                   .getY() - beacons.get(j)
				                                                                                                    .getY()) + Math.abs(beacons.get(i)
				                                                                                                                               .getZ() - beacons.get(j)
				                                                                                                                                                .getZ());
				distanceMatrix[j][i] = distanceMatrix[i][j];
			}
		}
	}

	public int[] distanceBeacon(int i) {
		return distanceMatrix[i];
	}

	public int[] distanceBeaconSorted(int i) {
		LinkedList<Integer> sortDistances = new LinkedList<>();
		for (int j = 0; j < size; j++) {
			if (j != i)
				sortDistances.add(distance(i, j));
		}
		return sortDistances.stream()
		                    .mapToInt(Integer::intValue)
		                    .toArray();
	}

	public int distance(int i, int j) {
		return distanceMatrix[i][j];
	}

	public String orientation() {
		String xStr, yStr, zStr;
		if (firstReverse)
			xStr = "-" + first;
		else
			xStr = String.valueOf(first);
		if (secondReverse)
			yStr = "-" + second;
		else
			yStr = String.valueOf(second);
		if (thirdReverse)
			zStr = "-" + third;
		else
			zStr = String.valueOf(third);
		return "(" + xStr + ", " + yStr + ", " + zStr + ")";
	}

	public void addBeacon(String beacon) {
		int x = Integer.parseInt(beacon.substring(0, beacon.indexOf(',')));
		int y = Integer.parseInt(beacon.substring(beacon.indexOf(',') + 1, beacon.lastIndexOf(',')));
		int z = Integer.parseInt(beacon.substring(beacon.lastIndexOf(',') + 1));
		beacons.add(new Point(x, y, z));
		size++;
	}
}