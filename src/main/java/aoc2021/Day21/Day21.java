package aoc2021.Day21;

import java.util.ArrayList;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day21 implements IDay {
	static int deterministicDieValue;
	static long deterministicDieRoll;

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(21));
		System.out.println("Day 21\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		deterministicDieValue = 1;
		deterministicDieRoll = 0;
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
		long player1wins = 0;
		long player2wins = 0;
		int size = 50;
		long check = 1;
		int[] playerMove = new int[]{0, 0, 0, 1, 3, 6, 7, 6, 3, 1};
		long[] player1pos = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long[] player1posNew = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long[] player2pos = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long[] player2posNew = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		long[] player1sc = new long[size];
		long[] player1scNew = new long[size];
		long[] player2sc = new long[size];
		long[] player2scNew = new long[size];
		for (int i = 0; i < size; i++) {
			player1sc[i] = 0;
			player1scNew[i] = 0;
			player2sc[i] = 0;
			player2scNew[i] = 0;
		}
		player1pos[Integer.parseInt(data.get(0)
		                                .substring(data.get(0)
		                                               .lastIndexOf(' ') + 1)) - 1] = 1;
		player2pos[Integer.parseInt(data.get(1)
		                                .substring(data.get(1)
		                                               .lastIndexOf(' ') + 1)) - 1] = 1;
		while (check != 0) {
			check = 0;
			doTheMove(size, playerMove, player1pos, player1posNew, player1sc, player1scNew);
			for (int i = 0; i < size; i++) {
				if (i < 21 && player1sc[i] > 0)
					check++;
				else if (i >= 21) {
					player1wins += player1sc[i];
					player1sc[i] = 0;
				}
			}
			if (check != 0) {
				doTheMove(size, playerMove, player2pos, player2posNew, player2sc, player2scNew);
				for (int i = 0; i < size; i++) {
					if (i >= 21) {
						player2wins += player2sc[i];
						player2sc[i] = 0;
					}
				}
			}
			System.out.println("Player 1 wins " + player1wins + " times.\nPlayer 2 wins " + player2wins + " times.\n");
		}
		return Math.max(player1wins, player2wins);
	}

	private void doTheMove(int size, int[] playerMove, long[] playerPos, long[] playerPosNew, long[] playerSc,
	                       long[] playerScNew) {
		for (int pos = 0; pos < 10; pos++) {
			for (int move = 3; move < 10; move++)
				playerPosNew[(pos + move) % 10] += playerPos[pos] * playerMove[move];
		}
		for (int i = 0; i < size - 10; i++) {
			for (int pos = 0; pos < 10; pos++)
				playerScNew[i + pos + 1] += (playerSc[i] + playerPos[pos]);
		}
		for (int i = 0; i < size; i++) {
			playerSc[i] = playerScNew[i];
			playerScNew[i] = 0;
		}
		for (int pos = 0; pos < 10; pos++) {
			playerPos[pos] = playerPosNew[pos];
			playerPosNew[pos] = 0;
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