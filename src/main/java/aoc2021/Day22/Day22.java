package aoc2021.Day22;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day22 implements IDay {
	boolean CHANGED = true;

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(22));
		part(data, 3);
	}

	long part(LinkedList<String> data, int part) {
		LinkedList<Bounds> bounds = new LinkedList<>();
		boolean turn;
		int[] bound = new int[6];
		for (var line : data) {
			turn = findTheBounds(bound, line);
			Bounds toAdd = new Bounds(bound, turn);
			for (int j = bounds.size() - 1; j >= 0; j--) {
				if (toAdd.contains(bounds.get(j)))
					bounds.remove(j);
				if (toAdd.intersect(bounds.get(j))) {
					LinkedList<Bounds> temp = toAdd.dissect(bounds.get(j));
					bounds.remove(j);
					bounds.addAll(j, temp);
				}
			}
			if (turn)
				bounds.add(toAdd);
		}
		while (CHANGED) {
			CHANGED = false;
			for (int i = bounds.size() - 1; i > 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (bounds.get(i)
					          .contains(bounds.get(j))) {
						bounds.remove(j);
						i--;
						CHANGED = true;
						continue;
					}
					if (bounds.get(i)
					          .intersect(bounds.get(j))) {
						LinkedList<Bounds> temp = bounds.get(i)
						                                .dissect(bounds.get(j));
						bounds.remove(j);
						bounds.addAll(j, temp);
						CHANGED = true;
						i += temp.size() - 1;
					}
				}
			}
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

	private boolean findTheBounds(int[] bounds, String line) {
		boolean turn;
		turn = line.charAt(1) == 'n';
		bounds[0] = Integer.parseInt(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.indexOf('.') + 2);
		bounds[1] = Integer.parseInt(line.substring(0, line.indexOf(',')));
		bounds[2] = Integer.parseInt(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.indexOf('.') + 2);
		bounds[3] = Integer.parseInt(line.substring(0, line.indexOf(',')));
		bounds[4] = Integer.parseInt(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
		line = line.substring(line.lastIndexOf('.') + 1);
		bounds[5] = Integer.parseInt(line);
		return turn;
	}
}