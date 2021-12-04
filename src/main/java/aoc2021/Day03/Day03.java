package aoc2021.Day03;

import aoc2021.IDay;
import aoc2021.own.functions.DataReader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 implements IDay {
    public void day() throws Exception {
        var data = DataReader.readAlchemyString(DataReader.createFilePath(3));
        System.out.println("Day 3\nThe answer to part 1 is " + part1(data) + "\nThe answer to part 2 is " + part2(data));
    }

    int part1(LinkedList<String> data) {
        String maxBit = "";
        int i1, i0;
        for (int i = 0; i < data.peekFirst().length(); i++) {
            i0 = 0;
            i1 = 0;
            for (var el : data) {
                if (el.charAt(i) == '0')
                    i0++;
                else
                    i1++;
            }
            if (i0 > i1)
                maxBit += "0";
            else
                maxBit += "1";
        }
        int a = 0;
        int b = 0;
        for (int i = maxBit.length() - 1; i >= 0; --i) {
            a += ((maxBit.charAt(i) - '0') * Math.pow(2, (maxBit.length() - i - 1)));
            b += (('1' - maxBit.charAt(i)) * Math.pow(2, (maxBit.length() - i - 1)));
        }
        return a * b;
    }
    int part2(LinkedList<String> data) {
        List<String> oxygen = (List<String>) data.clone();
        List<String> carbon = (List<String>) data.clone();
        long length = data.peekFirst().length();

        for (int i = 0; i < length && oxygen.size() > 1; ++i) {
            int finalI = i;
            long i0 = oxygen.stream().filter(el -> el.charAt(finalI) == '0').count();
            long i1 = oxygen.size() - i0;
            char bit = (i0 > i1) ? '0' : '1';
            oxygen = oxygen.stream().filter(el -> el.charAt(finalI) == bit).collect(Collectors.toList());
        }
        for (int i = 0; i < length && carbon.size() > 1; ++i) {
            int finalI = i;
            long i0 = carbon.stream().filter(el -> el.charAt(finalI) == '0').count();
            long i1 = carbon.size() - i0;
            char bit = (i0 <= i1) ? '0' : '1';
            carbon = carbon.stream().filter(el -> el.charAt(finalI) == bit).collect(Collectors.toList());
        }
        int a = 0;
        int b = 0;
        for (int i = (int) length - 1; i >= 0; --i) {
            a += ((oxygen.get(0).charAt(i) - '0') * Math.pow(2, (length - i - 1)));
            b += ((carbon.get(0).charAt(i) - '0') * Math.pow(2, (length - i - 1)));
        }
        System.out.println(oxygen);
        System.out.println(a + " - " + b);
        return a * b;
    }
}
