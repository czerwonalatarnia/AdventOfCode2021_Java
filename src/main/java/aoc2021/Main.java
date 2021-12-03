package aoc2021;

import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
	public static void main(String[] args) {

		/* If you want to run specific day, set value of DAY to the day you want and MONTH to 12. */
		LocalDate today = LocalDate.now(ZoneId.of("Europe/Warsaw"));
		int DAY = today.getDayOfMonth();
		int MONTH = today.getMonthValue();
		RunHelper.runChallenges(DAY, MONTH);
	}
}
