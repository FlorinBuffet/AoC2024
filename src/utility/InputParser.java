package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class implements some input parser functions.
 *
 * @author Florin Buffet
 * @version V1.2
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

    /**
     * Parses a list of lists of strings from the given Scanner.
     * Each line of input is split into strings using the specified delimiter.
     *
     * @param scan the Scanner to read the input from
     * @param delimiter the delimiter to use when splitting the strings
     * @return a list of lists of strings, where each inner list represents a line of input
     */
    public static List<List<String>> parseStringListPerLine(Scanner scan, String delimiter) {
        List<List<String>> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            list.add(Stream.of(scan.nextLine().split(delimiter)).collect(Collectors.toList()));
        }
        return list;
    }

    /**
     * Parses a list of lists of integers from the given Scanner.
     * Each line of input is split into integers using the specified delimiter.
     *
     * @param scan the Scanner to read the input from
     * @param delimiterRegex the delimiter as regex to use when splitting the integers
     * @return a list of lists of strings, where each inner list represents a line of input
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public static List<List<Integer>> parseIntegerListPerLine(Scanner scan, String delimiterRegex) {
        List<List<Integer>> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                return list;
            }
            list.add(Stream.of(line.split(delimiterRegex)).map(Integer::parseInt).collect(Collectors.toList()));
        }
        return list;
    }

    /**
     * Parses a list of lists of longs from the given Scanner.
     * Each line of input is split into longs using the specified delimiter.
     *
     * @param scan the Scanner to read the input from
     * @param delimiterRegex the delimiter as regex to use when splitting the longs
     * @return a list of lists of strings, where each inner list represents a line of input
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public static List<List<Long>> parseLongListPerLine(Scanner scan, String delimiterRegex, String remove) {
        List<List<Long>> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                return list;
            }
            line = line.replace(remove, "");
            list.add(Stream.of(line.split(delimiterRegex)).map(Long::parseLong).collect(Collectors.toList()));
        }
        return list;
    }

    /**
     * Parses a list of integers from the given Scanner.
     * The method reads integers from the Scanner until no more integers are available.
     *
     * @param scan the Scanner to read the integers from
     * @return a list of integers parsed from the Scanner
     */
    public static List<Integer> parseIntegerList(Scanner scan) {
        List<Integer> list = new ArrayList<>();
        while (scan.hasNextInt()) {
            list.add(scan.nextInt());
        }
        return list;
    }
}