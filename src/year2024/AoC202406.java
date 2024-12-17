package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 6.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class AoC202406 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202406() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of lists
     * @throws FileNotFoundException if the file is not found
     */
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        char[][] data = new char[1][1];
        int currentLine = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (data.length == 1) {
                data = new char[line.length()][line.length()];
            }
            data[currentLine] = line.toCharArray();
            currentLine++;
        }
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
        return calculateUsedFields(data);
    }

    /**
     * Helper function to calculate the number of fields visited by the walker.
     *
     * @param data the input field
     * @return how many fields could be visited, -1 if the walker is stuck
     */
    @SuppressWarnings({"MethodWithMultipleReturnPoints", "OverlyLongMethod", "OverlyComplexMethod"})
    private static int calculateUsedFields(char[][] data) {
        int x = 0;
        int y = 0;

        //find guard
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (isGuardAtPosition(j, data[i])) {
                    x = i;
                    y = j;
                }
            }
        }

        int[] lastReturnValue;

        if (data[x][y] == '^') {
            lastReturnValue = new int[]{x, y, 0};
        } else if (data[x][y] == '>') {
            lastReturnValue = new int[]{x, y, 1};
        } else if (data[x][y] == 'v') {
            lastReturnValue = new int[]{x, y, 2};
        } else {
            lastReturnValue = new int[]{x, y, 3};
        }

        int counter = 0;
        do {
            if (lastReturnValue[2] == 0) {
                data[x][y] = '^';
            } else if (lastReturnValue[2] == 1) {
                data[x][y] = '>';
            } else if (lastReturnValue[2] == 2) {
                data[x][y] = 'v';
            } else if (lastReturnValue[2] == 3) {
                data[x][y] = '<';
            }
            lastReturnValue = newPosition(data, x, y);
            data[x][y] = 'X';
            x = lastReturnValue[0];
            y = lastReturnValue[1];
            counter++;
        } while ((lastReturnValue[0] > -1) && (lastReturnValue[1] > -1) && counter < (data.length * data.length));

        if (counter == data.length * data.length) {
            return -1;
        }

        int result = 0;
        for (char[] chars : data) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * Checks if the given position in the data array contains the guard.
     *
     * @param j    the index to check in the data array
     * @param data the array of characters representing the field
     * @return true if the position contains a guard character ('<', '>', '^', 'v'), false otherwise
     */
    private static boolean isGuardAtPosition(int j, char[] data) {
        return data[j] == '<' || data[j] == '>' || data[j] == '^' || data[j] == 'v';
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        char[][] data = readFile(path);
        int possibleSolutions = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                char[][] copyMatrix = new char[data.length][data.length];
                for (int k = 0; k < data.length; k++) {
                    copyMatrix[k] = Arrays.copyOf(data[k], data[k].length);
                }
                copyMatrix[i][j] = '#';
                if (calculateUsedFields(copyMatrix) == -1) {
                    possibleSolutions++;
                }
            }
        }

        return possibleSolutions;
    }

    /**
     * Calculates the new position and direction of the walker.
     *
     * @param field The play field.
     * @param x     walker's x position
     * @param y     walker's y position
     * @return the new position and direction of the walker
     */
    private static int[] newPosition(char[][] field, int x, int y) {
        //return array: x, y, direction (0: up, 1: right, 2: down, 3: left)
        int[] returnValue = new int[3];

        if (field[x][y] == '<') {
            if (y == 0) {
                returnValue = new int[]{-1, -1, 0};
            } else if (field[x][y - 1] == '#') {
                returnValue = new int[]{x, y, 0};
            } else {
                returnValue = new int[]{x, y - 1, 3};
            }
        } else if (field[x][y] == '>') {
            if (y == field.length - 1) {
                returnValue = new int[]{-1, -1, 0};
            } else if (field[x][y + 1] == '#') {
                returnValue = new int[]{x, y, 2};
            } else {
                returnValue = new int[]{x, y + 1, 1};
            }
        } else if (field[x][y] == '^') {
            if (x == 0) {
                returnValue = new int[]{-1, -1, 0};
            } else if (field[x - 1][y] == '#') {
                returnValue = new int[]{x, y, 1};
            } else {
                returnValue = new int[]{x - 1, y, 0};
            }
        } else if (field[x][y] == 'v') {
            if (x == field.length - 1) {
                returnValue = new int[]{-1, -1, 0};
            } else if (field[x + 1][y] == '#') {
                returnValue = new int[]{x, y, 3};
            } else {
                returnValue = new int[]{x + 1, y, 2};
            }
        }
        return returnValue;
    }
}

