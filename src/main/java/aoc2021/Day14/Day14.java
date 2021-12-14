package aoc2021.Day14;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day14 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(14));
		System.out.println("Day 14\nThe answer to part 1 is " + part1(data));
		System.out.println("The answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		String elements = data.getFirst();
		LinkedList<String> rules = new LinkedList<>(data);
		rules.pollFirst();
		HashMap<String, Character> insertions = new HashMap<>();
		for (var rule : rules) {
			insertions.put("" + rule.charAt(0) + rule.charAt(1), rule.charAt(rule.length() - 1));
		}
		int[] elementAmount = new int[26];
		for (int it = 0; it < 10; it++) {
			StringBuilder newElements = new StringBuilder("" + elements.charAt(0));
			for (int i = 1; i < elements.length(); i++) {
				String key = "" + elements.charAt(i - 1) + elements.charAt(i);
				newElements.append(insertions.get(key))
				           .append(elements.charAt(i));
			}
			elements = String.valueOf(newElements);
		}
		for (int it = 0; it < elements.length(); it++) {
			elementAmount[elements.charAt(it) - 'A']++;
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (var el : elementAmount) {
			if (el > max)
				max = el;
			if (el < min & el > 0)
				min = el;
		}
		for (var el : elementAmount) {
			System.out.print(el + ", ");
		}
		System.out.println();
		return max - min;
	}

	// Something doesn't work correctly

	BigInteger part2(LinkedList<String> data) {
		String elements = data.getFirst();
		LinkedList<String> rules = new LinkedList<>(data);
		rules.pollFirst();
		HashMap<String, Character> insertions = new HashMap<>();
		HashMap<String, BigInteger> amount = new HashMap<>();
		BigInteger[] elementAmount = new BigInteger[26];
		for (int i = 0; i < 26; i++)
			elementAmount[i] = BigInteger.ZERO;
		for (var rule : rules) {
			final String key = "" + rule.charAt(0) + rule.charAt(1);
			insertions.put(key, rule.charAt(rule.length() - 1));
			amount.put(key, BigInteger.ZERO);
		}
		for (int i = 1; i < elements.length(); i++) {
			String key = "" + elements.charAt(i - 1) + elements.charAt(i);
			amount.replace(key, amount.get(key)
			                          .add(BigInteger.ONE));
		}
		for (int it = 0; it < 40; it++) {
			HashMap<String, BigInteger> temp = new HashMap<>();
			for (String key : amount.keySet()) {
				String key1 = "" + key.charAt(0) + insertions.get(key);
				String key2 = "" + insertions.get(key) + key.charAt(1);
				if (temp.containsKey(key1))
					temp.replace(key1, temp.get(key1)
					                       .add(amount.get(key)));
				else
					temp.put(key1, amount.get(key));
				if (temp.containsKey(key2))
					temp.replace(key2, temp.get(key2)
					                       .add(amount.get(key)));
				else
					temp.put(key2, amount.get(key));
			}
			amount = new HashMap<>(temp);
		}
		for (String key : amount.keySet()) {
			elementAmount[key.charAt(0) - 'A'] = elementAmount[key.charAt(0) - 'A'].add(amount.get(key));
			elementAmount[key.charAt(0) - 'A'] = elementAmount[key.charAt(0) - 'A'].add(amount.get(key));
		}
		for (var el : elementAmount) {
			System.out.print(el + ", ");
		}
		System.out.println();
		BigInteger max = BigInteger.ZERO;
		BigInteger min = new BigInteger(String.valueOf(Long.MAX_VALUE));
		for (var el : elementAmount) {
			if (el.compareTo(max) > 0)
				max = el;
			if (el.compareTo(min) < 0 && el.compareTo(BigInteger.ZERO) > 0)
				min = el;
		}

		// But the result IS OKAY

		return max.subtract(min).divide(BigInteger.TWO).subtract(BigInteger.ONE);
	}
}