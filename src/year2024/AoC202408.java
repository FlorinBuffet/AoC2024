package year2024;

import utility.BooleanGridHelper;
import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 8.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202408 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202408() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        char[][] input = InputParser.parseCharSquareMatrix(scanner);
        scanner.close();
        return input;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        char[][] input = readFile(path);
        boolean[][] antinode = new boolean[input.length][input[0].length];
        for (int i = 0; i < antinode.length; i++) {
            for (int j = 0; j < antinode[0].length; j++) {
                setAntinodesForAntenna(input, antinode, i, j, false);
            }
        }
        return BooleanGridHelper.countTrue(antinode);
    }

    /**
     * Sets the antinodes for an antenna in the given input grid.
     * If the character at the specified coordinates is not a dot ('.'),
     * it finds all matching characters in the grid and sets the antinodes
     * accordingly.
     *
     * @param input the 2D array of characters representing the input grid
     * @param antinodes the 2D boolean array to mark the antinodes
     * @param x the x-coordinate of the antenna
     * @param y the y-coordinate of the antenna
     * @param completeAntinodeLine whether to set a complete line of antinodes
     */
    private static void setAntinodesForAntenna(char[][] input, boolean[][] antinodes, int x, int y, boolean completeAntinodeLine) {
        if (input[x][y] != '.') {
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[0].length; j++) {
                    if (input[i][j] == input[x][y] && (i != x || j != y)) {
                        if (completeAntinodeLine)
                            setLineAntinodesForPair(antinodes, x, y, i, j);
                        else
                            setAntinodesForPair(antinodes, x, y, i, j);
                    }
                }
            }
        }
    }

    /**
     * Sets the antinodes for a pair of coordinates in the given antinodes grid.
     * Depending on the relative positions of the coordinates, it sets the antinodes
     * at specific positions calculated based on the differences in x and y coordinates.
     *
     * @param antinodes the 2D boolean array to mark the antinodes
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     */
    @SuppressWarnings("FeatureEnvy")
    private static void setAntinodesForPair(boolean[][] antinodes, int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x1 - x2);
        int yDiff = Math.abs(y1 - y2);

        //noinspection OverlyComplexBooleanExpression
        if (((x1 < x2) && (y1 < y2)) || ((x1 > x2) && (y1 > y2))) {
            BooleanGridHelper.setIfValid(antinodes, Math.min(x1, x2) - xDiff, Math.min(y1, y2) - yDiff, true);
            BooleanGridHelper.setIfValid(antinodes, Math.max(x1, x2) + xDiff, Math.max(y1, y2) + yDiff, true);
        } else {
            BooleanGridHelper.setIfValid(antinodes, Math.min(x1, x2) - xDiff, Math.max(y1, y2) + yDiff, true);
            BooleanGridHelper.setIfValid(antinodes, Math.max(x1, x2) + xDiff, Math.min(y1, y2) - yDiff, true);
        }
    }

    /**
     * Sets a line of antinodes for a pair of coordinates in the given antinodes grid.
     * Depending on the relative positions of the coordinates, it sets the antinodes
     * at specific positions calculated based on the differences in x and y coordinates.
     *
     * @param antinodes the 2D boolean array to mark the antinodes
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     */
    @SuppressWarnings("FeatureEnvy")
    private static void setLineAntinodesForPair(boolean[][] antinodes, int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x1 - x2);
        int yDiff = Math.abs(y1 - y2);
        for (int i = 0; i < antinodes.length / Math.max(xDiff, yDiff); i++) {
            int newXTop = Math.min(x1, x2) - xDiff * i;
            int newXBottom = Math.max(x1, x2) + xDiff * i;
            int newYTop = Math.min(y1, y2) - yDiff * i;
            int newYBottom = Math.max(y1, y2) + yDiff * i;
            //noinspection OverlyComplexBooleanExpression
            if (((x1 < x2) && (y1 < y2)) || ((x1 > x2) && (y1 > y2))) {
                BooleanGridHelper.setIfValid(antinodes, newXTop, newYTop, true);
                BooleanGridHelper.setIfValid(antinodes, newXBottom, newYBottom, true);
            } else {
                BooleanGridHelper.setIfValid(antinodes, newXTop, newYBottom, true);
                BooleanGridHelper.setIfValid(antinodes, newXBottom, newYTop, true);
            }
        }
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        char[][] input = readFile(path);
        boolean[][] antinode = new boolean[input.length][input[0].length];
        for (int i = 0; i < antinode.length; i++) {
            for (int j = 0; j < antinode[0].length; j++) {
                setAntinodesForAntenna(input, antinode, i, j, true);
            }
        }
        return BooleanGridHelper.countTrue(antinode);
    }
}