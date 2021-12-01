package Day01;

import java.util.LinkedList;
import Functions.DataReader;
import static Functions.Calculator.arrayLongSum;

public interface Day01 {
	static void day01() {
		LinkedList<Long> sonarReports = DataReader.readLongArray(DataReader.createFilePath(1));
		System.out.println("\nThe answer to part 1 is " + part1(sonarReports));
		System.out.println("\nThe answer to part 2 is " + part2(sonarReports));
	}

	static int part1(LinkedList<Long> sonarReports) {
		int increases = 0;
		for (int i = 1; i < sonarReports.size(); i++) {
			if (sonarReports.get(i) > sonarReports.get(i - 1))
				increases++;
		}
		return increases;
	}

	static int part2(LinkedList<Long> sonarReports) {
		int tripleIncreases = 0;
		for (int i = 3; i < sonarReports.size(); i++) {
			if (arrayLongSum(sonarReports, 3, i) > arrayLongSum(sonarReports, 3, i - 1))
				tripleIncreases++;
		}
		return tripleIncreases;
	}
}
