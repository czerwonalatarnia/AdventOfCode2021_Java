package Functions;

import java.util.Collection;

public interface Calculator {

	/**
	 * <p>Takes as the argument {@link Collection} of {@code long} elements and returns the sum of all of them.</p>
	 */

	static long arrayLongSum(Collection<Long> array) {
		long sum = 0;
		for (var el : array) {
			sum += el;
		}
		return sum;
	}

	/**
	 * <p>Takes as the argument {@link Collection} of {@code double} elements and returns the sum of all of them.</p>
	 */

	static double arrayDoubleSum(Collection<Double> array) {
		double sum = 0;
		for (var el : array) {
			sum += el;
		}
		return sum;
	}

	/**
	 * <p>Finds the biggest element of {@link Collection}{@code <long>} and returns its value.</p>
	 */

	static long arrayLongMax(Collection<Long> array) {
		long max = Long.MIN_VALUE;
		for (var el : array) {
			if (el > max)
				max = el;
		}
		return max;
	}

	/**
	 * <p>Finds the smallest element of {@link Collection}{@code <long>} and returns its value.</p>
	 */

	static long arrayLongMin(Collection<Long> array) {
		long min = Long.MAX_VALUE;
		for (var el : array) {
			if (el < min)
				min = el;
		}
		return min;
	}

	/**
	 * <p>Finds the biggest element of {@link Collection}{@code <double>} and returns its value.</p>
	 */

	static double arrayDoubleMax(Collection<Double> array) {
		double max = Double.MIN_VALUE;
		for (var el : array) {
			if (el > max)
				max = el;
		}
		return max;
	}

	/**
	 * <p>Finds the smallest element of {@link Collection}{@code <double>} and returns its value.</p>
	 */

	static double arrayDoubleMin(Collection<Double> array) {
		double min = Double.MAX_VALUE;
		for (var el : array) {
			if (el < min)
				min = el;
		}
		return min;
	}
}