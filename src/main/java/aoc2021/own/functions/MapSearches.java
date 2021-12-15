package aoc2021.own.functions;

import java.util.PriorityQueue;
import aoc2021.own.objects.Node2D;

public interface MapSearches {
	static void mapBFSSquareNaive(int[][] result, int[][] weights, int maxRight, int maxDown, int startingX,
	                              int startingY, int startingWeight) {
		result[startingX][startingY] = startingWeight;
		boolean ifBacktracked = true;
		while (ifBacktracked) {
			ifBacktracked = false;
			for (int y = 0; y < maxDown; y++) {
				for (int x = 0; x < maxRight; x++) {
					if (x < maxRight - 1) {
						if (result[x + 1][y] > result[x][y] + weights[x + 1][y])
							result[x + 1][y] = result[x][y] + weights[x + 1][y];
					}
					if (x > 0) {
						if (result[x - 1][y] > result[x][y] + weights[x - 1][y]) {
							result[x - 1][y] = result[x][y] + weights[x - 1][y];
							ifBacktracked = true;
						}
					}
					if (y > 0) {
						if (result[x][y - 1] > result[x][y] + weights[x][y - 1]) {
							result[x][y - 1] = result[x][y] + weights[x][y - 1];
							ifBacktracked = true;
						}
					}
					if (y < maxDown - 1) {
						if (result[x][y + 1] > result[x][y] + weights[x][y + 1])
							result[x][y + 1] = result[x][y] + weights[x][y + 1];
					}
				}
			}
		}
	}

	static void mapBFSSquare(int[][] result, int[][] weights, int maxRight, int maxDown, int startingX, int startingY,
	                         int startingWeight) {
		PriorityQueue<Node2D> queue = new PriorityQueue<>();
		queue.offer(new Node2D(startingX, startingY, startingWeight));
		while (!queue.isEmpty()) {
			Node2D front = queue.poll();
			assert front != null;
			int x = front.getX();
			int y = front.getY();
			if (result[x][y] > front.getWeight())
				result[x][y] = front.getWeight();
			else
				continue;
			if (x < maxRight - 1) {
				if (result[x + 1][y] > result[x][y] + weights[x + 1][y])
					queue.offer(new Node2D(x + 1, y, result[x][y] + weights[x + 1][y]));
			}
			if (x > 0) {
				if (result[x - 1][y] > result[x][y] + weights[x - 1][y])
					queue.offer(new Node2D(x - 1, y, result[x][y] + weights[x - 1][y]));
			}
			if (y > 0) {
				if (result[x][y - 1] > result[x][y] + weights[x][y - 1])
					queue.offer(new Node2D(x, y - 1, result[x][y] + weights[x][y - 1]));
			}
			if (y < maxDown - 1) {
				if (result[x][y + 1] > result[x][y] + weights[x][y + 1])
					queue.offer(new Node2D(x, y + 1, result[x][y] + weights[x][y + 1]));
			}
		}
	}
}
