package Day02;

public class Submarine {
	private long horizontal = 0;
	private long vertical = 0;
	private long aim = 0;

	public long getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(long horizontal) {
		this.horizontal = horizontal;
	}

	public void moveHorizontal(long horizontal) {
		this.horizontal += horizontal;
	}

	public long getVertical() {
		return vertical;
	}

	public void setVertical(long vertical) {
		this.vertical = vertical;
	}

	public void moveVertical(long vertical) {
		this.vertical += vertical;
	}

	public long getAim() {
		return aim;
	}

	public void setAim(long aim) {
		this.aim = aim;
	}

	public void moveAim(long aim) {
		this.aim += aim;
	}

	public long getSuperPosition() {
		return horizontal*vertical;
	}
}
