package aoc2021.Day03;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day03 implements IDay {
	public void day() {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(3));
		System.out.println("Day 3\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		assert data.peekFirst() != null;
		char[] maxBit = new char[data.peekFirst()
		                             .length()];
		int i1, i0;
		for (int i = 0; i < data.peekFirst()
		                        .length(); i++) {
			i0 = 0;
			i1 = 0;
			for (var el : data) {
				if (el.charAt(i) == '0')
					i0++;
				else
					i1++;
			}
			if (i0 > i1)
				maxBit[i] = '0';
			else
				maxBit[i] = '1';
		}
		int a = 0;
		int b = 0;
		for (int i = maxBit.length - 1; i >= 0; --i) {
			a += ((maxBit[i] - '0') * Math.pow(2, (maxBit.length - i - 1)));
			b += (('1' - maxBit[i]) * Math.pow(2, (maxBit.length - i - 1)));
		}
		return a * b;
	}

	int part2(List<String> oxygen) {
		List<String> carbon = List.copyOf(oxygen);
		assert oxygen.get(0) != null;
		long length = oxygen.get(0)
		                    .length();

		for (int i = 0; i < length && oxygen.size() > 1; ++i) {
			int finalI = i;
			long i0 = oxygen.stream()
			                .filter(el -> el.charAt(finalI) == '0')
			                .count();
			long i1 = oxygen.size() - i0;
			char bit = (i1 >= i0) ? '1' : '0';
			oxygen = oxygen.stream()
			               .filter(el -> el.charAt(finalI) == bit)
			               .collect(Collectors.toList());
		}
		for (int i = 0; i < length && carbon.size() > 1; ++i) {
			int finalI = i;
			long i0 = carbon.stream()
			                .filter(el -> el.charAt(finalI) == '0')
			                .count();
			long i1 = carbon.size() - i0;
			char bit = (i0 <= i1) ? '0' : '1';
			carbon = carbon.stream()
			               .filter(el -> el.charAt(finalI) == bit)
			               .collect(Collectors.toList());
		}
		int a = 0;
		int b = 0;
		for (int i = (int) length - 1; i >= 0; --i) {
			a += ((oxygen.get(0)
			             .charAt(i) - '0') * Math.pow(2, (length - i - 1)));
			b += ((carbon.get(0)
			             .charAt(i) - '0') * Math.pow(2, (length - i - 1)));
		}
		return a * b;
	}
}