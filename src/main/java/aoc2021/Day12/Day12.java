package aoc2021.Day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

public class Day12 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(12));
		System.out.println("Day 12\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
	}

	int part1(LinkedList<String> data) {
		HashMap<String, Cave> caves = new HashMap<>();
		if (mapTheCaves(data, caves))
			return -1;
		LinkedList<Path> pathBuilding = new LinkedList<>();
		HashSet<Path> paths = new HashSet<>();
		pathBuilding.add(new Path("start"));
		pathFinder(caves, pathBuilding, paths);
		return paths.size();
	}

	int part2(LinkedList<String> data) {
		HashMap<String, Cave> caves = new HashMap<>();
		if (mapTheCaves(data, caves))
			return -1;
		LinkedList<Path> pathBuilding = new LinkedList<>();
		HashSet<Path> paths = new HashSet<>();
		pathBuilding.add(new Path("start"));
		pathFinder2(caves, pathBuilding, paths);
		return paths.size();
	}

	private boolean mapTheCaves(LinkedList<String> data, HashMap<String, Cave> caves) {
		for (var line : data) {
			String[] connection = line.split("-");
			if (connection.length != 2) {
				System.out.println("Error, wrong number of caves.");
				return true;
			}
			for (int i = 0; i < connection.length; i++) {
				if (!caves.containsKey(connection[i])) {
					caves.put(connection[i], new Cave(connection[i], connection[1 - i]));
				} else {
					caves.replace(connection[i], new Cave(connection[i], caves.get(connection[i]), connection[1 - i]));
				}
			}
		}
		return false;
	}

	private void pathFinder(HashMap<String, Cave> caves, LinkedList<Path> pathBuilding, HashSet<Path> paths) {
		while (!pathBuilding.isEmpty()) {
			Path currentPath = pathBuilding.poll();
			if (currentPath.currentCave()
			               .equals("end"))
				continue;
			for (var connected : caves.get(currentPath.currentCave())
			                          .getConnected()) {
				if (connected.chars()
				             .filter(Character::isUpperCase)
				             .findAny()
				             .isEmpty()) {
					if (connected.equals("end")) {
						paths.add(new Path(currentPath, "end"));
						continue;
					}
					if (currentPath.isRepeat(connected))
						continue;
				}
				pathBuilding.add(new Path(currentPath, connected));
			}
		}
	}

	private void pathFinder2(HashMap<String, Cave> caves, LinkedList<Path> pathBuilding, HashSet<Path> paths) {
		while (!pathBuilding.isEmpty()) {
			Path currentPath = pathBuilding.poll();
			if (currentPath.currentCave()
			               .equals("end"))
				continue;
			for (var connected : caves.get(currentPath.currentCave())
			                          .getConnected()) {
				if (connected.chars()
				             .filter(Character::isUpperCase)
				             .findAny()
				             .isEmpty()) {
					if (connected.equals("end")) {
						paths.add(new Path(currentPath, caves.get("end")));
						continue;
					}
					if (currentPath.isRepeat(connected)) {
						if (currentPath.isRepeat2(connected)) {
							continue;
						}
						if (currentPath.canRepeat()) {
							currentPath.setCanRepeat(false);
						} else
							continue;
					}
				}
				pathBuilding.add(new Path(currentPath, caves.get(connected)));
			}
		}
		for (var asd : paths) {
			System.out.println(asd.finalPath());
		}
	}
}