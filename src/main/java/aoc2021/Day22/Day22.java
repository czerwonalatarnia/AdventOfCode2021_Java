package aoc2021.Day22;

import java.util.HashSet;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import aoc2021.own.objects.Point;

public class Day22 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(22));
		LinkedList<String> data2 = DataReader.readAlchemyString(DataReader.createFilePath(22));
		System.out.println("Day 22\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data2));
	}

	long part1(LinkedList<String> data) {
		boolean[][][] cubes = new boolean[101][101][101];
		int[] bounds = new int[6];
		boolean turn;
		for (var line : data) {
			turn = findTheBounds(bounds, line);
			for (int i = (bounds[0] < -50) ? 0 : bounds[0] + 50; i <= bounds[1] + 50 && i < 101; i++) {
				for (int j = (bounds[2] < -50) ? 0 : bounds[2] + 50; j <= bounds[3] + 50 && j < 101; j++) {
					for (int k = (bounds[4] < -50) ? 0 : bounds[4] + 50; k <= bounds[5] + 50 && k < 101; k++)
						cubes[i][j][k] = turn;
				}
			}
		}
		int counter = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				for (int k = 0; k < 101; k++) {
					if (cubes[i][j][k])
						counter++;
				}
			}
		}
		return counter;
	}

	long part2(LinkedList<String> data2) {
		LinkedList<Bounds> bounds = new LinkedList<>();
		HashSet<Point> list = new HashSet<>();
		boolean turn;
		int[] bound = new int[6];
		for (var line : data2) {
			turn = findTheBounds(bound, line);
			bounds.add(new Bounds(bound, turn));
		}
		for (int repeat = 0; repeat < 10; repeat++) {
			for (int i = bounds.size() - 1; i > 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (eliminate(bounds.get(i), bounds.get(j))) {
						System.out.println("Eaten " + bounds.get(j) + " by " + bounds.get(i));
						bounds.remove(j);
						i--;
						continue;
					}
					if (reduce(bounds.get(i), bounds.get(j), bounds, j)) {
						System.out.println("Changed " + bounds.get(j) + " by " + bounds.get(i));
						i = bounds.size() - 1;
					}
				}
			}
			System.out.println();
			for (int i = 0; i < bounds.size() - 1; i++) {
				for (int j = i + 1; j < bounds.size(); j++) {
					if (bounds.get(j)
					          .isTurn() != bounds.get(i)
					                             .isTurn())
						break;
					if (eliminate(bounds.get(i), bounds.get(j))) {
						System.out.println("Eaten upstream " + bounds.get(j) + " by " + bounds.get(i));
						bounds.remove(j);
						j--;
						continue;
					}
					if (reduce(bounds.get(i), bounds.get(j), bounds, j)) {
						System.out.println("Changed upstream " + bounds.get(j) + " by " + bounds.get(i));
						i = 0;
					}
				}
			}
			System.out.println();
		}
		for (var el : bounds)
			System.out.println(el);
		return 0;
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

	private boolean eliminate(Bounds bounds1, Bounds bounds2) {
		int[] boundsBig = bounds1.getBounds();
		int[] boundsSmall = bounds2.getBounds();
		return boundsBig[0] <= boundsSmall[0] && boundsBig[1] >= boundsSmall[1] && boundsBig[2] <= boundsSmall[2] && boundsBig[3] >= boundsSmall[3] && boundsBig[4] <= boundsSmall[4] && boundsBig[5] >= boundsSmall[5];
	}

	private boolean reduce(Bounds bounds1, Bounds bounds2, LinkedList<Bounds> bounds, int pos) {
		int[] boundsStay = bounds1.getBounds();
		int[] boundsReduce = bounds2.getBounds();
		if (boundsStay[1] >= boundsReduce[0] && boundsStay[0] <= boundsReduce[1] && boundsStay[3] >= boundsReduce[2] && boundsStay[2] <= boundsReduce[3] && boundsStay[5] >= boundsReduce[4] && boundsStay[4] <= boundsReduce[5]) {
			if (boundsStay[0] <= boundsReduce[0] && boundsStay[1] >= boundsReduce[1]) {
				if (boundsStay[2] <= boundsReduce[2] && boundsStay[3] >= boundsReduce[3]) {
					if (boundsStay[4] <= boundsReduce[4])
						bounds2.setBoundsI(boundsStay[5] + 1, 4);
					else if (boundsStay[5] >= boundsReduce[5])
						bounds2.setBoundsI(boundsStay[4] - 1, 5);
					else {
						bounds2.setBoundsI(boundsStay[4] - 1, 5);
						int[] temp = boundsReduce.clone();
						temp[4] = boundsStay[5] + 1;
						bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					}
					return true;
				} else if (boundsStay[4] <= boundsReduce[4] && boundsStay[5] >= boundsReduce[5]) {
					if (boundsStay[2] <= boundsReduce[2])
						bounds2.setBoundsI(boundsStay[3] + 1, 2);
					else if (boundsStay[3] >= boundsReduce[3])
						bounds2.setBoundsI(boundsStay[2] - 1, 3);
					else {
						bounds2.setBoundsI(boundsStay[2] - 1, 3);
						int[] temp = boundsReduce.clone();
						temp[2] = boundsStay[3] + 1;
						bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					}
					return true;
				}
			} else if (boundsStay[2] <= boundsReduce[2] && boundsStay[3] >= boundsReduce[3]) {
				if (boundsStay[4] <= boundsReduce[4] && boundsStay[5] >= boundsReduce[5]) {
					if (boundsStay[0] <= boundsReduce[0])
						bounds2.setBoundsI(boundsStay[1] + 1, 0);
					else if (boundsStay[3] >= boundsReduce[3])
						bounds2.setBoundsI(boundsStay[0] - 1, 1);
					else {
						bounds2.setBoundsI(boundsStay[0] - 1, 1);
						int[] temp = boundsReduce.clone();
						temp[0] = boundsStay[1] + 1;
						bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					}
					return true;
				}
			}
		}
		return false;
	}
}