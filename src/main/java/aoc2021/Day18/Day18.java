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

	int part1(LinkedList<String> data) {
		LinkedList<NodeBinaryTree> trees = new LinkedList<>();
		for (var el : data)
			trees.add(new NodeBinaryTree(el, 0, null));
		while (trees.size() > 1)
			trees.addFirst(addTrees(trees.poll(), Objects.requireNonNull(trees.poll())));
		System.out.println(trees.get(0));
		return 0;
	}

	int part2(LinkedList<String> data) {
		return 0;
	}

	private NodeBinaryTree addTrees(NodeBinaryTree leftSide, NodeBinaryTree rightSide) {
		NodeBinaryTree newOne = new NodeBinaryTree("[" + leftSide.toString() + "," + rightSide.toString() + "]", 0, null);
		newOne.validate();
		return newOne;
	}
}