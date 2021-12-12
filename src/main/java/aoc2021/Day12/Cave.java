package aoc2021.Day12;

import java.util.HashSet;
import java.util.Objects;

public class Cave {
	private final String name;
	private HashSet<String> connected = new HashSet<>();

	public Cave(String name, String connectTo) {
		this.name = name;
		connected.add(connectTo);
	}

	public Cave(String name, Cave cave, String connectTo) {
		this.name = name;
		this.connected = new HashSet<>(cave.connected);
		connected.add(connectTo);
	}

	@Override public int hashCode() {
		return Objects.hash(name, connected);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cave cave = (Cave) o;
		return Objects.equals(name, cave.name) && Objects.equals(connected, cave.connected);
	}

	public String getName() {
		return name;
	}

	public HashSet<String> getConnected() {
		return connected;
	}

	public String connectedToString() {
		return connected.toString();
	}
}
