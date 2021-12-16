package aoc2021.own.functions;

import java.util.List;

/**
 * Class created for easy reference to methods hidden in the bodies of functions.
 */

public interface Calculator {

	/**
	 * <p>Sums all the elements in {@link List}<{@code long}> and returns the result.</p>
	 */

	static long arrayLongSum(List<Long> array) {
		return array.stream()
		            .reduce(0L, Long::sum);
	}

	/**
	 * <p>Sums the {@code n} subsequent elements in {@link List}<{@code long}>, with the first of them on position {@code pos}, and returns the result.</p>
	 */

	static long arrayLongSum(List<Long> array, int n, int pos) {
		return array.subList(pos, pos + n)
		            .stream()
		            .reduce(0L, Long::sum);
	}

	/**
	 * <p>Sums all the elements in {@link List}<{@code double}> and returns the result.</p>
	 */

	static double arrayDoubleSum(List<Double> array) {
		return array.stream()
		            .reduce(0d, Double::sum);
	}

	/**
	 * <p>Sums the {@code n} subsequent elements in {@link List}<{@code double}>, with the first of them on position {@code pos}, and returns the result.</p>
	 */

	static double arrayDoubleSum(List<Double> array, int n, int pos) {
		return array.subList(pos, pos + n)
		            .stream()
		            .reduce(0d, Double::sum);
	}

	/**
	 * <p>Finds the biggest element of {@link List}<{@code long}> and returns its value.</p>
	 */

	static long arrayLongMax(List<Long> array) {
		return array.stream().max(Long::compareTo).orElseThrow();
	}

	/**
	 * <p>Finds the smallest element of {@link List}<{@code long}> and returns its value.</p>
	 */

	static long arrayLongMin(List<Long> array) {
		return array.stream().min(Long::compareTo).orElseThrow();
	}

	/**
	 * <p>Finds the biggest element of {@link List}<{@code double}> and returns its value.</p>
	 */

	static double arrayDoubleMax(List<Double> array) {
		return array.stream().max(Double::compareTo).orElseThrow();
	}

	/**
	 * <p>Finds the smallest element of {@link List}<{@code double}> and returns its value.</p>
	 */

	static double arrayDoubleMin(List<Double> array) {
		return array.stream().min(Double::compareTo).orElseThrow();
	}

	static int binaryToInt(String binary) {
		int length = binary.length();
		int sum = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (binary.charAt(i) == '1') {
				sum += Math.pow(2, length - 1  - i);
			}
		} return sum;
	}

	static String hexToBinString(String hex) {
		StringBuilder bin = new StringBuilder();
		for (int i = 0; i < hex.length(); i++){
			switch (hex.charAt(i)) {
				case '0' -> bin.append("0000");
				case '1' -> bin.append("0001");
				case '2' -> bin.append("0010");
				case '3' -> bin.append("0011");
				case '4' -> bin.append("0100");
				case '5' -> bin.append("0101");
				case '6' -> bin.append("0110");
				case '7' -> bin.append("0111");
				case '8' -> bin.append("1000");
				case '9' -> bin.append("1001");
				case 'A', 'a' -> bin.append("1010");
				case 'B', 'b' -> bin.append("1011");
				case 'C', 'c' -> bin.append("1100");
				case 'D', 'd' -> bin.append("1101");
				case 'E', 'e' -> bin.append("1110");
				case 'F', 'f' -> bin.append("1111");
			}
		}
		return String.valueOf(bin);
	}
}