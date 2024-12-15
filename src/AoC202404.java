import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day 4.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class AoC202404 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202404() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of chars
     * @throws FileNotFoundException
     */
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
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException
     */
    public static int partOne(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);
        int found = 0;

        //find horizontal and vertical matches
        for (int i = 0; i < playField.length; i++) {
            CharSequence thisLine = new String(playField[i]);
            String thisColumn = columnAsString(playField, i);
            found += StringUtils.countMatches(thisLine, "XMAS");
            found += StringUtils.countMatches(thisLine, "SAMX");
            found += StringUtils.countMatches(thisColumn, "XMAS");
            found += StringUtils.countMatches(thisColumn, "SAMX");
        }

        for (int i = 3; i < playField.length; i++) {
            for (int j = 3; j < playField.length; j++) {
                if (checkDiagonalFromTopLeft(playField, i, j)) {
                    found++;
                }
            }
            for (int j = playField.length - 4; j >= 0; j--) {
                if (checkDiagonalFromTopRight(playField, i, j)) {
                    found++;
                }
            }
        }

        return found;
    }

    private static String columnAsString(char[][] playField, int i) {
        //noinspection NonConstantStringShouldBeStringBuffer
        String thisColumn = "";
        for (char[] chars : playField) {
            thisColumn += chars[i];
        }
        return thisColumn;
    }

    /**
     * Check if the diagonal from top left to bottom right contains the word "XMAS" or "SAMX".
     *
     * @param playField
     * @param i
     * @param j
     * @return true if the diagonal contains "XMAS" or "SAMX"
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    private static boolean checkDiagonalFromTopLeft(char[][] playField, int i, int j) {
        boolean forward = playField[i - 3][j - 3] == 'X' && playField[i - 2][j - 2] == 'M' && playField[i - 1][j - 1] == 'A' && playField[i][j] == 'S';
        boolean backward = playField[i - 3][j - 3] == 'S' && playField[i - 2][j - 2] == 'A' && playField[i - 1][j - 1] == 'M' && playField[i][j] == 'X';
        return forward || backward;
    }

    /**
     * Check if the diagonal from top right to bottom left contains the word "XMAS" or "SAMX".
     *
     * @param playField
     * @param i
     * @param j
     * @return true if the diagonal contains "XMAS" or "SAMX"
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    private static boolean checkDiagonalFromTopRight(char[][] playField, int i, int j) {
        boolean forward = playField[i - 3][j + 3] == 'X' && playField[i - 2][j + 2] == 'M' && playField[i - 1][j + 1] == 'A' && playField[i][j] == 'S';
        boolean backward = playField[i - 3][j + 3] == 'S' && playField[i - 2][j + 2] == 'A' && playField[i - 1][j + 1] == 'M' && playField[i][j] == 'X';
        return forward || backward;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException
     */
    public static int partTwo(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);
        int found = 0;

        for (int i = 1; i < playField.length - 1; i++) {
            for (int j = 1; j < playField.length - 1; j++) {
                if (checkCross(playField, i, j)) found++;
            }
        }

        return found;
    }

    /**
     * Check if the cross across the field contains "MAS" or "SAM".
     *
     * @param playField
     * @param i
     * @param j
     * @return true if the cross contains "MAS" or "SAM"
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    private static boolean checkCross(char[][] playField, int i, int j) {
        char center = playField[i][j];
        char topLeft = playField[i - 1][j - 1];
        char topRight = playField[i - 1][j + 1];
        char bottomLeft = playField[i + 1][j - 1];
        char bottomRight = playField[i + 1][j + 1];
        boolean result = false;

        if (center == 'A') {
            if (topLeft == 'M' && bottomRight == 'S' || (topLeft == 'S' && bottomRight == 'M')) { //NOSONAR
                result = (topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M');
            }
        }
        return result;
    }
}