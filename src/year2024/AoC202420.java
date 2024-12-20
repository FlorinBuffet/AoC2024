package year2024;

import utility.InputParser;
import utility.NodeGrid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: improve implementation

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.1
 */
@SuppressWarnings("FeatureEnvy")
public class AoC202420 {

    private static final int GRID_SIZE_LIMIT_FOR_SAMPLES = 20;

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

        int shortestPath = grid.getDistanceFromStartToEnd();
        int shorterPath = 0;

        for (int i = 1; i < grid.getSize() - 1; i++) {
            for (int j = 1; j < grid.getSize() - 1; j++) {
                shorterPath += checkIfCheatOver100(grid, i, j, shortestPath) ? 1 : 0;
            }
        }

        return shorterPath;
    }

    /**
     * Checks if adding a node at the specified coordinates saves more than 100 picoseconds.
     *
     * @param grid         the NodeGrid object representing the grid
     * @param x            the x-coordinate of the node
     * @param y            the y-coordinate of the node
     * @param shortestPath the current shortest path length
     * @return true if adding the node saves more than 100 picoseconds, false otherwise
     */
    private static boolean checkIfCheatOver100(NodeGrid grid, int x, int y, int shortestPath) {
        boolean isSaving = false;
        if (!grid.doesNodeExist(x, y)) {
            grid.addNode(x, y);
            int thisPath = grid.getDistanceFromStartToEnd();
            int picosecondsSaved = grid.getSize() <= GRID_SIZE_LIMIT_FOR_SAMPLES ? 10 : 100;
            if (thisPath <= shortestPath - picosecondsSaved) {
                isSaving = true;
            }
            grid.deleteNode(x, y);
        }
        return isSaving;
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

        int shortestPath = grid.getDistanceFromStartToEnd();
        int shorterPath = 0;

        for (int i = 1; i <= grid.getSize() - 2; i++) {
            for (int j = 1; j <= grid.getSize() - 2; j++) {
                if (grid.doesNodeExist(i, j)) {
                    shorterPath += createCheatsUpTo20(grid, i, j, shortestPath, path);
                }
            }
        }

        return shorterPath / 2;
    }

    /**
     * Creates cheats up to 20 nodes away from the start node and checks if they save more than 50 or 100 picoseconds.
     *
     * @param grid         the NodeGrid object representing the grid
     * @param startX       the x-coordinate of the start node
     * @param startY       the y-coordinate of the start node
     * @param shortestPath the current shortest path length
     * @param path         the path to the input file
     * @return the number of saving cheats
     * @throws FileNotFoundException if the file is not found
     */
    private static int createCheatsUpTo20(NodeGrid grid, int startX, int startY, int shortestPath, String path)
            throws FileNotFoundException {
        int savingCheats = 0;
        for (int i = 1; i <= grid.getSize() - 2; i++) {
            for (int j = 1; j <= grid.getSize() - 2; j++) {
                if (grid.doesNodeExist(i, j) && gridDistance(startX, startY, i, j) <= GRID_SIZE_LIMIT_FOR_SAMPLES) {
                    NodeGrid sample = readFile(path);
                    sample.addCrossEdge(startX, startY, i, j, gridDistance(startX, startY, i, j));
                    int thisPath = sample.getDistanceFromStartToEnd();
                    //noinspection MagicNumber
                    int picosecondsSaved = grid.getSize() <= GRID_SIZE_LIMIT_FOR_SAMPLES ? 50 : 100;
                    if (thisPath <= shortestPath - picosecondsSaved) {
                        savingCheats++;
                    }
                }
            }
        }
        return savingCheats;
    }

    private static int gridDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}