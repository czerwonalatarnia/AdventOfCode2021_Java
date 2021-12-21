package aoc2021.Day21;

public class Player {
	private final int player;
	private int currentPosition;
	private long score = 0;

	public Player(int pos, int player) {
		this.currentPosition = pos;
		this.player = player;
	}

	public void move(int move) {
		currentPosition = (currentPosition + move);
		while (currentPosition > 10)
			currentPosition -= 10;
		score += currentPosition;
	}

	public long getScore() {
		return score;
	}
}