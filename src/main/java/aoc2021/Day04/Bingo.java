package aoc2021.Day04;

public class Bingo {
	private final int[][] field = new int[5][5];
	private final boolean[][] isChecked = new boolean[][]{{Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}, {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE}};
	private int winningNumber = -1;
	private int sumOfNotHit = 0;

	public Bingo(String[] input) {
		if (input.length != 5) {
			System.out.println("You have wrong amount of lines to create bingo");
		}
		for (int line = 0; line < 5; line++) {
			String[] cutLine = input[line].split("\\s+");
			for (int number = 0; number < 5; number++) {
				field[line][number] = Integer.parseInt(cutLine[number]);
				sumOfNotHit += field[line][number];
			}
		}
	}

	/**
	 * <p>Function that checks if the number drawn in current round of Bingo appears on the board. If this has happened, the function marks it in the {@code isChecked} board
	 * and decreases the {@code sumOfNotHit} which sums the values from the bingo board that were not marked yet.</p>
	 * <p>It also uses the {@code checkIfWin} method to see if the number marked this round created a bingo on the board, and marks that number as {@code winningNumber}.</p>
	 * @param draw number that was drawn in current round of Bingo game.
	 * @return true if after this round the bingo was completed,
	 * false if there is still no bingo.
	 */

	public boolean drawNumber(int draw) {
		boolean checkedThisLoop = false;
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if (field[x][y] == draw) {
					isChecked[x][y] = true;
					checkedThisLoop = true;
					sumOfNotHit -= draw;
					break;
				}
			}
			if (checkedThisLoop) {
				if (checkIfWin()) {
					winningNumber = draw;
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	public boolean checkIfWin() {
		for (int x = 0; x < 5; x++) {
			if (isChecked[x][0] && isChecked[x][1] && isChecked[x][2] && isChecked[x][3] && isChecked[x][4])
				return true;
			if (isChecked[0][x] && isChecked[1][x] && isChecked[2][x] && isChecked[3][x] && isChecked[4][x])
				return true;
		}
		return false;
	}

	public int result() {
		return winningNumber * sumOfNotHit;
	}

	/**
	 * Function that returns if a board was finished, as otherwise the {@code winningNumber} is marked with default value of 0.
	 */

	public boolean hasWon() {
		return winningNumber >= 0;
	}
}