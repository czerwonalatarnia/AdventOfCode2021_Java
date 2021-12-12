package aoc2021.Day12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Path {
	private final String path;
	private final HashSet<String> visited;
	private final LinkedList<String> currentPath;
	private boolean canRepeat;

	public Path(String cave) {
		path = cave;
		visited = new HashSet<>();
		visited.add(cave);
		canRepeat = true;
		currentPath = new LinkedList<>();
		currentPath.add(cave);
	}

	public Path(Path oldPath, String cave) {
		path = oldPath.path + cave;
		visited = new HashSet<>(oldPath.visited);
		visited.add(cave);
		currentPath = new LinkedList<>(oldPath.currentPath);
		currentPath.addLast(cave);
	}

	public Path(Path oldPath, Cave cave) {
		path = oldPath.path + cave.getName();
		visited = new HashSet<>(oldPath.visited);
		visited.add(cave.getName());
		currentPath = new LinkedList<>(oldPath.currentPath);
		canRepeat = oldPath.canRepeat;
		currentPath.addLast(cave.getName());
	}

	public boolean canRepeat() {
		return canRepeat;
	}

	public void setCanRepeat(boolean canRepeat) {
		this.canRepeat = canRepeat;
	}

	@Override public int hashCode() {
		return Objects.hash(path, visited, currentPath, canRepeat);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Path path1 = (Path) o;
		return canRepeat == path1.canRepeat && Objects.equals(path, path1.path) && Objects.equals(visited, path1.visited) && Objects.equals(currentPath, path1.currentPath);
	}

	public String currentCave() {
		return this.currentPath.getLast();
	}

	public boolean isRepeat(String cave) {
		return visited.contains(cave);
	}

	public String finalPath() {
		StringBuilder print = new StringBuilder();
		for (var el : currentPath) {
			print.append(el)
			     .append(',');
		}
		print.deleteCharAt(print.length() - 1);
		return String.valueOf(print);
	}
}
