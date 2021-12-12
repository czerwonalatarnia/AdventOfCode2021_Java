package aoc2021.Day12;

import java.util.HashSet;
import java.util.LinkedList;

public class Path {
	private final String path;
	private final HashSet<String> visited;
	private final HashSet<String> visited2;
	private final LinkedList<String> currentPath;
	private int repeatSmall;

	public Path(String cave) {
		path = cave;
		visited = new HashSet<>();
		visited.add(cave);
		repeatSmall = 0;
		visited2 = new HashSet<>();
		visited2.add("start");
		visited2.add("end");
		currentPath = new LinkedList<>();
		currentPath.add(cave);
	}

	public Path(Path oldPath, String cave) {
		path = oldPath.path + cave;
		visited = new HashSet<>(oldPath.visited);
		visited2 = new HashSet<>(oldPath.visited2);
		visited.add(cave);
		currentPath = new LinkedList<>(oldPath.currentPath);
		currentPath.addLast(cave);
	}

	public Path(Path oldPath, Cave cave) {
		path = oldPath.path + cave;
		currentPath = new LinkedList<>(oldPath.currentPath);
		currentPath.addLast(cave.getName());
		visited = new HashSet<>(oldPath.visited);
		visited2 = new HashSet<>(oldPath.visited2);
		repeatSmall = oldPath.repeatSmall;
		visited2.add("start");
		visited2.add("end");
		if (cave.getName().chars()
		             .filter(Character::isUpperCase)
		             .findAny()
		             .isEmpty()) {
			if (visited.add(cave.getName())) {
				if (repeatSmall > 0)
					visited2.add(cave.getName());
				else
					repeatSmall++;
			} else
				visited2.add(cave.getName());
		}
	}

	public String currentCave() {
		return this.currentPath.getLast();
	}

	public boolean isRepeat(String cave) {
		return visited.contains(cave);
	}

	public boolean isRepeat2(String cave) {
		return visited2.contains(cave);
	}

	public String finalPath() {
		return currentPath.toString();
	}
}
