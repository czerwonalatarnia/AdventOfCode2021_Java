package aoc2021.own.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;
import aoc2021.own.exception.FileIsEmpty;

public interface DataReader {

	/**
	 * <p>Creates the {@code String} variable which is a path to the text file.</p>
	 */
	Path resourcePath = Path.of("src", "main", "resources");

	static Path createFilePath(int day) {
		String dayStr = String.format("day%02d.txt", day);
		return resourcePath.resolve(dayStr);
	}

	/**
	 * <p>Outputs the {@link LinkedList} of {@code long} elements.</p>
	 * <p>Use when the data is consisted of integer numbers.</p>
	 */

	static LinkedList<Long> readLongArray(Path filePath) {
		LinkedList<Long> table = new LinkedList<>();
		File fileToRead = filePath.toFile();
		try {
			if (fileToRead.length() == 0)
				throw new FileIsEmpty("File " + filePath + " is empty.");
			Scanner fileScanner = new Scanner(fileToRead);
			fileScanner.useDelimiter("(\\s*,\\s*)|\\s+");
			while (fileScanner.hasNextLong()) {
				table.add(fileScanner.nextLong());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (FileIsEmpty e) {
			e.printStackTrace();
			System.out.println("File is empty, check if you didn't forget the data");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return table;
	}

	/**
	 * <p>Outputs the {@link LinkedList} of {@code double} elements.</p>
	 * <p>Use when the data is consisted of real numbers.</p>
	 */

	static LinkedList<Double> readDoubleArray(String filePath) {
		LinkedList<Double> table = new LinkedList<>();
		File fileToRead = new File(filePath);
		try {
			if (fileToRead.length() == 0)
				throw new FileIsEmpty("File " + filePath + " is empty.");
			Scanner fileScanner = new Scanner(fileToRead);
			fileScanner.useDelimiter("(\\s*,\\s*)|\\s+");
			while (fileScanner.hasNextDouble()) {
				table.add(fileScanner.nextDouble());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (FileIsEmpty e) {
			e.printStackTrace();
			System.out.println("File is empty, check if you didn't forget the data");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return table;
	}

	/**
	 * <p>Outputs a {@link String}.</p>
	 * <p>Use when the data fits in one line but there's no reason to split it.</p>
	 */

	static String readSimpleString(Path filePath) {
		File fileToRead = filePath.toFile();
		String string;
		try {
			if (fileToRead.length() == 0) {
				throw new FileIsEmpty("File " + filePath + " is empty.");
			}
			Scanner fileScanner = new Scanner(fileToRead);
			string = fileScanner.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return "";
		} catch (FileIsEmpty e) {
			e.printStackTrace();
			System.out.println("File is empty, check if you didn't forget the data");
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return "";
		}
		return string;
	}

	/**
	 * <p>Outputs the {@link LinkedList} of {@link String} elements.</p>
	 * <p>Use when the input is represented in a way that would require further, and more specific, analyze of each line.</p>
	 */

	static LinkedList<String> readAlchemyString(Path filePath) {
		LinkedList<String> alchemy = new LinkedList<>();
		File fileToRead = filePath.toFile();
		try {
			if (fileToRead.length() == 0)
				throw new FileIsEmpty("File " + filePath + " is empty.");
			Scanner fileScanner = new Scanner(fileToRead);
			while (fileScanner.hasNextLine()) {
				String lineSizeCheck = fileScanner.nextLine();
				if (lineSizeCheck.length() != 0)
					alchemy.add(lineSizeCheck);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (FileIsEmpty e) {
			e.printStackTrace();
			System.out.println("File is empty, check if you didn't forget the data");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return alchemy;
	}
}