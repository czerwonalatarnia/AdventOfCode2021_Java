package aoc2021.Day19;

import java.util.HashSet;
import java.util.LinkedList;
import aoc2021.own.objects.Point;

public class SubScanner {
	private final int number;
	private final LinkedList<Point> beacons = new LinkedList<>();
	private final HashSet<Integer> linkedScanners = new HashSet<>();
	private int size = 0;
	private int[][] distanceMatrix;

	public SubScanner(int number) {
		this.number = number;
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

	public void addLinked(int scanner) {
		linkedScanners.add(scanner);
	}

	public void addBeacon(String beacon) {
		int x = Integer.parseInt(beacon.substring(0, beacon.indexOf(',')));
		int y = Integer.parseInt(beacon.substring(beacon.indexOf(',') + 1, beacon.lastIndexOf(',')));
		int z = Integer.parseInt(beacon.substring(beacon.lastIndexOf(',') + 1));
		beacons.add(new Point(x, y, z));
		size++;
	}
}
