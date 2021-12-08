package aoc2021.Day08;

public class Display {
	private final String[] numberCodes = new String[10];

	public void matchStringAndNumber(String code, int display) {
		numberCodes[display] = code;
	}

	public int getNumber(String value) {
		for (int i = 0; i < 10; i++) {
			if (value.length() == numberCodes[i].length()) {
				int count = 0;
				for (int j = 0; j < value.length(); j++) {
					if (numberCodes[i].contains("" + value.toCharArray()[j])) {
						count++;
					}
				}
				if (count == value.length())
					return i;
			}
		}
		return -10000;
	}
}
