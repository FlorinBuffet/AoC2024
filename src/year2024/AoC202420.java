package year2024;

import utility.InputParser;
import utility.NodeGrid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202420 {

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
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        char[][] data = InputParser.parseCharSquareMatrix(scanner);
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
        char[][] data = readFile(path);

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
        int shortestPath = grid.getDistanceFromStartToEnd();
        int shorterPath = 0;

        for (int i = 1; i < data.length - 1; i++) {
            for (int j = 1; j < data[i].length - 1; j++) {
                shorterPath += checkIfCheatOver100(grid, i, j, shortestPath) ? 1 : 0;
            }
        }

        return shorterPath;
    }

    private static boolean checkIfCheatOver100(NodeGrid grid, int x, int y, int shortestPath) {
        boolean isSaving = false;
        if (!grid.doesNodeExist(x, y)){
            grid.addNode(x, y);
            int thisPath = grid.getDistanceFromStartToEnd();
            if (thisPath <= shortestPath - 100) {
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
        return 0;
    }
}