package aoc2021.Day19;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import aoc2021.IDay;
import aoc2021.own.functions.DataReader;
import aoc2021.own.objects.Point;

public class Day19 implements IDay {
	public void day() {
		LinkedList<String> data = DataReader.readAlchemyString(DataReader.createFilePath(19));
		System.out.println("Day 19\nThe answer to part 1 is " + part(data, 1) + "\nThe answer to part 2 is " + part(data, 2));
	}

	long part(LinkedList<String> data, int part) {
		LinkedList<SubScanner> scanners = new LinkedList<>();
		HashSet<BeaconPair> beaconPairs = new HashSet<>();
		HashSet<ScannerPair> scannerPairs = new HashSet<>();
		for (var el : data) {
			int index = el.indexOf('r') + 2;
			if (el.charAt(1) == '-') {
				if (el.charAt(index + 1) == ' ')
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)))));
				else
					scanners.add(new SubScanner(Integer.parseInt(String.valueOf(el.charAt(index)) + el.charAt(index + 1))));
			} else {
				assert scanners.peekLast() != null;
				scanners.peekLast()
				        .addBeacon(el);
			}

		}
		for (var el : scanners)
			el.fillMatrix();
		for (int it = 0; it < scanners.size() - 1; it++) {
			for (int jt = it + 1; jt < scanners.size(); jt++)
				beaconPairs.addAll(compareScanners(scanners.get(it), scanners.get(jt), scannerPairs));
		}
		orientation(scanners, scannerPairs, beaconPairs);
		HashSet<Point> allTheBeacons = new HashSet<>();
		for (var el : scanners)
			allTheBeacons.addAll(el.getBeacons());
		long distanceMax = 0;
		for (var el : scanners) {
			for (var er : scanners) {
				if (el.getScannerPosition()
				      .minus(er.getScannerPosition())
				      .distanceFromZero() > distanceMax) {
					distanceMax = el.getScannerPosition()
					                .minus(er.getScannerPosition())
					                .distanceFromZero();
				}
			}
		}
		if (part == 1)
			return allTheBeacons.size();
		else
			return distanceMax;
	}

	private HashSet<BeaconPair> compareScanners(SubScanner scan1, SubScanner scan2, HashSet<ScannerPair> scannerPairs) {
		HashSet<BeaconPair> linked = new HashSet<>();
		for (int i = 0; i < scan1.getSize(); i++) {
			for (int j = 0; j < scan2.getSize(); j++) {
				int counter = 0;
				for (int it = 0; it < scan1.distanceBeacon(i).length; it++) {
					for (int jt = 0; jt < scan2.distanceBeacon(j).length; jt++) {
						if (scan1.distanceBeacon(i)[it] == scan2.distanceBeacon(j)[jt]) {
							counter++;
							break;
						}
					}
					if (counter > 6) {
						linked.add(new BeaconPair(scan1.getNumber(), i, scan2.getNumber(), j));
						break;
					}
				}
			}
		}
		if (linked.size() >= 12)
			scannerPairs.add(new ScannerPair(scan1.getNumber(), scan2.getNumber()));
		return linked;
	}

	private void orientation(LinkedList<SubScanner> scanners, HashSet<ScannerPair> scannerPairs,
	                         HashSet<BeaconPair> beaconPairs) {
		ArrayBlockingQueue<ScannerPair> scannersToOrient = new ArrayBlockingQueue<>(scannerPairs.size());
		for (var el : scannerPairs)
			scannersToOrient.offer(el);
		while (scannersToOrient.size() > 0) {
			ScannerPair pair = scannersToOrient.poll();
			if (!scanners.get(pair.getFirstScanner())
			             .isOriented() && !scanners.get(pair.getSecondScanner())
			                                       .isOriented())
				scannersToOrient.offer(pair);
			else if (scanners.get(pair.getFirstScanner())
			                 .isOriented() && !scanners.get(pair.getSecondScanner())
			                                           .isOriented()) {
				Set<BeaconPair> temp = beaconPairs.stream()
				                                  .filter(s -> s.getFirstScanner() == pair.getFirstScanner() && s.getSecondScanner() == pair.getSecondScanner())
				                                  .collect(Collectors.toSet());
				BeaconPair[] linkedBeacons = new BeaconPair[temp.size()];
				int iterator = 0;
				for (var el : temp) {
					linkedBeacons[iterator] = el;
					iterator++;
				}
				findingXCoordinate(scanners, pair, linkedBeacons);
				findingYCoordinate(scanners, pair, linkedBeacons);
				findingZCoordinate(scanners, pair, linkedBeacons);
				scanners.get(pair.getSecondScanner())
				        .orientate();
				scanners.get(pair.getSecondScanner())
				        .setRelativePosition(scanners.get(pair.getFirstScanner())
				                                     .getBeacons()
				                                     .get(linkedBeacons[0].getFirstBeacon())
				                                     .plus(scanners.get(pair.getSecondScanner())
				                                                   .getBeacons()
				                                                   .get(linkedBeacons[0].getSecondBeacon())));
			} else if (!scanners.get(pair.getFirstScanner())
			                    .isOriented() && scanners.get(pair.getSecondScanner())
			                                             .isOriented()) {
				Set<BeaconPair> temp = beaconPairs.stream()
				                                  .filter(s -> s.getFirstScanner() == pair.getFirstScanner() && s.getSecondScanner() == pair.getSecondScanner())
				                                  .collect(Collectors.toSet());
				BeaconPair[] linkedBeacons = new BeaconPair[temp.size()];
				int iterator = 0;
				for (var el : temp) {
					linkedBeacons[iterator] = el.swap();
					iterator++;
				}
				findingXCoordinate(scanners, pair.swap(), linkedBeacons);
				findingYCoordinate(scanners, pair.swap(), linkedBeacons);
				findingZCoordinate(scanners, pair.swap(), linkedBeacons);
				scanners.get(pair.swap()
				                 .getSecondScanner())
				        .orientate();
				scanners.get(pair.swap()
				                 .getSecondScanner())
				        .setRelativePosition(scanners.get(pair.swap()
				                                              .getFirstScanner())
				                                     .getBeacons()
				                                     .get(linkedBeacons[0].getFirstBeacon())
				                                     .plus(scanners.get(pair.swap()
				                                                            .getSecondScanner())
				                                                   .getBeacons()
				                                                   .get(linkedBeacons[0].getSecondBeacon())));
			}
		}
	}

	private void findingXCoordinate(LinkedList<SubScanner> scanners, ScannerPair pair, BeaconPair[] linkedBeacons) {
		HashMap<String, Integer> findingX = new HashMap<>();
		findingX.put("xx-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingX.put("xx+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingX.put("xy-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingX.put("xy+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingX.put("xz-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		findingX.put("xz+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getX() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getX() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		Collection<Integer> check = findingX.values();
		int zeroCounter = 0;
		for (var el : check) {
			if (el == 0)
				zeroCounter++;
		}
		if (zeroCounter != 1)
			System.out.println("ERROR LOGIC X LOGIC ERROR");
		if (findingX.get("xx+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('x');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(false);
		} else if (findingX.get("xx-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('x');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(true);
		} else if (findingX.get("xy+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('y');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(false);
		} else if (findingX.get("xy-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('y');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(true);
		} else if (findingX.get("xz+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('z');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(false);
		} else if (findingX.get("xz-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setFirst('z');
			scanners.get(pair.getSecondScanner())
			        .setFirstReverse(true);
		}
	}

	private void findingYCoordinate(LinkedList<SubScanner> scanners, ScannerPair pair, BeaconPair[] linkedBeacons) {
		HashMap<String, Integer> findingY = new HashMap<>();
		findingY.put("yx-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingY.put("yx+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingY.put("yy-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingY.put("yy+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingY.put("yz-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		findingY.put("yz+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getY() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getY() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		Collection<Integer> check = findingY.values();
		int zeroCounter = 0;
		for (var el : check) {
			if (el == 0)
				zeroCounter++;
		}
		if (zeroCounter != 1)
			System.out.println("ERROR LOGIC Y LOGIC ERROR");
		if (findingY.get("yx+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('x');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(false);
		} else if (findingY.get("yx-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('x');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(true);
		} else if (findingY.get("yy+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('y');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(false);
		} else if (findingY.get("yy-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('y');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(true);
		} else if (findingY.get("yz+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('z');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(false);
		} else if (findingY.get("yz-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setSecond('z');
			scanners.get(pair.getSecondScanner())
			        .setSecondReverse(true);
		}
	}

	private void findingZCoordinate(LinkedList<SubScanner> scanners, ScannerPair pair, BeaconPair[] linkedBeacons) {
		HashMap<String, Integer> findingZ = new HashMap<>();
		findingZ.put("zx-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingZ.put("zx+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getX()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getX()));
		findingZ.put("zy-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingZ.put("zy+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getY()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getY()));
		findingZ.put("zz-", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() - scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() - scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		findingZ.put("zz+", (scanners.get(linkedBeacons[0].getFirstScanner())
		                             .getBeacon(linkedBeacons[0].getFirstBeacon())
		                             .getZ() + scanners.get(linkedBeacons[0].getSecondScanner())
		                                               .getBeacon(linkedBeacons[0].getSecondBeacon())
		                                               .getZ()) - (scanners.get(linkedBeacons[1].getFirstScanner())
		                                                                   .getBeacon(linkedBeacons[1].getFirstBeacon())
		                                                                   .getZ() + scanners.get(linkedBeacons[1].getSecondScanner())
		                                                                                     .getBeacon(linkedBeacons[1].getSecondBeacon())
		                                                                                     .getZ()));
		Collection<Integer> check = findingZ.values();
		int zeroCounter = 0;
		for (var el : check) {
			if (el == 0)
				zeroCounter++;
		}
		if (zeroCounter != 1)
			System.out.println("ERROR LOGIC Z LOGIC ERROR");
		if (findingZ.get("zx+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('x');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(false);
		} else if (findingZ.get("zx-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('x');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(true);
		} else if (findingZ.get("zy+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('y');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(false);
		} else if (findingZ.get("zy-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('y');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(true);
		} else if (findingZ.get("zz+") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('z');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(false);
		} else if (findingZ.get("zz-") == 0) {
			scanners.get(pair.getSecondScanner())
			        .setThird('z');
			scanners.get(pair.getSecondScanner())
			        .setThirdReverse(true);
		}
	}
}