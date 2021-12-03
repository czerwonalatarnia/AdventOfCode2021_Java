package aoc2021.Day03;

import aoc2021.IDay;
import java.util.LinkedList;
import aoc2021.own.functions.DataReader;

public class Day03 implements IDay  {
	public void day() throws Exception {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(3));
		System.out.println("Day 3\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		String maxBit = "";
		int i1, i0;
		for (int i = 0; i < data.peekFirst().length(); i++) {
			i0 = 0;
			i1 = 0;
			for (var el : data) {
				if (el.charAt(i) == '0')
					i0++;
				else
					i1++;
			}
			if (i0 > i1)
				maxBit += "0";
			else
				maxBit += "1";
		}
		int a = 0;
		int b = 0;
		for (int i = maxBit.length() - 1; i >= 0; --i) {
			a += ((maxBit.charAt(i) - '0') * Math.pow(2, (maxBit.length() - i - 1)));
			b += (('1' - maxBit.charAt(i)) * Math.pow(2, (maxBit.length() - i - 1)));
		}
		return a * b;
	}

	int part2(LinkedList<String> data) {
		String maxBit = "";
		String minBit = "";
		LinkedList<String> oxygen = (LinkedList<String>) data.clone();
		LinkedList<String> data2 = (LinkedList<String>) data.clone();
		LinkedList<String> carbon = (LinkedList<String>) data.clone();
		int i1, i0;
		for (int i = 0; i < data.peekFirst().length(); i++) {
			i0 = 0;
			i1 = 0;
			for (var el : data) {
				if (el.charAt(i) == '0')
					i0++;
				else
					i1++;
			}
			if (i0 > i1) {
				maxBit += "0";
				minBit += "1";
			} else {
				maxBit += "1";
				minBit += "0";
			}
		}
		for (int i = 0; i < maxBit.length()  && oxygen.size() > 1; ++i) {
			i0 = 0;
			i1 = 0;
			for (var el : data) {
				if (el.charAt(i) == '0')
					i0++;
				else
					i1++;
			}
			if (i > 0) {
				if (i0 > i1) {
					maxBit = maxBit.substring(0,i)+"0"+maxBit.substring(i+1);
				} else {
					maxBit = maxBit.substring(0,i)+"1"+maxBit.substring(i+1);
				}
			} else {
				if (i0 > i1) {
					maxBit = "0"+maxBit.substring(i+1);
				} else {
					maxBit = "1"+maxBit.substring(i+1);
				}
			}
			for (int j = 0; j < oxygen.size(); ++j) {

				if (oxygen.get(j).charAt(i) != maxBit.charAt(i)) {
					oxygen.remove(j);
					j--;
				}
			}
			data = (LinkedList<String>) oxygen.clone();
		}
		for (int i = 0; i < minBit.length() && carbon.size() > 1; ++i) {
			i0 = 0;
			i1 = 0;
			for (var el : data2) {
				if (el.charAt(i) == '0')
					i0++;
				else
					i1++;
			}
			if (i > 0) {
				if (i0 <= i1) {
					minBit = minBit.substring(0,i)+"0"+minBit.substring(i+1);
				} else {
					minBit = minBit.substring(0,i)+"1"+minBit.substring(i+1);
				}
			} else {
				if (i0 <= i1) {
					minBit = "0"+minBit.substring(i+1);
				} else {
					minBit = "1"+minBit.substring(i+1);
				}
			}
			for (int j = 0; j < carbon.size(); ++j) {
				if (carbon.get(j).charAt(i) != minBit.charAt(i)) {
					carbon.remove(j);
					j--;
				}
			}
			data2 = (LinkedList<String>) carbon.clone();
		}
		int a = 0;
		int b = 0;
		for (int i = maxBit.length() - 1; i >= 0; --i) {
			a += ((oxygen.get(0).charAt(i) - '0') * Math.pow(2, (maxBit.length() - i -1)));
			b += ((carbon.get(0).charAt(i) - '0') * Math.pow(2, (maxBit.length() - i-1)));
		}
		System.out.println(oxygen);
		System.out.println(a + " - " + b);
		return a * b;
	}
}
