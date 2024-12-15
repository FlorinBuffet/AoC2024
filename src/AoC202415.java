import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 15.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202415 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202415() {
    }

    /**
     * Reads the input file and returns the data for the day's part one of the challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Integers
     * @throws FileNotFoundException if the file is not found
     */
    private static Object[] readFilePartOne(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String data = scanner.nextLine();
        char[][] grid = new char[data.length()][data.length()];
        for (int i = 0; i < data.length(); i++) {
            grid[i] = data.toCharArray();
            data = scanner.nextLine();
        }

        String commands = "";
        while (scanner.hasNextLine()) {
            commands += scanner.nextLine();
        }
        scanner.close();

        Object[] input = {grid, commands};
        return input;
    }

    /**
     * Reads the input file and returns the data for the day's part two of the challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Integers
     * @throws FileNotFoundException if the file is not found
     */
    private static Object[] readFilePartTwo(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String data = scanner.nextLine();
        char[][] grid = new char[data.length()][data.length()];
        for (int i = 0; i < data.length(); i++) {
            data = data.replace("O", "[]");
            data = data.replace("#", "##");
            data = data.replace(".", "..");
            data = data.replace("@", "@.");
            grid[i] = data.toCharArray();
            data = scanner.nextLine();
        }

        String commands = "";
        while (scanner.hasNextLine()) {
            commands += scanner.nextLine();
        }
        scanner.close();

        Object[] input = {grid, commands};
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
        Object[] input = readFilePartOne(path);
        char[][] grid = (char[][]) input[0];
        String commands = (String) input[1];

        while (!commands.isEmpty()) {
            char command = commands.charAt(0);
            commands = commands.substring(1);
            switch (command) {
                case '<':
                    moveOnRow(grid, -1);
                    break;
                case '^':
                    moveOnColumn(grid, -1);
                    break;
                case '>':
                    moveOnRow(grid, 1);
                    break;
                case 'v':
                    moveOnColumn(grid, 1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
        }
        return calculateGPSSum(grid);
    }

    /**
     * Calculates the GPS sum for the grid.
     *
     * @param grid a 2D array representing the grid
     * @return the sum of the GPS values, where each 'O' in the grid contributes
     * 100 times its row index plus its column index to the sum.
     */
    private static int calculateGPSSum(char[][] grid) {
        int sum = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] == 'O') {
                    sum += 100 * row + col;
                }
            }
        }
        return sum;
    }

    /**
     * Finds the position of the robot in the grid.
     *
     * @param grid a 2D array representing the grid
     * @return an array of two integers where the first element is the row index
     * and the second element is the column index of the robot's position.
     * Returns {-1, -1} if the robot is not found.
     */
    private static int[] findRobot(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Prints the grid to the console.
     *
     * @param grid a 2D array representing the grid
     */
    private static void printGrid(char[][] grid) {
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Moves the robot in the grid along the column based on the given direction.
     *
     * @param grid      a 2D array representing the grid
     * @param direction the direction to move the robot, where -1 is up and 1 is down
     */
    @SuppressWarnings("DuplicatedCode")
    private static void moveOnColumn(char[][] grid, int direction) {
        int[] robot = findRobot(grid);
        int currentRow = robot[0];
        int currentCol = robot[1];

        int checkRow = currentRow + direction;
        boolean movePossible = false;

        while (grid[checkRow][currentCol] != '#') {
            if (grid[checkRow][currentCol] == '.') {
                movePossible = true;
                break;
            }
            checkRow += direction;
        }

        if (movePossible) {
            char lastChar = '.';
            do {
                char tmp = grid[currentRow][currentCol];
                grid[currentRow][currentCol] = lastChar;
                lastChar = tmp;
                currentRow += direction;
            } while (grid[currentRow][currentCol] != '.');
            grid[currentRow][currentCol] = lastChar;
        }
    }

    /**
     * Moves the robot in the grid along the row based on the given direction.
     *
     * @param grid      a 2D array representing the grid
     * @param direction the direction to move the robot, where -1 is lef and 1 is right
     */
    @SuppressWarnings("DuplicatedCode")
    private static void moveOnRow(char[][] grid, int direction) {
        int[] robot = findRobot(grid);
        int currentRow = robot[0];
        int currentCol = robot[1];

        int checkCol = currentCol + direction;
        boolean movePossible = false;

        while (grid[currentRow][checkCol] != '#') {
            if (grid[currentRow][checkCol] == '.') {
                movePossible = true;
                break;
            }
            checkCol += direction;
        }

        if (movePossible) {
            char lastChar = '.';
            do {
                char tmp = grid[currentRow][currentCol];
                grid[currentRow][currentCol] = lastChar;
                lastChar = tmp;
                currentCol += direction;
            } while (grid[currentRow][currentCol] != '.');
            grid[currentRow][currentCol] = lastChar;
        }
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        Object[] input = readFilePartTwo(path);
        char[][] grid = (char[][]) input[0];
        String commands = (String) input[1];
        printGrid(grid);

        return 0;
    }
}