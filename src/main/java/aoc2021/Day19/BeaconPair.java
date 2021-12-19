package aoc2021.Day19;

import java.util.Objects;

public class BeaconPair implements Comparable<BeaconPair> {
	private final int firstScanner;
	private final int firstBeacon;
	private final int secondScanner;
	private final int secondBeacon;

	public BeaconPair(int firstScanner, int firstBeacon, int secondScanner, int secondBeacon) {
		this.firstScanner = firstScanner;
		this.firstBeacon = firstBeacon;
		this.secondScanner = secondScanner;
		this.secondBeacon = secondBeacon;
	}

	public BeaconPair swap() {
		return new BeaconPair(secondScanner, secondBeacon, firstScanner, firstBeacon);
	}

	public int getFirstScanner() {
		return firstScanner;
	}

	public int getFirstBeacon() {
		return firstBeacon;
	}

	public int getSecondScanner() {
		return secondScanner;
	}

	public int getSecondBeacon() {
		return secondBeacon;
	}

	@Override public int hashCode() {
		return Objects.hash(firstScanner, firstBeacon, secondScanner, secondBeacon);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BeaconPair that = (BeaconPair) o;
		return firstScanner == that.firstScanner && firstBeacon == that.firstBeacon && secondScanner == that.secondScanner && secondBeacon == that.secondBeacon;
	}

	@Override public int compareTo(BeaconPair o) {
		if (firstScanner == o.firstScanner && secondScanner == o.secondScanner && firstBeacon == o.firstBeacon && secondBeacon == o.secondBeacon)
			return 0;
		else if (firstScanner == o.firstScanner && secondScanner == o.secondScanner && firstBeacon == o.firstBeacon) {
			if (secondBeacon > o.secondBeacon)
				return 1;
			else
				return -1;
		} else if (firstScanner == o.firstScanner && secondScanner == o.secondScanner) {
			if (firstBeacon > o.firstBeacon)
				return 1;
			else
				return -1;
		} else if (firstScanner == o.firstScanner) {
			if (secondScanner > o.secondScanner)
				return 1;
			else
				return -1;
		} else {
			if (firstScanner > o.firstScanner)
				return 1;
			else
				return -1;
		}
	}
}