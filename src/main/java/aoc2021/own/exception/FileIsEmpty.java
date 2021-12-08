package aoc2021.own.exception;

/**
 * Exception to warn you that you failed to copy-paste data or your chosen method of filling the input produced the file without the input.
 */

public class FileIsEmpty extends Exception {
	public FileIsEmpty(String errorMessage) {
		super(errorMessage);
	}
}