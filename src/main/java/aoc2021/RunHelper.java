package aoc2021;

import java.lang.reflect.InvocationTargetException;

public interface RunHelper {
	static void runChallenges(int day, int month) {
		if (month == 12) {
			doDay(day);
		} else {
			for (int iDay = 1; iDay <= 25; iDay++) {
				doDay(iDay);
				System.out.println();
			}
		}
	}

	static void doDay(int iDay) {
		String classPath;
		if (iDay < 10)
			classPath = "aoc2021.Day0" + iDay + ".Day0" + iDay;
		else
			classPath = "aoc2021.Day" + iDay + ".Day" + iDay;
		try {
			Class.forName(classPath).getDeclaredMethod("day").invoke(null);
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Something that throws " + e + " has happened.");
		}
	}
}
