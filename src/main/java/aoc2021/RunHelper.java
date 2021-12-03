package aoc2021;

import aoc2021.own.exception.FileIsEmpty;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.stream.IntStream;

public interface RunHelper {
	static void runChallenges(int day, int month) {
		if (month == 12) {
			doDay(day);
		} else {
			IntStream.rangeClosed(1, 25).forEachOrdered(iDay -> {
				doDay(iDay);
				System.out.println();
			});
		}
	}

	static void doDay(int iDay) {
		String classPath = String.format("aoc2021.Day%02d.Day%02d", iDay, iDay);
		try {
			IDay dayChallenge = (IDay) Class.forName(classPath).getDeclaredConstructor().newInstance();
			dayChallenge.day();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (FileIsEmpty e) {
			System.out.println(e.getMessage());
			System.out.println("File is empty, check if you didn't forget the data");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something that throws " + e.toString() + " has happened.");
		}
	}
}
