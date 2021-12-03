package aoc2021.Day02;

import java.util.LinkedList;

import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day02 implements IDay {
	public void day() throws Exception {
		LinkedList<String> movementCommands = DataReader.readAlchemyString(DataReader.createFilePath(2));
		System.out.println("Day 2\nThe answer to part 1 is " + part1(movementCommands) + "\nThe answer to part 2 is " + part2(movementCommands));
	}

	long part1(LinkedList<String> movementCommands) {
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

	long part2(LinkedList<String> movementCommands) {
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