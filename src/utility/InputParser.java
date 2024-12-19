package utility;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This class implements some input parser functions.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class InputParser {

    /**
     * Private constructor to prevent instantiation.
     */
    private InputParser() {
    }

    /**
     * Parses a square matrix of characters from the given Scanner.
     * The matrix is assumed to be square, with each line having the same length.
     *
     * @param scan the Scanner to read the input from
     * @return a 2D array of characters representing the square matrix
     */
    public static char[][] parseCharSquareMatrix(Scanner scan) {
        String firstLine = scan.nextLine();
        char[][] matrix = new char[firstLine.length()][firstLine.length()];
        matrix[0] = firstLine.toCharArray();
        for (int i = 1; i < matrix.length; i++) {
            matrix[i] = scan.nextLine().toCharArray();
        }
        return matrix;
    }

    /**
     * Parses a square matrix of integers from the given Scanner.
     * The matrix is assumed to be square, with each line having the same length.
     *
     * @param scan      the Scanner to read the input from
     * @param delimiter the delimiter to use when parsing the integers
     * @return a 2D array of integers representing the square matrix
     */
    public static int[][] parseIntegerSquareMatrix(Scanner scan, String delimiter) {
        String firstLine = scan.nextLine();
        int[][] matrix = new int[firstLine.length()][firstLine.length()];
        matrix[0] = Stream.of(firstLine.split(delimiter)).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i < matrix.length; i++) {
            matrix[i] = Stream.of(scan.nextLine().split(delimiter)).mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}