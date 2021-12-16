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
	private final int stringPosUnlessCopyOfPrevious;
	private Message nextMessages = null;

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
			debuggingLengthID = original[iterator] - '0';
			iterator += 1;
			if (debuggingLengthID == 0)
				iterator += 15;
			else
				iterator += 11;
			value = 0;
		}
		if (iterator + 11 < original.length)
			nextMessages = new Message(Arrays.copyOfRange(original, iterator, original.length));
	}

	public int setValue() {
		int sum = 0;
		do {
			sum *= 16;
			sum += Calculator.binaryToInt("" + original[iterator + 1] + original[iterator + 2] + original[iterator + 3] + original[iterator + 4]);
			iterator += 5;
		} while (original[iterator - 5] != '0');
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
		if (nextMessages != null)
			sum += nextMessages.getVersions();
		sum += getMainVersion();
		return sum;
	}

	public int getValue() {
		return value;
	}

	public int getValues() {
		int sum = 0;
		if (nextMessages != null)
			sum += nextMessages.getValues();
		sum += getValue();
		return sum;
	}

	private void printIdValue() {
		System.out.print(stringPosUnlessCopyOfPrevious);
		System.out.print(' ');
	}
}