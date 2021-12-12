package aoc2021.Day12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Path {
	private final String path;
	private final HashSet<String> visited;
	private final HashSet<String> visited2;
	private final LinkedList<String> currentPath;
	private boolean canRepeat;

	public Path(String cave) {
		path = cave;
		visited = new HashSet<>();
		visited.add(cave);
		canRepeat = true;
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
		visited = new HashSet<>(oldPath.visited);
		visited2 = new HashSet<>(oldPath.visited2);
		currentPath = new LinkedList<>(oldPath.currentPath);
		canRepeat = oldPath.canRepeat;

		currentPath.addLast(cave.getName());
		if (cave.getName()
		        .chars()
		        .filter(Character::isUpperCase)
		        .findAny()
		        .isEmpty()) {
			if (visited.add(cave.getName())) {
				if (canRepeat)
					canRepeat = false;
				else
					visited2.add(cave.getName());
			} else
				visited2.add(cave.getName());
		}
	}

	@Override public int hashCode() {
		return Objects.hash(path, visited, visited2, currentPath, canRepeat);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Path path1 = (Path) o;
		return canRepeat == path1.canRepeat && Objects.equals(path, path1.path) && Objects.equals(visited, path1.visited) && Objects.equals(visited2, path1.visited2) && Objects.equals(currentPath, path1.currentPath);
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
