package aoc2021.Day04;

import java.util.LinkedList;
import java.util.Objects;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day04 implements IDay {
	public void day() {
		var data = DataReader.readAlchemyString(DataReader.createFilePath(4));
		var data2 = DataReader.readAlchemyString(DataReader.createFilePath(4));
		System.out.println("Day 4\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data2));
	}

	int part1(LinkedList<String> data) {
		String[] drawnBallsString = Objects.requireNonNull(data.pollFirst())
		                                   .split(",");
		int[] drawnBalls = getDrawnBalls(drawnBallsString);
		LinkedList<Bingo> bingo = getBingo(data);
		for (var drawn : drawnBalls) {
			for (var el : bingo) {
				if (el.drawNumber(drawn)) {
					return el.result();
				}
			}
		}
		return -1;
	}

	int part2(LinkedList<String> data) {
		String[] drawnBallsString = Objects.requireNonNull(data.pollFirst())
		                                   .split(",");
		int[] drawnBalls = getDrawnBalls(drawnBallsString);
		LinkedList<Bingo> bingo = getBingo(data);
		LinkedList<Integer> winningScores = new LinkedList<>();
		for (var drawn : drawnBalls) {
			for (var el : bingo) {
				if (!el.hasWon()) {
					if (el.drawNumber(drawn)) {
						winningScores.add(el.result());
					}
				}
			}
		}
		return winningScores.peekLast();
	}

	private int[] getDrawnBalls(String[] drawnBallsString) {
		int[] drawnBalls = new int[drawnBallsString.length];
		for (int drawn = 0; drawn < drawnBalls.length; drawn++) {
			drawnBalls[drawn] = Integer.parseInt(drawnBallsString[drawn]);
		}
		return drawnBalls;
	}

	private LinkedList<Bingo> getBingo(LinkedList<String> data) {
		if (data.size() % 5 != 0) {
			System.out.println("You have wrong data");
			return new LinkedList<>();
		}
		LinkedList<Bingo> buildGame = new LinkedList<>();
		while (data.size() != 0) {
			String[] bingoLines = new String[5];
			for (int line = 0; line < 5; line++) {
				bingoLines[line] = Objects.requireNonNull(data.pollFirst())
				                          .strip();
			}
			buildGame.add(new Bingo(bingoLines));
		}
		return buildGame;
	}
}