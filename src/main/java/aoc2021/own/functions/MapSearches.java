package aoc2021.own.functions;

public interface MapSearches {
	static void mapBFSSquareNaive(int[][] result, int[][] weights, int maxRight, int maxDown, int startingX, int startingY,
	                        int startingWeight) {
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

	static void mapBFSSquareNaive(long[][] result, long[][] weights, int maxRight, int maxDown, int startingX, int startingY,
	                        int startingWeight) {
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
}
