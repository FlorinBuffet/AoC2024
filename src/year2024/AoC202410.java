package year2024;

import utility.BooleanGridHelper;
import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 10.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202410 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202410() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of chars
     * @throws FileNotFoundException if the file is not found
     */
    private static int[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int[][] data = InputParser.parseIntegerSquareMatrix(scanner, "");
        scanner.close();
        return data;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        int[][] data = readFile(path);
        int sum = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (data[x][y] == 0) {
                    boolean[][] accessed = new boolean[data.length][data.length];
                    calculateAccessedNine(x, y, data, accessed);
                    sum += BooleanGridHelper.countTrue(accessed);
                }
            }
        }
        return sum;
    }

    /**
     * Recursively marks cells with a value of 9 as accessed starting from the given coordinates.
     * The method checks the adjacent cells (up, down, left, right) and continues the search if the adjacent cell
     * has a value that is one greater than the current cell.
     *
     * @param x the x-coordinate of the starting cell
     * @param y the y-coordinate of the starting cell
     * @param data the 2D array representing the grid with height information
     * @param accessed the 2D boolean array to mark the accessed cells
     */
    private static void calculateAccessedNine(int x, int y, int[][] data, boolean[][] accessed) {
        if (x < data.length - 1 && data[x][y] + 1 == data[x + 1][y]) {
            calculateAccessedNine(x + 1, y, data, accessed);
        }
        if (x > 0 && data[x][y] + 1 == data[x - 1][y]) {
            calculateAccessedNine(x - 1, y, data, accessed);
        }
        if (y < data.length - 1 && data[x][y] + 1 == data[x][y + 1]) {
            calculateAccessedNine(x, y + 1, data, accessed);
        }
        if (y > 0 && data[x][y] + 1 == data[x][y - 1]) {
            calculateAccessedNine(x, y - 1, data, accessed);
        }
        if (data[x][y] == 9) {
            accessed[x][y] = true;
        }
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        int[][] data = readFile(path);
        int sum = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (data[x][y] == 0) {
                    sum += calculateRoutesToNines(x, y, data);
                }
            }
        }
        return sum;
    }

    /**
     * Recursively calculates the number of routes to cells with a value of 9 starting from the given coordinates.
     * The method checks the adjacent cells (up, down, left, right) and continues the search if the adjacent cell
     * has a value that is one greater than the current cell.
     *
     * @param x    the x-coordinate of the starting cell
     * @param y    the y-coordinate of the starting cell
     * @param data the 2D array representing the grid with height information
     * @return the number of routes to cells with a value of 9
     */
    private static int calculateRoutesToNines(int x, int y, int[][] data) {
        int sum = 0;
        if (x < data.length - 1 && data[x][y] + 1 == data[x + 1][y]) {
            sum += calculateRoutesToNines(x + 1, y, data);
        }
        if (x > 0 && data[x][y] + 1 == data[x - 1][y]) {
            sum += calculateRoutesToNines(x - 1, y, data);
        }
        if (y < data.length - 1 && data[x][y] + 1 == data[x][y + 1]) {
            sum += calculateRoutesToNines(x, y + 1, data);
        }
        if (y > 0 && data[x][y] + 1 == data[x][y - 1]) {
            sum += calculateRoutesToNines(x, y - 1, data);
        }
        if (data[x][y] == 9) {
            sum = 1;
        }
        return sum;
    }
}

