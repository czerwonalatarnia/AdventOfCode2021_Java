package aoc2021.Day18;

import java.util.LinkedList;
import java.util.Objects;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day18 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(18));
		System.out.println("Day 18\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	long part1(LinkedList<String> data) {
		LinkedList<NodeBinaryTree> trees = new LinkedList<>();
		for (var el : data)
			trees.add(new NodeBinaryTree(el, 0, null));
		while (trees.size() > 1) {
			NodeBinaryTree first = trees.poll();
			NodeBinaryTree second = trees.poll();
			assert second != null;
			trees.addFirst(addTrees(first, second));
		}
		System.out.println(trees.get(0));
		return 0;
	}

	long part2(LinkedList<String> data) {
		return 0;
	}

	private NodeBinaryTree addTrees(NodeBinaryTree leftSide, NodeBinaryTree rightSide) {
		System.out.println("Add " + leftSide + " to " + rightSide);
		NodeBinaryTree newOne = new NodeBinaryTree("[" + leftSide.toString() + "," + rightSide.toString() + "]", 0, null);
		System.out.println("Before reduction: " + newOne);
		newOne.validate();
		System.out.println(newOne + "\n");
		return newOne;
	}
}