package aoc2021.Day22;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day22 implements IDay {
	boolean added;
	int add;
	int REPEAT = 5;
	int SPLIT_X = 100000;
	int SPLIT_Y = 100000;
	int SPLIT_Z = 100000;

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(22));
		LinkedList<String> data2 = DataReader.readAlchemyString(DataReader.createFilePath(22));
		System.out.println("Day 22\nThe answer to part 1 is " + part(data, 1) + "\nThe answer to part 2 is " + part(data2, 2));
	}

	long part(LinkedList<String> data, int part) {
		LinkedList<Bounds> bounds = new LinkedList<>();
		boolean turn;
		int[] bound = new int[6];
		for (var line : data) {
			turn = findTheBounds(bound, line);
			bounds.add(new Bounds(bound, turn));
			splitterX(bounds);
		}
		for (int repeat = 0; repeat < REPEAT; repeat++) {
			System.out.println("Repeat numer " + (repeat + 1) + ". Size is " + bounds.size());
			for (int i = bounds.size() - 1; i > 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (eliminate(bounds.get(i), bounds.get(j))) {
						bounds.remove(j);
						i--;
					} else if (reduce(bounds.get(i), bounds.get(j), bounds, j)) {
						if (added)
							i += add;
					}
				}
			}
			System.out.println("\tGoing up");
			for (int i = 0; i < bounds.size() - 1; i++) {
				for (int j = i + 1; j < bounds.size(); j++) {
					if (bounds.get(j)
					          .isTurn() != bounds.get(i)
					                             .isTurn())
						break;
					if (eliminate(bounds.get(i), bounds.get(j))) {
						bounds.remove(j);
						j--;
					} else if (reduce(bounds.get(i), bounds.get(j), bounds, j)) {
						if (added)
							j += add;
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
		System.out.println("Finished. Size is " + bounds.size());
		if (part == 1)
			return smallResult;
		else
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

	private void splitterX(LinkedList<Bounds> bounds) {
		Bounds bound = bounds.pollLast();
		assert bound != null;
		int[] cube = bound.getBounds()
		                  .clone();
		int splitSize = SPLIT_X;
		int min = bound.getBounds()[0];
		int max = bound.getBounds()[1];
		int split = min - min % splitSize;
		if (split < min)
			split += splitSize;
		for (; split < max; split += splitSize) {
			cube[1] = split;
			splitterY(new Bounds(cube, bound.isTurn()), bounds);
			cube[0] = split + 1;
		}
		cube[1] = max;
		splitterY(new Bounds(cube, bound.isTurn()), bounds);
	}

	private boolean eliminate(Bounds bounds1, Bounds bounds2) {
		int[] boundsBig = bounds1.getBounds();
		int[] boundsSmall = bounds2.getBounds();
		return boundsBig[0] <= boundsSmall[0] && boundsBig[1] >= boundsSmall[1] && boundsBig[2] <= boundsSmall[2] && boundsBig[3] >= boundsSmall[3] && boundsBig[4] <= boundsSmall[4] && boundsBig[5] >= boundsSmall[5];
	}

	private boolean reduce(Bounds bounds1, Bounds bounds2, LinkedList<Bounds> bounds, int pos) {
		added = false;
		add = 0;
		int[] boundsStay = bounds1.getBounds()
		                          .clone();
		int[] boundsReduce = bounds2.getBounds()
		                            .clone();
		if (boundsStay[1] < boundsReduce[0] || boundsStay[0] > boundsReduce[1] || boundsStay[3] < boundsReduce[2] || boundsStay[2] > boundsReduce[3] || boundsStay[5] < boundsReduce[4] || boundsStay[4] > boundsReduce[5]) {
			return false;
		}
		if (boundsStay[0] <= boundsReduce[0] && boundsStay[1] >= boundsReduce[1])
			return firstDimensionFullyIn(bounds2, bounds, pos, boundsStay, boundsReduce);
		else if (boundsStay[2] <= boundsReduce[2] && boundsStay[3] >= boundsReduce[3]) {
			return secondDimensionFullyIn(bounds2, bounds, pos, boundsStay, boundsReduce);
		} else if (boundsStay[4] <= boundsReduce[4] && boundsStay[5] >= boundsReduce[5]) {
			if (boundsStay[0] <= boundsReduce[0] && boundsStay[2] <= boundsReduce[2]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				temp[1] = boundsStay[1];
				temp[2] = boundsStay[3] + 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] <= boundsReduce[0] && boundsStay[3] >= boundsReduce[3]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				temp[1] = boundsStay[1];
				temp[3] = boundsStay[2] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[2] <= boundsReduce[2] && boundsStay[1] >= boundsReduce[1]) {
				bounds2.setBoundsI(boundsStay[3] + 1, 2);
				int[] temp = boundsReduce.clone();
				temp[3] = boundsStay[3];
				temp[1] = boundsStay[0] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[3] >= boundsReduce[3] && boundsStay[1] >= boundsReduce[1]) {
				bounds2.setBoundsI(boundsStay[2] - 1, 3);
				int[] temp = boundsReduce.clone();
				temp[2] = boundsStay[2];
				temp[1] = boundsStay[0] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			}
		} else {
			if (boundsStay[1] < boundsReduce[1] && boundsStay[3] < boundsReduce[3] && boundsStay[5] < boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[2] = boundsStay[3] + 1;
				temp[1] = boundsStay[1];
				temp2[3] = boundsStay[3];
				temp2[1] = boundsStay[1];
				temp2[4] = boundsStay[5] + 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[1] < boundsReduce[1] && boundsStay[4] > boundsReduce[4] && boundsStay[3] < boundsReduce[3]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[2] = boundsStay[3] + 1;
				temp[1] = boundsStay[1];
				temp2[3] = boundsStay[3];
				temp2[1] = boundsStay[1];
				temp2[5] = boundsStay[4] - 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[1] < boundsReduce[1] && boundsStay[2] > boundsReduce[2] && boundsStay[5] < boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[3] = boundsStay[2] - 1;
				temp[1] = boundsStay[1];
				temp2[2] = boundsStay[2];
				temp2[1] = boundsStay[1];
				temp2[4] = boundsStay[5] + 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] > boundsReduce[0] && boundsStay[3] < boundsReduce[3] && boundsStay[5] < boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[0] - 1, 1);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[2] = boundsStay[3] + 1;
				temp[0] = boundsStay[0];
				temp2[3] = boundsStay[3];
				temp2[0] = boundsStay[0];
				temp2[4] = boundsStay[5] + 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] > boundsReduce[0] && boundsStay[2] > boundsReduce[2] && boundsStay[5] < boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[0] - 1, 1);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[3] = boundsStay[2] - 1;
				temp[0] = boundsStay[0];
				temp2[2] = boundsStay[2];
				temp2[0] = boundsStay[0];
				temp2[4] = boundsStay[5] + 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] > boundsReduce[0] && boundsStay[3] < boundsReduce[3] && boundsStay[4] > boundsReduce[4]) {
				bounds2.setBoundsI(boundsStay[0] - 1, 1);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[2] = boundsStay[3] + 1;
				temp[0] = boundsStay[0];
				temp2[3] = boundsStay[3];
				temp2[0] = boundsStay[0];
				temp2[5] = boundsStay[4] - 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] > boundsReduce[0] && boundsStay[2] > boundsReduce[2] && boundsStay[4] > boundsReduce[4]) {
				bounds2.setBoundsI(boundsStay[0] - 1, 1);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[3] = boundsStay[2] - 1;
				temp[0] = boundsStay[0];
				temp2[2] = boundsStay[2];
				temp2[0] = boundsStay[0];
				temp2[5] = boundsStay[4] - 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[1] < boundsReduce[1] && boundsStay[2] > boundsReduce[2] && boundsStay[4] > boundsReduce[4]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				int[] temp2 = temp.clone();
				temp[3] = boundsStay[2] - 1;
				temp[1] = boundsStay[1];
				temp2[2] = boundsStay[2];
				temp2[1] = boundsStay[1];
				temp2[5] = boundsStay[4] - 1;
				added = true;
				add = 2;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] > boundsReduce[0] && boundsStay[1] < boundsReduce[1]) {
				if (boundsStay[2] < boundsReduce[2] && boundsStay[4] < boundsReduce[4]) {
					bounds2.setBoundsI(boundsStay[1] + 1, 0);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[1] = boundsStay[0] - 1;
					temp[0] = boundsStay[0];
					temp[1] = boundsStay[1];
					int[] temp2 = temp.clone();
					temp[2] = boundsStay[3] + 1;
					temp2[3] = boundsStay[3];
					temp2[4] = boundsStay[5] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[2] < boundsReduce[2] && boundsStay[5] > boundsReduce[5]) {
					bounds2.setBoundsI(boundsStay[1] + 1, 0);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[1] = boundsStay[0] - 1;
					temp[0] = boundsStay[0];
					temp[1] = boundsStay[1];
					int[] temp2 = temp.clone();
					temp[2] = boundsStay[3] + 1;
					temp2[3] = boundsStay[3];
					temp2[5] = boundsStay[4] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[3] < boundsReduce[3] && boundsStay[4] < boundsReduce[4]) {
					bounds2.setBoundsI(boundsStay[1] + 1, 0);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[1] = boundsStay[0] - 1;
					temp[0] = boundsStay[0];
					temp[1] = boundsStay[1];
					int[] temp2 = temp.clone();
					temp[3] = boundsStay[2] - 1;
					temp2[2] = boundsStay[2];
					temp2[4] = boundsStay[5] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[3] > boundsReduce[3] && boundsStay[5] > boundsReduce[5]) {
					bounds2.setBoundsI(boundsStay[1] + 1, 0);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[1] = boundsStay[0] - 1;
					temp[0] = boundsStay[0];
					temp[1] = boundsStay[1];
					int[] temp2 = temp.clone();
					temp[3] = boundsStay[2] - 1;
					temp2[2] = boundsStay[2];
					temp2[5] = boundsStay[4] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				}
			} else if (boundsStay[2] > boundsReduce[2] && boundsStay[3] < boundsReduce[3]) {
				if (boundsStay[0] < boundsReduce[0] && boundsStay[4] < boundsReduce[4]) {
					bounds2.setBoundsI(boundsStay[3] + 1, 2);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[3] = boundsStay[2] - 1;
					temp[2] = boundsStay[2];
					temp[3] = boundsStay[3];
					int[] temp2 = temp.clone();
					temp[0] = boundsStay[1] + 1;
					temp2[1] = boundsStay[1];
					temp2[4] = boundsStay[5] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[0] < boundsReduce[0] && boundsStay[5] > boundsReduce[5]) {
					bounds2.setBoundsI(boundsStay[3] + 1, 2);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[3] = boundsStay[2] - 1;
					temp[2] = boundsStay[2];
					temp[3] = boundsStay[3];
					int[] temp2 = temp.clone();
					temp[0] = boundsStay[1] + 1;
					temp2[1] = boundsStay[1];
					temp2[5] = boundsStay[4] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[1] < boundsReduce[1] && boundsStay[4] < boundsReduce[4]) {
					bounds2.setBoundsI(boundsStay[3] + 1, 2);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[3] = boundsStay[2] - 1;
					temp[2] = boundsStay[2];
					temp[3] = boundsStay[3];
					int[] temp2 = temp.clone();
					temp[1] = boundsStay[0] - 1;
					temp2[0] = boundsStay[0];
					temp2[4] = boundsStay[5] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[1] > boundsReduce[1] && boundsStay[5] > boundsReduce[5]) {
					bounds2.setBoundsI(boundsStay[3] + 1, 2);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[3] = boundsStay[2] - 1;
					temp[2] = boundsStay[2];
					temp[3] = boundsStay[3];
					int[] temp2 = temp.clone();
					temp[1] = boundsStay[0] - 1;
					temp2[0] = boundsStay[0];
					temp2[5] = boundsStay[4] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				}
			} else if (boundsStay[4] > boundsReduce[4] && boundsStay[5] < boundsReduce[5]) {
				if (boundsStay[0] < boundsReduce[0] && boundsStay[2] < boundsReduce[2]) {
					bounds2.setBoundsI(boundsStay[5] + 1, 4);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[5] = boundsStay[4] - 1;
					temp[4] = boundsStay[4];
					temp[5] = boundsStay[5];
					int[] temp2 = temp.clone();
					temp[0] = boundsStay[1] + 1;
					temp2[1] = boundsStay[1];
					temp2[2] = boundsStay[3] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[0] < boundsReduce[0] && boundsStay[3] > boundsReduce[3]) {
					bounds2.setBoundsI(boundsStay[5] + 1, 4);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[5] = boundsStay[4] - 1;
					temp[4] = boundsStay[4];
					temp[5] = boundsStay[5];
					int[] temp2 = temp.clone();
					temp[0] = boundsStay[1] + 1;
					temp2[1] = boundsStay[1];
					temp2[3] = boundsStay[2] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[1] < boundsReduce[1] && boundsStay[2] < boundsReduce[2]) {
					bounds2.setBoundsI(boundsStay[5] + 1, 4);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[5] = boundsStay[4] - 1;
					temp[4] = boundsStay[4];
					temp[5] = boundsStay[5];
					int[] temp2 = temp.clone();
					temp[1] = boundsStay[0] - 1;
					temp2[0] = boundsStay[0];
					temp2[2] = boundsStay[3] + 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				} else if (boundsStay[1] > boundsReduce[1] && boundsStay[3] > boundsReduce[3]) {
					bounds2.setBoundsI(boundsStay[5] + 1, 4);
					int[] temp = boundsReduce.clone();
					int[] temp3 = boundsReduce.clone();
					temp3[5] = boundsStay[4] - 1;
					temp[4] = boundsStay[4];
					temp[5] = boundsStay[5];
					int[] temp2 = temp.clone();
					temp[1] = boundsStay[0] - 1;
					temp2[0] = boundsStay[0];
					temp2[3] = boundsStay[2] - 1;
					added = true;
					add = 3;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp2, bounds2.isTurn()));
					bounds.add(pos, new Bounds(temp3, bounds2.isTurn()));
					return true;
				}
			}
		}
		return false;
	}

	private void splitterY(Bounds bound, LinkedList<Bounds> bounds) {
		int[] cube = bound.getBounds()
		                  .clone();
		int splitSize = SPLIT_Y;
		int min = bound.getBounds()[2];
		int max = bound.getBounds()[3];
		int split = min - min % splitSize;
		if (split < min)
			split += splitSize;
		for (; split < max; split += splitSize) {
			cube[3] = split;
			splitterZ(new Bounds(cube, bound.isTurn()), bounds);
			cube[2] = split + 1;
		}
		cube[3] = max;
		splitterZ(new Bounds(cube, bound.isTurn()), bounds);
	}

	private boolean firstDimensionFullyIn(Bounds bounds2, LinkedList<Bounds> bounds, int pos, int[] boundsStay,
	                                      int[] boundsReduce) {
		if (boundsStay[2] <= boundsReduce[2] && boundsStay[3] >= boundsReduce[3]) {
			firstTwoDimensionsIn(bounds2, bounds, pos, boundsStay, boundsReduce);
			return true;
		} else if (boundsStay[4] <= boundsReduce[4] && boundsStay[5] >= boundsReduce[5]) {
			middleDimensionOnlyNotIn(bounds2, bounds, pos, boundsStay, boundsReduce);
			return true;
		} else if (boundsStay[2] <= boundsReduce[2] && boundsStay[4] <= boundsReduce[4]) {
			bounds2.setBoundsI(boundsStay[3] + 1, 2);
			int[] temp = boundsReduce.clone();
			temp[3] = boundsStay[3];
			temp[4] = boundsStay[5] + 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
			return true;
		} else if (boundsStay[2] <= boundsReduce[2] && boundsStay[5] >= boundsReduce[5]) {
			bounds2.setBoundsI(boundsStay[3] + 1, 2);
			int[] temp = boundsReduce.clone();
			temp[3] = boundsStay[3];
			temp[5] = boundsStay[4] - 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
			return true;
		} else if (boundsStay[4] <= boundsReduce[4] && boundsStay[3] >= boundsReduce[3]) {
			bounds2.setBoundsI(boundsStay[5] + 1, 4);
			int[] temp = boundsReduce.clone();
			temp[5] = boundsStay[5];
			temp[3] = boundsStay[2] - 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
			return true;
		} else if (boundsStay[5] >= boundsReduce[5] && boundsStay[3] >= boundsReduce[3]) {
			bounds2.setBoundsI(boundsStay[4] - 1, 5);
			int[] temp = boundsReduce.clone();
			temp[4] = boundsStay[4];
			temp[3] = boundsStay[2] - 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
			return true;
		} else {
			if (boundsStay[2] <= boundsReduce[3] && boundsStay[2] > boundsReduce[2]) {
				bounds2.setBoundsI(boundsStay[2], 2);
				int[] temp = boundsReduce.clone();
				temp[3] = boundsStay[2] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[3] >= boundsReduce[2] && boundsStay[3] < boundsReduce[3]) {
				bounds2.setBoundsI(boundsStay[3], 3);
				int[] temp = boundsReduce.clone();
				temp[2] = boundsStay[3] + 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[4] <= boundsReduce[5] && boundsStay[4] > boundsReduce[4]) {
				bounds2.setBoundsI(boundsStay[4], 4);
				int[] temp = boundsReduce.clone();
				temp[5] = boundsStay[4] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[5] >= boundsReduce[4] && boundsStay[5] < boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[5], 5);
				int[] temp = boundsReduce.clone();
				temp[4] = boundsStay[5] + 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			}
		}
		return false;
	}

	private boolean secondDimensionFullyIn(Bounds bounds2, LinkedList<Bounds> bounds, int pos, int[] boundsStay,
	                                       int[] boundsReduce) {
		if (boundsStay[4] <= boundsReduce[4] && boundsStay[5] >= boundsReduce[5]) {
			lastTwoDimensionsFullyIn(bounds2, bounds, pos, boundsStay, boundsReduce);
			return true;
		} else {
			if (boundsStay[0] <= boundsReduce[0] && boundsStay[4] <= boundsReduce[4]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				temp[1] = boundsStay[1];
				temp[4] = boundsStay[5] + 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[0] <= boundsReduce[0] && boundsStay[5] >= boundsReduce[5]) {
				bounds2.setBoundsI(boundsStay[1] + 1, 0);
				int[] temp = boundsReduce.clone();
				temp[1] = boundsStay[1];
				temp[5] = boundsStay[4] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[4] <= boundsReduce[4] && boundsStay[1] >= boundsReduce[1]) {
				bounds2.setBoundsI(boundsStay[5] + 1, 4);
				int[] temp = boundsReduce.clone();
				temp[5] = boundsStay[5];
				temp[1] = boundsStay[0] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else if (boundsStay[5] >= boundsReduce[5] && boundsStay[1] >= boundsReduce[1]) {
				bounds2.setBoundsI(boundsStay[4] - 1, 5);
				int[] temp = boundsReduce.clone();
				temp[4] = boundsStay[4];
				temp[1] = boundsStay[0] - 1;
				added = true;
				add = 1;
				bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
				return true;
			} else {
				if (boundsStay[4] <= boundsReduce[5] && boundsStay[4] > boundsReduce[4]) {
					bounds2.setBoundsI(boundsStay[4], 4);
					int[] temp = boundsReduce.clone();
					temp[5] = boundsStay[4] - 1;
					added = true;
					add = 1;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					return true;
				} else if (boundsStay[5] >= boundsReduce[4] && boundsStay[5] < boundsReduce[5]) {
					bounds2.setBoundsI(boundsStay[5], 5);
					int[] temp = boundsReduce.clone();
					temp[4] = boundsStay[5] + 1;
					added = true;
					add = 1;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					return true;
				} else if (boundsStay[0] <= boundsReduce[1] && boundsStay[0] > boundsReduce[0]) {
					bounds2.setBoundsI(boundsStay[0], 0);
					int[] temp = boundsReduce.clone();
					temp[1] = boundsStay[0] - 1;
					added = true;
					add = 1;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					return true;
				} else if (boundsStay[1] >= boundsReduce[0] && boundsStay[1] < boundsReduce[1]) {
					bounds2.setBoundsI(boundsStay[1], 1);
					int[] temp = boundsReduce.clone();
					temp[0] = boundsStay[1] + 1;
					added = true;
					add = 1;
					bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
					return true;
				}
			}
		}
		return false;
	}

	private void splitterZ(Bounds bound, LinkedList<Bounds> bounds) {
		int[] cube = bound.getBounds()
		                  .clone();
		int splitSize = SPLIT_Z;
		int min = bound.getBounds()[4];
		int max = bound.getBounds()[5];
		int split = min - min % splitSize;
		if (split < min)
			split += splitSize;
		for (; split < max; split += splitSize) {
			cube[5] = split;
			bounds.add(new Bounds(cube, bound.isTurn()));
			cube[4] = split + 1;
		}
		cube[5] = max;
		bounds.add(new Bounds(cube, bound.isTurn()));
	}

	private void firstTwoDimensionsIn(Bounds bounds2, LinkedList<Bounds> bounds, int pos, int[] boundsStay,
	                                  int[] boundsReduce) {
		if (boundsStay[4] <= boundsReduce[4])
			bounds2.setBoundsI(boundsStay[5] + 1, 4);
		else if (boundsStay[5] >= boundsReduce[5])
			bounds2.setBoundsI(boundsStay[4] - 1, 5);
		else {
			bounds2.setBoundsI(boundsStay[4] - 1, 5);
			int[] temp = boundsReduce.clone();
			temp[4] = boundsStay[5] + 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
		}
	}

	private void middleDimensionOnlyNotIn(Bounds bounds2, LinkedList<Bounds> bounds, int pos, int[] boundsStay,
	                                      int[] boundsReduce) {
		if (boundsStay[2] <= boundsReduce[2])
			bounds2.setBoundsI(boundsStay[3] + 1, 2);
		else if (boundsStay[3] >= boundsReduce[3])
			bounds2.setBoundsI(boundsStay[2] - 1, 3);
		else {
			bounds2.setBoundsI(boundsStay[2] - 1, 3);
			int[] temp = boundsReduce.clone();
			temp[2] = boundsStay[3] + 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
		}
	}

	private void lastTwoDimensionsFullyIn(Bounds bounds2, LinkedList<Bounds> bounds, int pos, int[] boundsStay,
	                                      int[] boundsReduce) {
		if (boundsStay[0] <= boundsReduce[0])
			bounds2.setBoundsI(boundsStay[1] + 1, 0);
		else if (boundsStay[1] >= boundsReduce[1])
			bounds2.setBoundsI(boundsStay[0] - 1, 1);
		else {
			bounds2.setBoundsI(boundsStay[0] - 1, 1);
			int[] temp = boundsReduce.clone();
			temp[0] = boundsStay[1] + 1;
			added = true;
			add = 1;
			bounds.add(pos, new Bounds(temp, bounds2.isTurn()));
		}
	}
}