package Functions;

import java.util.List;

public interface Calculator {

	/**
	 * <p>Takes as the argument {@link List} of {@code long} elements and returns the sum of all of them.</p>
	 */

	static long arrayLongSum(List<Long> array) {
		long sum = 0;
		for (var el : array) {
			sum += el;
		}
		return sum;
	}

	/**
	 * <p>Sums the {@code n} subsequent elements in {@link List}{@code <long>}, with the last of them on position {@code pos}, and returns the sum.</p>
	 */

	static long arrayLongSum(List<Long> array, int n, int pos) {
		long sum = 0;
		for (int i = pos; i > pos - n; i--) {
			sum += array.get(i);
		}
		return sum;
	}

	/**
	 * <p>Takes as the argument {@link List} of {@code double} elements and returns the sum of all of them.</p>
	 */

	static double arrayDoubleSum(List<Double> array) {
		double sum = 0;
		for (var el : array) {
			sum += el;
		}
		return sum;
	}

	/**
	 * <p>Sums the {@code n} subsequent elements in {@link List}{@code <double>}, with the last of them on position {@code pos}, and returns the sum.</p>
	 */

	static double arrayDoubleSum(List<Double> array, int n, int pos) {
		double sum = 0;
		for (int i = pos; i >= pos - n; i--) {
			sum += array.get(i);
		}
		return sum;
	}

	/**
	 * <p>Finds the biggest element of {@link List}{@code <long>} and returns its value.</p>
	 */

	static long arrayLongMax(List<Long> array) {
		long max = Long.MIN_VALUE;
		for (var el : array) {
			if (el > max)
				max = el;
		}
		return max;
	}

	/**
	 * <p>Finds the smallest element of {@link List}{@code <long>} and returns its value.</p>
	 */

	static long arrayLongMin(List<Long> array) {
		long min = Long.MAX_VALUE;
		for (var el : array) {
			if (el < min)
				min = el;
		}
		return min;
	}

	/**
	 * <p>Finds the biggest element of {@link List}{@code <double>} and returns its value.</p>
	 */

	static double arrayDoubleMax(List<Double> array) {
		double max = Double.MIN_VALUE;
		for (var el : array) {
			if (el > max)
				max = el;
		}
		return max;
	}

	/**
	 * <p>Finds the smallest element of {@link List}{@code <double>} and returns its value.</p>
	 */

	static double arrayDoubleMin(List<Double> array) {
		double min = Double.MAX_VALUE;
		for (var el : array) {
			if (el < min)
				min = el;
		}
		return min;
	}
}