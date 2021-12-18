package aoc2021.Day18;

import java.util.Objects;

public class NodeBinaryTree {
	private final int level;
	private final String thisString;
	private final NodeBinaryTree parentNode;
	private NodeBinaryTree leftNode;
	private NodeBinaryTree rightNode;
	private int value = -1;

	public NodeBinaryTree(int split, int level, NodeBinaryTree parentNode) {
		this.level = level;
		this.thisString = String.valueOf(split);
		this.value = split;
		this.parentNode = parentNode;
		this.leftNode = null;
		this.rightNode = null;
	}

	public NodeBinaryTree(String number, int level, NodeBinaryTree parentNode) {
		this.level = level;
		this.thisString = number;
		this.parentNode = parentNode;
		if (number.charAt(0) == '[') {
			int brackets = 1;
			int i = 1;
			do {
				if (number.charAt(i) == '[')
					brackets++;
				else if (number.charAt(i) == ']')
					brackets--;
				i++;
			} while (brackets != 1);
			while (number.charAt(i) != ',')
				i++;
			int j = i + 1;
			do {
				if (number.charAt(j) == '[')
					brackets++;
				else if (number.charAt(j) == ']')
					brackets--;
				j++;
			} while (brackets != 1);
			leftNode = new NodeBinaryTree(number.substring(1, i), level + 1, this);
			rightNode = new NodeBinaryTree(number.substring(i + 1, j), level + 1, this);
		} else {
			StringBuilder val = new StringBuilder();
			for (int i = 0; i < number.length() && number.charAt(i) >= '0' && number.charAt(i) <= '9'; i++)
				val.append(number.charAt(i));
			this.value = Integer.parseInt(String.valueOf(val));
			this.leftNode = null;
			this.rightNode = null;
		}
	}

	public boolean validate() {
		if (validateExplode())
			return true;
		else
			return validateSplit();
	}

	public boolean validateSplit() {
		boolean wasChange = false;
		if (leftNode != null)
			wasChange = leftNode.validateSplit();
		if (!wasChange && this.value > 9) {
			int newNumber = this.value;
			this.leftNode = new NodeBinaryTree(newNumber / 2, this.level + 1, this);
			this.rightNode = new NodeBinaryTree(newNumber - newNumber / 2, this.level + 1, this);
			this.value = -1;
			wasChange = true;
		}
		if (rightNode != null && !wasChange)
			wasChange = rightNode.validateSplit();
		return wasChange;
	}

	private boolean validateExplode() {
		boolean wasChange = false;
		if (leftNode != null)
			wasChange = leftNode.validateExplode();
		if (!wasChange && level > 3) {
			if (leftNode != null && rightNode != null) {
				if (leftNode.value > -1 && rightNode.value > -1) {
					explode();
					wasChange = true;
				}
			}
		}
		if (rightNode != null && !wasChange)
			wasChange = rightNode.validateExplode();
		return wasChange;
	}

	public void explode() {
		value = 0;
		int splitLeft = this.leftNode.value;
		int splitRight = this.rightNode.value;
		leftNode = null;
		rightNode = null;
		NodeBinaryTree leftNumber = this;
		NodeBinaryTree parent = this.parentNode;
		while (parent.parentNode != null) {
			if (leftNumber == parent.leftNode) {
				leftNumber = parent;
				parent = leftNumber.parentNode;
			} else
				break;
		}
		if (parent.parentNode != null || leftNumber != parent.leftNode) {
			leftNumber = parent.leftNode;
			while (leftNumber.rightNode != null)
				leftNumber = leftNumber.rightNode;
			if (leftNumber.value < 0)
				System.out.println("ERROR LEFT ERROR");
			leftNumber.value += splitLeft;
		}
		NodeBinaryTree rightNumber = this;
		parent = this.parentNode;
		while (parent.parentNode != null) {
			if (rightNumber == parent.rightNode) {
				rightNumber = parent;
				parent = rightNumber.parentNode;
			} else
				break;
		}
		if (parent.parentNode != null || rightNumber != parent.rightNode) {
			rightNumber = parent.rightNode;
			while (rightNumber.leftNode != null)
				rightNumber = rightNumber.leftNode;
			if (rightNumber.value < 0)
				System.out.println("ERROR RIGHT ERROR");
			rightNumber.value += splitRight;
		}
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NodeBinaryTree that = (NodeBinaryTree) o;
		return level == that.level && value == that.value && thisString.equals(that.thisString) && parentNode.equals(that.parentNode) && leftNode.equals(that.leftNode) && rightNode.equals(that.rightNode);
	}

	@Override public int hashCode() {
		return Objects.hash(level, thisString, parentNode, leftNode, rightNode, value);
	}

	@Override public String toString() {
		if (leftNode == null && rightNode == null)
			return String.valueOf(value);
		else {
			assert leftNode != null;
			assert rightNode != null;
			return "[" + leftNode + "," + rightNode + "]";
		}
	}

	public long calcMagnitude() {
		if (value > -1)
			return value;
		else
			return 3 * leftNode.calcMagnitude() + 2 * rightNode.calcMagnitude();
	}
}