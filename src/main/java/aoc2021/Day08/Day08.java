package aoc2021.Day08;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import aoc2021.IDay;
import aoc2021.own.exception.FileIsEmpty;
import aoc2021.own.functions.Calculator;
import aoc2021.own.functions.DataReader;

public class Day08 implements IDay {
	public void day() throws FileIsEmpty, IOException {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(8));
		LinkedList<String> data2 = DataReader.readAlchemyString(DataReader.createFilePath(8));
		System.out.println("Day 8\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data2));
	}

	long part1(LinkedList<String> data) {
		return Arrays.stream(data.stream()
		                         .map(string -> string.substring(string.lastIndexOf('|') + 1))
		                         .collect(Collectors.joining())
		                         .trim()
		                         .split("\\s+"))
		             .filter(el -> el.length() < 5 || el.length() > 6)
		             .count();
	}

	long part2(LinkedList<String> data) {
		LinkedList<Long> number = new LinkedList<>();
		for (var line : data) {
			Display display = new Display();
			String[] numbers = line.substring(0, line.indexOf('|'))
			                       .trim()
			                       .split("\\s+");
			String[] decode = line.substring(line.indexOf('|') + 1)
			                      .trim()
			                      .split("\\s+");

			fillDisplay(numbers, display);
			long temp = 0;
			for (int i = 0; i < 4; i++) {
				temp += display.getNumber(decode[i]) * Math.pow(10, 3 - i);
			}
			number.add(temp);
		}
		return Calculator.arrayLongSum(number);
	}

	/**
	 * <p>Very, very, very long line of code (after de-wrapping it) created solely because day before this puzzle unlocked
	 * I had a lecture about {@link java.util.stream.Stream} and I wanted to see "how much" of today's code I can put
	 * into a {@code stream()}.</p>
	 * <p>{@code Arrays.stream(array).peek(el -> {})}, {@code .collect(Collectors.toList()).stream().peek(el -> {})}
	 * and {@code .collect(Collectors.toList()).forEach(el -> {})} are each simulating one {@code foreach} loop,
	 * which would be iterating over either the String array (in first case) or Lists created by {@code .collect(Collectors.toList())}.</p>
	 * <p>{@code .filter()} is being used to remove elements which no longer need to pass to further "loops".</p>
	 */

	private void fillDisplay(String[] numbers, Display display) {
		char[] one = new char[2];
		char[] four = new char[4];
		char[] six = new char[6];
		char[] three = new char[5];
		AtomicBoolean ifContainOne = new AtomicBoolean(false);
		AtomicBoolean ifContainFour = new AtomicBoolean(false);
		Arrays.stream(numbers)
		      .peek(string -> {
			      if (string.length() == 2) {
				      display.matchStringAndNumber(string, 1);
				      one[0] = string.charAt(0);
				      one[1] = string.charAt(1);
			      } else if (string.length() == 3)
				      display.matchStringAndNumber(string, 7);
			      else if (string.length() == 4) {
				      display.matchStringAndNumber(string, 4);
				      four[0] = string.charAt(0);
				      four[1] = string.charAt(1);
				      four[2] = string.charAt(2);
				      four[3] = string.charAt(3);
			      } else if (string.length() == 7)
				      display.matchStringAndNumber(string, 8);
		      })
		      .filter(string -> string.length() != 2 && string.length() != 3 && string.length() != 4 && string.length() != 7)
		      .collect(Collectors.toList())
		      .stream()
		      .peek(string -> {
			      ifContainOne.set(string.contains("" + one[0]) && string.contains("" + one[1]));
			      ifContainFour.set(string.contains("" + four[0]) && string.contains("" + four[1]) && string.contains("" + four[2]) && string.contains("" + four[3]));
			      if (string.length() == 5) {
				      if (ifContainOne.get()) {
					      display.matchStringAndNumber(string, 3);
					      three[0] = string.charAt(0);
					      three[1] = string.charAt(1);
					      three[2] = string.charAt(2);
					      three[3] = string.charAt(3);
					      three[4] = string.charAt(4);
				      }
			      } else if (string.length() == 6) {
				      if (!ifContainOne.get()) {
					      display.matchStringAndNumber(string, 6);
					      six[0] = string.charAt(0);
					      six[1] = string.charAt(1);
					      six[2] = string.charAt(2);
					      six[3] = string.charAt(3);
					      six[4] = string.charAt(4);
					      six[5] = string.charAt(5);
				      } else if (ifContainFour.get())
					      display.matchStringAndNumber(string, 9);
				      else
					      display.matchStringAndNumber(string, 0);
			      }
		      })
		      .filter(string -> {
			      ifContainOne.set(string.contains("" + one[0]) && string.contains("" + one[1]));
			      ifContainFour.set(string.contains("" + four[0]) && string.contains("" + four[1]) && string.contains("" + four[2]) && string.contains("" + four[3]));
			      if (string.length() == 5) {
				      return !string.contains("" + one[0]) || !string.contains("" + one[1]);
			      } else
				      return string.length() != 6;
		      })
		      .collect(Collectors.toList())
		      .forEach(string -> {
			      int count = 0;
			      if (string.length() == 5) {
				      for (char c : six) {
					      if (string.contains("" + c))
						      count++;
				      }
				      if (count == 5)
					      display.matchStringAndNumber(string, 5);
				      else if (!(new String(three).equals(string)))
					      display.matchStringAndNumber(string, 2);
			      }
		      });
	}
}
