package aoc2021.Day16;

import java.util.*;
import aoc2021.own.functions.Calculator;

public class Message {
	public static char[] original;
	public static int iterator;
	private final int mainVersion;
	private final int mainType;
	private final LinkedList<Message> subMessages = new LinkedList<>();
	private Integer value = null;

	public Message() {
		this.mainVersion = Calculator.binaryToInt("" + original[iterator] + original[iterator + 1] + original[iterator + 2]);
		iterator += 3;
		this.mainType = Calculator.binaryToInt("" + original[iterator] + original[iterator + 1] + original[iterator + 2]);
		iterator += 3;
		int debuggingLengthID, setting;
		if (mainType == 4) {
			setValue();
			debuggingLengthID = -1;
			setting = -1;
		} else {
			debuggingLengthID = original[iterator] - '0';
			iterator += 1;
			StringBuilder nope = new StringBuilder();
			if (debuggingLengthID == 0) {
				for (int i = 0; i < 15; i++)
					nope.append(original[iterator + i]);
				setting = Calculator.binaryToInt(String.valueOf(nope));
				iterator += 15;
			} else {
				for (int i = 0; i < 11; i++)
					nope.append(original[iterator + i]);
				setting = Calculator.binaryToInt(String.valueOf(nope));
				iterator += 11;
			}
		}
		if (iterator + 11 < original.length && debuggingLengthID == 0) {
			int startingIterator = iterator;
			while (iterator - startingIterator < setting)
				subMessages.add(new Message());
		} else if (iterator + 11 < original.length && debuggingLengthID == 1) {
			int counter = 0;
			while (counter < setting) {
				subMessages.add(new Message());
				counter++;
			}
		}
	}

	public void setValue() {
		int sum = 0;
		do {
			sum *= 16;
			sum += Calculator.binaryToInt("" + original[iterator + 1] + original[iterator + 2] + original[iterator + 3] + original[iterator + 4]);
			iterator += 5;
		} while (original[iterator - 5] != '0');
		value = sum;
	}

	public static void setOriginal(char[] origami) {
		original = origami;
	}

	public static void setIterator(int recursor) {
		iterator = recursor;
	}

	public int getMainVersion() {
		return mainVersion;
	}

	public int getMainType() {
		return mainType;
	}

	public int getVersions() {
		int sum = 0;
		for (var el : subMessages)
			sum += el.getVersions();
		sum += getMainVersion();
		return sum;
	}

	public Integer getValue() {
		return value;
	}

	public int getFinalValue() {
		int returnValue = 0;
		if (getMainType() == 4)
			return getValue();
		if (subMessages.contains(null)) {
			for (var el : subMessages) {
				if (el.getValue() == null)
					el.getFinalValue();
			}
		} else {
			List<Integer> elements = subMessages.stream()
			                                    .map(Message::getValue)
			                                    .toList();
			switch (getMainType()) {
				case 0 -> returnValue = elements.stream()
				                                .mapToInt(Integer::intValue)
				                                .sum();
				case 1 -> {
					returnValue = 1;
					for (var el : elements)
						returnValue *= el;
				}
				case 2 -> returnValue = elements.stream()
				                                .min(Integer::compareTo)
				                                .orElse(Integer.MAX_VALUE);
				case 3 -> returnValue = elements.stream()
				                                .max(Integer::compareTo)
				                                .orElse(Integer.MIN_VALUE);
				case 5 -> returnValue = elements.get(0) > elements.get(1) ? 1 : 0;
				case 6 -> returnValue = elements.get(0) < elements.get(1) ? 1 : 0;
				case 7 -> returnValue = Objects.equals(elements.get(0), elements.get(1)) ? 1 : 0;
			}
		}
		value = returnValue;
		return returnValue;
	}
}