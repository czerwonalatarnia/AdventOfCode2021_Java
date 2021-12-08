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
}