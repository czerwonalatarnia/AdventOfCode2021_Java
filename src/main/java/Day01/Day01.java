package Day01;

import Functions.DataReader;

import java.util.LinkedList;

public class Day01 {
	public static void main(String[] args) {
		LinkedList<Long> sonarResults = DataReader.readLongArray(1);
		int increase = 0;
		for (int i = 1; i < sonarResults.size(); i++) {
			if (sonarResults.get(i) > sonarResults.get(i-1))
				increase++;
		}
		System.out.println(sonarResults.peekLast());
		System.out.println(increase);
		int tripleIncrease = 0;
		for (int i = 3; i < sonarResults.size(); i++) {
			if ((sonarResults.get(i) + sonarResults.get(i-1) + sonarResults.get(i-2)) > (sonarResults.get(i-1) + sonarResults.get(i-2) + sonarResults.get(i-3)))
				tripleIncrease++;
		}
		System.out.println(tripleIncrease);
	}
}
