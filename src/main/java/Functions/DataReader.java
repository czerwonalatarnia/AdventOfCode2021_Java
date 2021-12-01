package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public interface DataReader {

	/**
	 * <p>Outputs the {@link LinkedList} of {@code long} elements.</p>
	 * <p>Use when the data is consisted of integer numbers.</p>
	 */

	static LinkedList<Long> readLongArray(int day) {
		LinkedList<Long> table = new LinkedList<>();
		String filePath;
		if (day < 10)
			filePath = "src\\main\\resources\\day0" + day + ".txt";
		else
			filePath = "src\\main\\resources\\day" + day + ".txt";
		File fileToRead = new File(filePath);
		try {
			Scanner fileScanner = new Scanner(fileToRead);
			fileScanner.useDelimiter("(\\s*,\\s*)|\\s+");
			while (fileScanner.hasNextLong()) {
				table.add(fileScanner.nextLong());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
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

	static LinkedList<Double> readDoubleArray(int day) {
		LinkedList<Double> table = new LinkedList<>();
		String filePath;
		if (day < 10)
			filePath = "data\\day0" + day + ".txt";
		else
			filePath = "data\\day" + day + ".txt";
		File fileToRead = new File(filePath);
		try {
			Scanner fileScanner = new Scanner(fileToRead);
			fileScanner.useDelimiter("(\\s*,\\s*)|\\s+");
			while (fileScanner.hasNextDouble()) {
				table.add(fileScanner.nextDouble());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return table;
	}

	/**
	 * <p>Outputs the {@link LinkedList} of {@link String} elements.</p>
	 * <p>Use when the data could be represented as simple array, similar to {@code readLongArray} and  {@code readDoubleArray}, but it contains non-number characters.</p>
	 */

	static LinkedList<String> readSimpleString(int day) {
		LinkedList<String> list = new LinkedList<>();
		String filePath;
		if (day < 10)
			filePath = "data\\day0" + day + ".txt";
		else
			filePath = "data\\day" + day + ".txt";
		File fileToRead = new File(filePath);
		try {
			Scanner fileScanner = new Scanner(fileToRead);
			while (fileScanner.hasNextLine()) {
				String string = fileScanner.nextLine();
				list.addAll(List.of(string.split("(\\s*,\\s*)|\\s+")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return list;
	}

	/**
	 * <p>Outputs the {@link LinkedList} of {@link String} elements.</p>
	 * <p>Use when the input is represented in a way that would require further, and more specific, analyze of each line.</p>
	 */

	static LinkedList<String> readAlchemyString(int day) {
		LinkedList<String> alchemy = new LinkedList<>();
		String filePath;
		if (day < 10)
			filePath = "data\\day0" + day + ".txt";
		else
			filePath = "data\\day" + day + ".txt";
		File fileToRead = new File(filePath);
		try {
			Scanner fileScanner = new Scanner(fileToRead);
			while (fileScanner.hasNextLine()) {
				alchemy.add(fileScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + filePath + " do not exist");
			return new LinkedList<>();
		} catch (Exception e) {
			System.out.println("You have encountered an unexpected error, but I was expecting it so have this catch");
			return new LinkedList<>();
		}
		return alchemy;
	}
}
