package aoc2021.Day16;

import aoc2021.IDay;
import aoc2021.own.functions.Calculator;
import aoc2021.own.functions.DataReader;

public class Day16 implements IDay {
	public void day() {
		String data = DataReader.readSimpleString(DataReader.createFilePath(16));
		System.out.println("Day 16\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(String data) {
		data = Calculator.hexToBinString(data);
		char[] dataButCharArray = data.toCharArray();
		Message.setOriginal(dataButCharArray);
		Message.setIterator(0);
		Message message = new Message();
		return message.getVersions();
	}

	int part2(String data) {
		data = Calculator.hexToBinString(data);
		char[] dataButCharArray = data.toCharArray();
		Message.setOriginal(dataButCharArray);
		Message.setIterator(0);
		Message message = new Message();
		System.out.println();
		return message.getFinalValue();
	}
}