package aoc2021.Day16;

import java.util.Arrays;
import java.util.LinkedList;
import aoc2021.own.functions.Calculator;

public class Message {
	public static char[] original;
	public static int iterator;
	public final char[] thisMessage;
	private final int mainVersion;
	private final int mainType;
	private final int debuggingLengthID;
	private final int value;
	private final LinkedList<Message> subMessages = new LinkedList<>();
	private final int stringPosUnlessCopyOfPrevious;

	public Message(char[] message) {
		this.thisMessage = message;
		this.mainVersion = Calculator.binaryToInt("" + original[iterator] + original[iterator + 1] + original[iterator + 2]);
		iterator += 3;
		this.stringPosUnlessCopyOfPrevious = Arrays.toString(original)
		                                           .indexOf(Arrays.toString(thisMessage));
		this.mainType = Calculator.binaryToInt("" + original[iterator] + original[iterator + 1] + original[iterator + 2]);
		iterator += 3;
		if (mainType == 4) {
			debuggingLengthID = -1;
			value = setValue();
		} else {
			debuggingLengthID = original[iterator];
			iterator += 1;
			value = 0;
		}
	}

	public int setValue() {
		int sum = 0;
		do {
			sum *= 16;
			sum += Calculator.binaryToInt("" + original[iterator + 1] + original[iterator + 2] + original[iterator + 3] + original[iterator + 4]);
			System.out.println(sum);
			iterator += 5;
		} while (original[iterator - 5] != '0');
		System.out.println(sum);
		return sum;
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

	public int getVersions() {
		int sum = 0;
		if (!subMessages.isEmpty()) {
			for (var el : subMessages) {
				sum += el.getVersions();
			}
		}
		sum += getMainVersion();
		return sum;
	}

	public int getValue() {
		return value;
	}

	public int getValues() {
		int sum = 0;
		if (!subMessages.isEmpty()) {
			for (var el : subMessages) {
				sum += el.getValues();
			}
		}
		sum += getValue();
		return sum;
	}

	private void printIdValue() {
		System.out.print(stringPosUnlessCopyOfPrevious);
		System.out.print(' ');
	}
}