package aoc2021.Day21;

import java.util.ArrayList;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day21 implements IDay {
	static int deterministicDieValue = 1;
	static long deterministicDieRoll = 0;
	static long player1wins = 0;
	static long player2wins = 0;

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(21));
		System.out.println("Day 21\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		ArrayList<Player> players = new ArrayList<>(2);
		for (int i = 0; i < data.size(); i++)
			players.add(new Player(Integer.parseInt(data.get(i)
			                                            .substring(data.get(i)
			                                                           .lastIndexOf(' ') + 1)), i + 1));
		while (players.get(0)
		              .getScore() < 1000 && players.get(1)
		                                           .getScore() < 1000) {
			if (deterministicDieRoll % 2 == 0)
				players.get(0)
				       .move(roll());
			else
				players.get(1)
				       .move(roll());
		}
		if (players.get(0)
		           .getScore() >= 1000)
			return players.get(1)
			              .getScore() * deterministicDieRoll;
		else if (players.get(1)
		                .getScore() >= 1000)
			return players.get(0)
			              .getScore() * deterministicDieRoll;
		return 0;
	}

	long part2(LinkedList<String> data) {
		int player1score = 0;
		int player2score = 0;
		int player1start = Integer.parseInt(data.get(0)
		                                        .substring(data.get(0)
		                                                       .lastIndexOf(' ') + 1));
		int player2start = Integer.parseInt(data.get(1)
		                                        .substring(data.get(1)
		                                                       .lastIndexOf(' ') + 1));
		doTheRoll(player1start, player1score, player2start, player2score, 1, 1);
		return Math.max(player1wins, player2wins);
	}

	private void doTheRoll(int player1pos, int player1score, int player2pos, int player2score, int turn, long amount) {
		if (player1score >= 21) {
			player1wins += amount;
			return;
		} else if (player2score >= 21) {
			player2wins += amount;
			return;
		}
		if (turn % 2 == 1) {
			doTheRoll((player1pos + 3) % 10, player1score + (player1pos + 3) % 10, player2pos, player2score, 2 - turn, amount);
			doTheRoll((player1pos + 4) % 10, player1score + (player1pos + 4) % 10, player2pos, player2score, 2 - turn, amount * 3);
			doTheRoll((player1pos + 5) % 10, player1score + (player1pos + 5) % 10, player2pos, player2score, 2 - turn, amount * 6);
			doTheRoll((player1pos + 6) % 10, player1score + (player1pos + 6) % 10, player2pos, player2score, 2 - turn, amount * 7);
			doTheRoll((player1pos + 7) % 10, player1score + (player1pos + 7) % 10, player2pos, player2score, 2 - turn, amount * 6);
			doTheRoll((player1pos + 8) % 10, player1score + (player1pos + 8) % 10, player2pos, player2score, 2 - turn, amount * 3);
			doTheRoll((player1pos + 9) % 10, player1score + (player1pos + 9) % 10, player2pos, player2score, 2 - turn, amount);
		} else {
			doTheRoll(player1pos, player1score, (player2pos + 3) % 10, player2score + (player2pos + 3) % 10, 2 - turn, amount);
			doTheRoll(player1pos, player1score, (player2pos + 4) % 10, player2score + (player2pos + 4) % 10, 2 - turn, amount * 3);
			doTheRoll(player1pos, player1score, (player2pos + 5) % 10, player2score + (player2pos + 5) % 10, 2 - turn, amount * 6);
			doTheRoll(player1pos, player1score, (player2pos + 6) % 10, player2score + (player2pos + 6) % 10, 2 - turn, amount * 7);
			doTheRoll(player1pos, player1score, (player2pos + 7) % 10, player2score + (player2pos + 7) % 10, 2 - turn, amount * 6);
			doTheRoll(player1pos, player1score, (player2pos + 8) % 10, player2score + (player2pos + 8) % 10, 2 - turn, amount * 3);
			doTheRoll(player1pos, player1score, (player2pos + 9) % 10, player2score + (player2pos + 9) % 10, 2 - turn, amount);
		}
	}

	private int roll() {
		deterministicDieRoll += 3;
		int move = 0;
		for (int i = 0; i < 3; i++) {
			move += deterministicDieValue;
			deterministicDieValue++;
			if (deterministicDieValue > 100)
				deterministicDieValue = 1;
		}
		return move;
	}
}