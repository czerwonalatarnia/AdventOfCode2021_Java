package aoc2021.Day19;

import java.util.Objects;

public class ScannerPair implements Comparable<ScannerPair> {
	private final int firstScanner;
	private final int secondScanner;

	public ScannerPair(int firstScanner, int secondScanner) {
		this.firstScanner = firstScanner;
		this.secondScanner = secondScanner;
	}

	public int getFirstScanner() {
		return firstScanner;
	}

	public int getSecondScanner() {
		return secondScanner;
	}

	@Override public int hashCode() {
		return Objects.hash(firstScanner, secondScanner);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScannerPair that = (ScannerPair) o;
		return firstScanner == that.firstScanner && secondScanner == that.secondScanner;
	}

	@Override public int compareTo(ScannerPair o) {
		if (firstScanner == o.firstScanner && secondScanner == o.secondScanner)
			return 0;
		else if (firstScanner == o.firstScanner) {
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