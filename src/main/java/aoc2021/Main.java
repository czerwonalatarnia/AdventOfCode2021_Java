package aoc2021;

import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
	public static void main(String[] args) {

		/* If you want to run specific day, set value of DAY to the day you want, MONTH to 12 and YEAR to 2021. */

		System.out.println("Welcome to Advent of Code 2021!\n");
		int DAY = LocalDate.now(ZoneId.of("Europe/Warsaw")).getDayOfMonth();
		int MONTH = LocalDate.now(ZoneId.of("Europe/Warsaw")).getMonthValue();
		int YEAR = LocalDate.now(ZoneId.of("Europe/Warsaw")).getYear();
		RunHelper.runChallenges(DAY, MONTH, YEAR);
	}
}