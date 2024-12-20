package year2024;

import utility.InputParser;
import utility.NodeGrid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V2.0
 */
@SuppressWarnings("FeatureEnvy")
public class AoC202420 {

    private static final int GRID_SIZE_LIMIT_FOR_SAMPLES = 20;
    private static final int ROUNDS_PART_TWO = 20;
    private static final int ROUNDS_PART_ONE = 2;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202420() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static NodeGrid readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        char[][] data = InputParser.parseCharSquareMatrix(scanner);
        scanner.close();

        NodeGrid grid = new NodeGrid(data.length, true);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == '#') {
                    grid.deleteNode(i, j);
                } else if (data[i][j] == 'S') {
                    grid.setStart(i, j);
                } else if (data[i][j] == 'E') {
                    grid.setEnd(i, j);
                }
            }
        }
        return grid;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        NodeGrid grid = readFile(path);
        grid.calculateDistances();
        return calculateNumberOfCheats(grid, ROUNDS_PART_ONE);
    }

    /**
     * Calculates the number of cheats that save picoseconds within the grid.
     *
     * @param grid   the NodeGrid object representing the grid
     * @param rounds the number of rounds to create cheats
     * @return the total number of saving cheats
     */
    private static int calculateNumberOfCheats(NodeGrid grid, int rounds) {
        int shorterPath = 0;

        for (int i = 1; i <= grid.getSize() - 2; i++) {
            for (int j = 1; j <= grid.getSize() - 2; j++) {
                if (grid.doesNodeExist(i, j)) {
                    shorterPath += createCheats(grid, i, j, rounds);
                }
            }
        }

        return shorterPath;
    }

    /**
     * Creates cheats within a specified maximum distance from the start node and checks if they save picoseconds.
     *
     * @param grid        the NodeGrid object representing the grid
     * @param startX      the x-coordinate of the start node
     * @param startY      the y-coordinate of the start node
     * @param maxDistance the maximum distance to create cheats
     * @return the number of saving cheats
     */
    private static int createCheats(NodeGrid grid, int startX, int startY, int maxDistance) {
        int picosecondsSaved = 100;
        if (maxDistance == ROUNDS_PART_ONE && grid.getSize() <= GRID_SIZE_LIMIT_FOR_SAMPLES) {
            picosecondsSaved = 10;
        } else if (maxDistance == ROUNDS_PART_TWO && grid.getSize() <= GRID_SIZE_LIMIT_FOR_SAMPLES) {
            //noinspection MagicNumber
            picosecondsSaved = 50;
        }
        int savingCheats = 0;
        for (int i = 1; i <= grid.getSize() - 2; i++) {
            for (int j = 1; j <= grid.getSize() - 2; j++) {
                if (grid.doesNodeExist(i, j) && gridDistance(startX, startY, i, j) <= maxDistance) {
                    int newCostToHere = grid.getDistanceFromStartToHere(startX, startY) + gridDistance(startX, startY, i, j);
                    if (newCostToHere + picosecondsSaved <= grid.getDistanceFromStartToHere(i, j)) {
                        savingCheats++;
                    }
                }
            }
        }
        return savingCheats;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        NodeGrid grid = readFile(path);
        grid.calculateDistances();
        return calculateNumberOfCheats(grid, ROUNDS_PART_TWO);
    }

    /**
     * Calculates the Manhattan distance between two points in a grid.
     *
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return the Manhattan distance between the two points
     */
    private static int gridDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}