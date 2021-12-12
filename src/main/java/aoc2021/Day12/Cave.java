package aoc2021.Day12;

import java.util.HashSet;

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
