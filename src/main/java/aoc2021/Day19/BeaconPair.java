package aoc2021.Day19;

public class BeaconPair {
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
}
