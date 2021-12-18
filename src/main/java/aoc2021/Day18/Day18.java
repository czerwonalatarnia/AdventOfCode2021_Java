package aoc2021.Day18;

import java.util.LinkedList;
import java.util.Objects;
import aoc2021.IDay;
import aoc2021.own.functions.Calculator;
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
		return trees.get(0)
		            .calcMagnitude();
	}

	long part2(LinkedList<String> data) {
		LinkedList<NodeBinaryTree> trees = new LinkedList<>();
		LinkedList<PairTree> pairs = new LinkedList<>();
		LinkedList<Long> values = new LinkedList<>();
		for (var el : data)
			trees.add(new NodeBinaryTree(el, 0, null));
		for (var el : trees) {
			for (var er : trees) {
				if (!Objects.equals(el, er)) {
					pairs.add(new PairTree(el, er));
				}
			}
		}
		for (var pair : pairs) {
			NodeBinaryTree first = pair.left;
			NodeBinaryTree second = pair.right;
			values.add(addTrees(first, second).calcMagnitude());
		}
		return Calculator.arrayLongMax(values);
	}

	private NodeBinaryTree addTrees(NodeBinaryTree leftSide, NodeBinaryTree rightSide) {
		NodeBinaryTree newOne = new NodeBinaryTree("[" + leftSide.toString() + "," + rightSide.toString() + "]", 0, null);
		while (newOne.validate()) ;
		return newOne;
	}
}