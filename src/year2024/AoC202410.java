package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

//TODO: cleanup class

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
     * @throws FileNotFoundException
     */
    private static int[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int[][] data = new int[1][1];
        int currentLine = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (data.length == 1) {
                data = new int[line.length()][line.length()];
            }
            data[currentLine] = Stream.of(line.split("")).mapToInt(Integer::parseInt).toArray();
            currentLine++;
        }
        scanner.close();
        return data;
    }

    /**
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException
     */
    public static int partOne(String path) throws FileNotFoundException {
        int[][] data = readFile(path);
        int sum = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (data[x][y] == 0) {
                    boolean[][] accessed = new boolean[data.length][data.length];
                    calculateAccessedNine(x, y, data, accessed);
                    sum += countTruesInBooleanArray(accessed);
                }
            }
        }
        return sum;
    }

    /**
     * @param x        x coordinate
     * @param y        y coordinate
     * @param data     the map including height information
     * @param accessed the map of fields that are the highest height that have been accessed
     */
    public static void calculateAccessedNine(int x, int y, int[][] data, boolean[][] accessed) {
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
     * Function that counts the number of true values in a boolean array.
     *
     * @param array the boolean array
     * @return the number of true values in the array
     */
    public static int countTruesInBooleanArray(boolean[][] array) {
        int sum = 0;
        for (boolean[] booleans : array) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException
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
     * @param x    x coordinate
     * @param y    y coordinate
     * @param data the map including height information
     * @return the number of routes to the highest height
     */
    public static int calculateRoutesToNines(int x, int y, int[][] data) {
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

