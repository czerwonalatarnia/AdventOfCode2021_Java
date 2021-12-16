package aoc2021.Day16;

import java.util.LinkedList;
import aoc2021.own.functions.Calculator;

public class Message {
	private final int mainVersion;
	private final int mainType;
	private final int value;
	private LinkedList<Message> subMessages = new LinkedList<>();

	public Message(String message) {
		mainVersion = Calculator.binaryToInt(message.substring(0, 3));
		mainType = Calculator.binaryToInt(message.substring(3, 6));
		int iterator, lengthType;
		if (mainType == 4) {
			iterator = 6;
			lengthType = -1;
		} else {
			iterator = 7;
			lengthType = message.charAt(6) - '0';
		}
		System.out.println("Creating message, lenghtType = " + lengthType + "\n\t\t" + message);
		String restOfMessage = message.substring(iterator);
		if (lengthType == 0) {
			value = 0;
			subMessages = setSubMessages(restOfMessage.substring(15));
		} else if (lengthType == 1) {
			value = 0;
			int amount = Calculator.binaryToInt(restOfMessage.substring(0, 11));
			subMessages = setSubMessages(restOfMessage.substring(11), amount);
		} else {
			int sum = 0;
			for (int i = 0; i < restOfMessage.length(); i += 5) {
				sum += Calculator.binaryToInt(restOfMessage.substring(i + 1, i + 5));
				if (restOfMessage.charAt(i) == '0') {
					break;
				} else
					sum *= 16;
			}
			value = sum;
		}
	}

	private LinkedList<Message> setSubMessages(String message) {
		System.out.println();
		LinkedList<Message> messages = new LinkedList<>();
		while (message.length() > 11) {
			System.out.println(message.length() + ", " + message);
			StringBuilder subMessage = new StringBuilder();
			int type = Calculator.binaryToInt(message.substring(3, 6));
			int lengthType;
			if (type == 4)
				lengthType = -1;
			else
				lengthType = message.charAt(6) - '0';
			switch (lengthType) {
				case -1 -> {
					subMessage.append(message, 0, 6);
					message = message.substring(6);
					System.out.println("Cut start\t" + message);
					int i = 0;
					for (; i < message.length(); i += 5) {
						if (message.charAt(i) == '0') {
							i += 5;
							subMessage.append(message, 0, i);
							message = message.substring(i);
							break;
						}
					}
					System.out.println("Prepared\t" + message);
				}
				case 0 -> {
					subMessage.append(message, 0, 22);
					int length = Calculator.binaryToInt(message.substring(6, 22));
					message = message.substring(22);
					System.out.println("Cut start\t" + message);
					subMessage.append(message, 0, length);
					message = message.substring(length);
					System.out.println("Prepared\t" + message);
				}
				case 1 -> {
					subMessage.append(message, 0, 18);
					message = message.substring(18);
					System.out.println("Cut start\t" + message);
					int i = 0;
					for (; i < message.length(); i += 5) {
						if (message.charAt(i) == '0') {
							i += 5;
							subMessage.append(message, 0, i);
							message = message.substring(i);
							break;
						}
					}
					System.out.println("Prepared\t" + message);
				}
			}
			System.out.println("Will create\t" + subMessage);
			messages.add(new Message(String.valueOf(subMessage)));
			System.out.println("Created\t" + message + '\n');
		}
		return messages;
	}

	private LinkedList<Message> setSubMessages(String message, int amount) {
		System.out.println();
		LinkedList<Message> messages = new LinkedList<>();
		while (amount > 0 && message.length() > 11) {
			System.out.println(amount + " = amount, " + message);
			amount--;
			StringBuilder subMessage = new StringBuilder();
			int type = Calculator.binaryToInt(message.substring(3, 6));
			int lengthType;
			if (type == 4)
				lengthType = -1;
			else
				lengthType = message.charAt(6) - '0';
			switch (lengthType) {
				case -1 -> {
					subMessage.append(message, 0, 6);
					message = message.substring(6);
					System.out.println("Cut start\t" + message);
					int i = 0;
					for (; i < message.length(); i += 5) {
						if (message.charAt(i) == '0') {
							i += 5;
							subMessage.append(message, 0, i);
							message = message.substring(i);
							break;
						}
					}
					System.out.println("Prepared\t" + message);
				}
				case 0 -> {
					subMessage.append(message, 0, 22);
					int length = Calculator.binaryToInt(message.substring(6, 22));
					message = message.substring(22);
					System.out.println("Cut start\t" + message);
					subMessage.append(message, 0, length);
					message = message.substring(length);
					System.out.println("Prepared\t" + message);
				}
				case 1 -> {
					subMessage.append(message, 0, 18);
					message = message.substring(18);
					System.out.println("Cut start\t" + message);
					int i = 0;
					for (; i < message.length(); i += 5) {
						if (message.charAt(i) == '0') {
							i += 5;
							subMessage.append(message, 0, i);
							message = message.substring(i);
							break;
						}
					}
					System.out.println("Prepared\t" + message);
				}
			}
			System.out.println("Will create\t" + subMessage);
			messages.add(new Message(String.valueOf(subMessage)));
			System.out.println("Created\t" + message + '\n');
		}
		return messages;
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
}
