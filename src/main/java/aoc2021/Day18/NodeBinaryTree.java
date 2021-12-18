package aoc2021.Day18;

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
		boolean wasChange = false;
		if (leftNode != null)
			wasChange = leftNode.validate();
		if (!wasChange && level > 3 && (leftNode != null ? leftNode.value : -1) > -1 && (rightNode != null ? rightNode.value : -1) > -1) {
			this.explode();
			System.out.println(this);
			wasChange = true;
		} if (!wasChange && this.value > 9) {
			System.out.println("In split - level " + level + ": " + this);
			System.out.print("Split ");
			int newNumber = this.value;
			this.leftNode = new NodeBinaryTree(newNumber / 2, this.level + 1, this);
			this.rightNode = new NodeBinaryTree(newNumber - newNumber / 2, this.level + 1, this);
			this.value = -1;
			System.out.println(this);
			wasChange = true;
		}
		if (rightNode != null && wasChange)
			wasChange = rightNode.validate();
		return wasChange;
	}

	public void explode() {
		System.out.println("In explode - level " + level + ": " + this);
		System.out.print("Explode ");
		value = 0;
		int splitLeft = this.leftNode.value;
		int splitRight = this.rightNode.value;
		this.leftNode = null;
		this.rightNode = null;
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

	@Override public String toString() {
		if (leftNode == null && rightNode == null)
			return String.valueOf(value);
		else {
			assert leftNode != null;
			assert rightNode != null;
			return "[" + leftNode + "," + rightNode + "]";
		}
	}
}