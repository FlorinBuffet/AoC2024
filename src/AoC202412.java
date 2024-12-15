import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day 12.
 *
 * @author Florin Buffet
 * @version V1.3
 */
public class AoC202412 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202412() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of lists
     * @throws FileNotFoundException if the file is not found
     */
    @SuppressWarnings("DuplicatedCode") // Every challenge has an own readFile method
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        char[][] playField = new char[1][0];
        int currentLine = 0;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (playField.length == 1) {
                playField = new char[data.length()][data.length()];
            }
            for (int i = 0; i < data.length(); i++) {
                playField[currentLine][i] = data.charAt(i);
            }
            currentLine++;
        }
        scanner.close();
        return playField;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        char[][] beet = readFile(path);
        boolean[][] fenced = new boolean[beet.length][beet.length];
        int sum = 0;
        for (int i = 0; i < beet.length; i++) {
            for (int j = 0; j < beet[i].length; j++) {
                if (Character.isUpperCase(beet[i][j])) {
                    int field = calculateField(beet, beet[i][j], i, j);
                    int fence = calculateFence(beet, fenced, beet[i][j], i, j);
                    sum += field * fence;
                }

            }
        }
        return sum;
    }


    /**
     * Recursively calculates the size of the field for a given plant at a given plot.
     *
     * @param beet  the 2D array representing the beet field
     * @param plant the character representing the plant to calculate the field for
     * @param i     the row index of the current position
     * @param j     the column index of the current position
     * @return the size of the field for the given plant
     */
    private static int calculateField(char[][] beet, char plant, int i, int j) {
        int field = 0;
        if (beet[i][j] == plant && Character.isUpperCase(beet[i][j])) {
            field++;
            beet[i][j] = Character.toLowerCase(beet[i][j]);
            if (i > 0) field += calculateField(beet, plant, i - 1, j);
            if (i < beet.length - 1) field += calculateField(beet, plant, i + 1, j);
            if (j > 0) field += calculateField(beet, plant, i, j - 1);
            if (j < beet[i].length - 1) field += calculateField(beet, plant, i, j + 1);
        }
        return field;
    }

    /**
     * Recursively calculates the length of the fence required for a given plant.
     *
     * @param beet   the 2D array representing the beet field
     * @param fenced the 2D array indicating which plots have been fenced
     * @param plant  the character representing the plant to calculate the fence for
     * @param i      the row index of the current position
     * @param j      the column index of the current position
     * @return the length of the fence required for the given plant
     */
    @SuppressWarnings("MethodWithMoreThanThreeNegations")
    private static int calculateFence(char[][] beet, boolean[][] fenced, char plant, int i, int j) {
        int fence = 0;
        if (!fenced[i][j]) {
            fenced[i][j] = true;
            if (i == 0 || beet[i - 1][j] != plant) fence++;
            else fence += calculateFence(beet, fenced, plant, i - 1, j);
            if (i == beet.length - 1 || beet[i + 1][j] != plant) fence++;
            else fence += calculateFence(beet, fenced, plant, i + 1, j);
            if (j == 0 || beet[i][j - 1] != plant) fence++;
            else fence += calculateFence(beet, fenced, plant, i, j - 1);
            if (j == beet[i].length - 1 || beet[i][j + 1] != plant) fence++;
            else fence += calculateFence(beet, fenced, plant, i, j + 1);
        }
        return fence;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        char[][] beet = readFile(path);
        int sum = 0;
        for (int i = 0; i < beet.length; i++) {
            for (int j = 0; j < beet[i].length; j++) {
                if (Character.isUpperCase(beet[i][j])) {
                    int field = calculateField(beet, beet[i][j], i, j);
                    AoC202412_Bot bot = new AoC202412_Bot(beet, i, j);
                    int fence = bot.countStraights();
                    System.out.println("Char: " + beet[i][j] + " Field: " + field + " Fence: "+fence);
                    sum += field * fence;
                }

            }
        }
        return sum;
    }
}