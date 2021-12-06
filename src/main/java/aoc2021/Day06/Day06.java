package aoc2021.Day06;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day06 implements IDay {
	public void day() {
		LinkedList<Long> data = DataReader.readLongArray(DataReader.createFilePath(6));
		System.out.println("Day 6\nThe answer to part 1 is " + breedTheFish(data, 80) + "\nThe answer to part 2 is " + breedTheFish(data, 256));
	}

	long breedTheFish(LinkedList<Long> data, int days) {
		if (days == 0)
			return 0;
		long sum = 0;
		long[] lanternfish = new long[9];
		for (var el : data)
			lanternfish[el.intValue()]++;
		for (int day = 0; day < days; day++) {
			long temp = lanternfish[0];
			for (int i = 1; i < lanternfish.length; i++)
				lanternfish[i - 1] = lanternfish[i];
			lanternfish[6] += temp;
			lanternfish[8] = temp;
		}
		for (long l : lanternfish)
			sum += l;
		return sum;
	}
}
