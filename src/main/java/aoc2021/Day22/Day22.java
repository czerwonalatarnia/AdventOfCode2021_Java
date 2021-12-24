package aoc2021.Day22;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day22 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(22));
		part(data, 3);
	}

	long part(LinkedList<String> data, int part) {
		LinkedList<Bounds> bounds = new LinkedList<>();
		boolean turn;
		long[] bound = new long[6];
		for (var line : data) {
			turn = findTheBounds(bound, line);
			Bounds toAdd = new Bounds(bound, turn);
			for (int j = bounds.size() - 1; j >= 0; j--) {
				if (toAdd.contains(bounds.get(j)))
					bounds.remove(j);
				else if (toAdd.intersect(bounds.get(j))) {
					LinkedList<Bounds> temp = toAdd.dissect(bounds.get(j));
					bounds.remove(j);
					bounds.addAll(temp);
				}
			}
			if (toAdd.isTurn())
				bounds.add(toAdd);
		}
		long result = 0;
		long smallResult = 0;
		for (var el : bounds) {
			if (el.isTurn()) {
				result += el.volume();
				smallResult += el.volumeSmall();
			}
		}
		if (part == 1)
			return smallResult;
		else if (part == 3) {
			System.out.println(
					"Day 22\nThe answer to part 1 is " + smallResult + "\nThe answer to part 2 is " + result);
			return 0;
		} else
			return result;
	}

	private boolean findTheBounds(long[] bounds, String line) {
		boolean turn;
		turn = line.charAt(1) == 'n';
		bounds[0] = Long.parseLong(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.indexOf('.') + 2);
		bounds[1] = Long.parseLong(line.substring(0, line.indexOf(',')));
		bounds[2] = Long.parseLong(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.indexOf('.') + 2);
		bounds[3] = Long.parseLong(line.substring(0, line.indexOf(',')));
		bounds[4] = Long.parseLong(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.lastIndexOf('.') + 1);
		bounds[5] = Long.parseLong(line);
		return turn;
	}
}