package Day01;

import java.util.LinkedList;
import Functions.DataReader;
import static Functions.Calculator.arrayLongSum;

public class Day01 {
	public static void main(String[] args) {
		LinkedList<Long> sonarReports = DataReader.readLongArray(1);

		// Part 1

		int increases = 0;
		for (int i = 1; i < sonarReports.size(); i++) {
			if (sonarReports.get(i) > sonarReports.get(i - 1))
				increases++;
		}
		System.out.println("The answer to part 1 is " + increases);

		// Part 2

		int tripleIncreases = 0;
		for (int i = 3; i < sonarReports.size(); i++) {
			if (arrayLongSum(sonarReports, 3, i) > arrayLongSum(sonarReports, 3, i - 1))
				tripleIncreases++;
		}
		System.out.println("\nThe answer to part 2 is " + tripleIncreases);
	}
}
