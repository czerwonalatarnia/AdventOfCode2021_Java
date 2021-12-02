package aoc2021.Day02;

import java.util.LinkedList;
import aoc2021.own.functions.DataReader;

public abstract class Day02 {
	public static void day() {
		LinkedList<String> movementCommands = DataReader.readAlchemyString(DataReader.createFilePath(2));
		System.out.println("\nThe answer to part 1 is " + part1(movementCommands));
		System.out.println("\nThe answer to part 2 is " + part2(movementCommands));
	}

	static long part1(LinkedList<String> movementCommands) {
		Submarine submarine = new Submarine();
		for (var el : movementCommands) {
			long command = el.charAt(el.length() - 1) - '0';
			if (el.charAt(0) == 'f')
				submarine.moveHorizontal(command);
			else if (el.charAt(0) == 'd')
				submarine.moveVertical(command);
			else if (el.charAt(0) == 'u')
				submarine.moveVertical(-command);
		}
		return submarine.getSuperPosition();
	}

	static long part2(LinkedList<String> movementCommands) {
		Submarine submarine = new Submarine();
		for (var el : movementCommands) {
			long command = el.charAt(el.length() - 1) - '0';
			if (el.charAt(0) == 'f') {
				submarine.moveHorizontal(command);
				submarine.moveVertical(submarine.getAim() * command);
			} else if (el.charAt(0) == 'd')
				submarine.moveAim(command);
			else if (el.charAt(0) == 'u')
				submarine.moveAim(-command);
		}
		return submarine.getSuperPosition();
	}
}