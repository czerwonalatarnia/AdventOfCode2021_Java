package aoc2021.Day25;

import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day25 implements IDay {
	static int MOVES = 1;
	static int STEP = 0;

	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(25));
		System.out.println(
				"Day 25\nThe final answer is " + part(data) + "\n\n\nCONGRATULATIONS, YOU HAVE FINISHED AOC\n\n");
	}

	int part(LinkedList<String> data) {
		int width = data.getFirst()
		                .length();
		int height = data.size();
		char[][] map = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				map[x][y] = data.get(y)
				                .charAt(x);
		}
		while (MOVES != 0) {
			MOVES = 0;
			for (int y = 0; y < height; y++) {
				int pos = width;
				while ((map[(pos) % width][y] == '>') && (map[(pos - 1) % width][y] == '>'))
					pos--;
				for (int moveX = 0; moveX < width; moveX++) {
					if ((map[(pos + moveX) % width][y] == '>') && (map[(pos + moveX + 1) % width][y] == '.')) {
						MOVES++;
						map[(pos + moveX) % width][y] = '.';
						map[(pos + moveX + 1) % width][y] = '>';
						moveX++;
					}
				}
			}
			for (int x = 0; x < width; x++) {
				int pos = height;
				while ((map[x][(pos) % height] == 'v') && (map[x][(pos - 1) % height] == 'v'))
					pos--;
				for (int moveY = 0; moveY < height; moveY++) {
					if ((map[x][(pos + moveY) % height] == 'v') && (map[x][(pos + moveY + 1) % height] == '.')) {
						MOVES++;
						map[x][(pos + moveY) % height] = '.';
						map[x][(pos + moveY + 1) % height] = 'v';
						moveY++;
					}
				}
			}
			//printMap(map, width, height);
			STEP ++;
		}
		return STEP;
	}

	private void printMap(char[][] map, int width, int height) {
		System.out.println();
		System.out.println();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				System.out.print(map[x][y]);
			System.out.println();
		}
	}
}