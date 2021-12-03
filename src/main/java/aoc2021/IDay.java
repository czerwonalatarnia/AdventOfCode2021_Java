package aoc2021;

import aoc2021.own.exception.FileIsEmpty;

import java.io.FileNotFoundException;

public interface IDay {
    // Common function to call from Main
    public void day() throws FileIsEmpty, FileNotFoundException, Exception;
}
