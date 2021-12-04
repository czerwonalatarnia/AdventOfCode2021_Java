package aoc2021.Day04;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day04 implements IDay {
	public void day() throws Exception {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(4));
		var data2 = DataReader.readAlchemyString(DataReader.createFilePath(4));
		System.out.println("Day 4\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data2));
	}

	int part1(LinkedList<String> data) {
		String[] drawnBallsString = data.pollFirst().split(",");
		int[] drawnBalls = new int[drawnBallsString.length];
		for (int drawn = 0; drawn < drawnBalls.length; drawn++) {
			drawnBalls[drawn] = Integer.parseInt(drawnBallsString[drawn]);
		}
		LinkedList<Bingo> bingos = new LinkedList<>();
		if (data.size() % 5 != 0) {
			System.out.println("You have wrong data");
			return -1;
		}
		while (data.size() != 0) {
			String[] bingoLines = new String[5];
			for (int line = 0; line < 5; line++) {
				String leadingSpaceCheck = data.pollFirst();
				if (leadingSpaceCheck.charAt(0) == ' ') {
					leadingSpaceCheck = leadingSpaceCheck.substring(1);
				}
				bingoLines[line] = leadingSpaceCheck;
			}
			bingos.add(new Bingo(bingoLines));
		}
		for (var drawn : drawnBalls) {
			for (var el : bingos) {
				if (el.drawNumber(drawn)) {
					return el.result();
				}
			}
		}
		return -1;
	}

	int part2(LinkedList<String> data) {
		String[] drawnBallsString = data.pollFirst().split(",");
		int[] drawnBalls = new int[drawnBallsString.length];
		for (int drawn = 0; drawn < drawnBalls.length; drawn++) {
			drawnBalls[drawn] = Integer.parseInt(drawnBallsString[drawn]);
		}
		LinkedList<Bingo> bingos = new LinkedList<>();
		if (data.size() % 5 != 0) {
			System.out.println("You have wrong data");
			return -1;
		}
		while (data.size() != 0) {
			String[] bingoLines = new String[5];
			for (int line = 0; line < 5; line++) {
				String leadingSpaceCheck = data.pollFirst();
				if (leadingSpaceCheck.charAt(0) == ' ') {
					leadingSpaceCheck = leadingSpaceCheck.substring(1);
				}
				bingoLines[line] = leadingSpaceCheck;
			}
			bingos.add(new Bingo(bingoLines));
		}
		LinkedList<Integer> winningScores = new LinkedList<>();
		for (var drawn : drawnBalls) {
			for (var el : bingos) {
				if (!el.hasWon()) {
					if (el.drawNumber(drawn)) {
						winningScores.add(el.result());
						System.out.println("Won with " + el.result());
					}
				}
			}
		}
		return winningScores.peekLast();
	}
}
