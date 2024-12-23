package year2024;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class solves AdventofCode 2024, Day 4.
 *
 * @author Florin Buffet
 * @version V2.1
 */
public class AoC202404 {

    private static final Pattern SAMX = Pattern.compile("SAMX");
    private static final Pattern XMAS = Pattern.compile("XMAS");

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
     */
    @SuppressWarnings({"ObjectAllocationInLoop", "DuplicatedCode"})
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        char[][] playField = InputParser.parseCharSquareMatrix(scanner);
        scanner.close();
        return playField;
    }

    /**
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);
        int found = 0;

        //find horizontal and vertical matches
        for (int i = 0; i < playField.length; i++) {
            CharSequence thisLine = new String(playField[i]);
            String thisColumn = columnAsString(playField, i);
            found += XMAS.split(thisLine, -1).length - 1;
            found += SAMX.split(thisLine, -1).length - 1;
            found += XMAS.split(thisColumn, -1).length - 1;
            found += SAMX.split(thisColumn, -1).length - 1;
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
        StringBuilder thisColumn = new StringBuilder();
        for (char[] chars : playField) {
            thisColumn.append(chars[i]);
        }
        return thisColumn.toString();
    }

    /**
     * Checks if the diagonal from the top-left to the bottom-right contains the word "XMAS" or "SAMX".
     *
     * @param playField the 2D array representing the play field
     * @param i         the row index of the bottom-right character in the diagonal
     * @param j         the column index of the bottom-right character in the diagonal
     * @return true if the diagonal contains "XMAS" or "SAMX", false otherwise
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    private static boolean checkDiagonalFromTopLeft(char[][] playField, int i, int j) {
        boolean fw = playField[i - 3][j - 3] == 'X' && playField[i - 2][j - 2] == 'M' && playField[i - 1][j - 1] == 'A' && playField[i][j] == 'S';
        boolean bw = playField[i - 3][j - 3] == 'S' && playField[i - 2][j - 2] == 'A' && playField[i - 1][j - 1] == 'M' && playField[i][j] == 'X';
        return fw || bw;
    }

    /**
     * Checks if the diagonal from the top-right to the bottom-left contains the word "XMAS" or "SAMX".
     *
     * @param playField the 2D array representing the play field
     * @param i         the row index of the bottom-left character in the diagonal
     * @param j         the column index of the bottom-left character in the diagonal
     * @return true if the diagonal contains "XMAS" or "SAMX", false otherwise
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    private static boolean checkDiagonalFromTopRight(char[][] playField, int i, int j) {
        boolean fw = playField[i - 3][j + 3] == 'X' && playField[i - 2][j + 2] == 'M' && playField[i - 1][j + 1] == 'A' && playField[i][j] == 'S';
        boolean bw = playField[i - 3][j + 3] == 'S' && playField[i - 2][j + 2] == 'A' && playField[i - 1][j + 1] == 'M' && playField[i][j] == 'X';
        return fw || bw;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException if the file is not found
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
     * Checks if the cross centered at the given position contains the word "XMAS" or "SAMX".
     *
     * @param playField the 2D array representing the play field
     * @param i         the row index of the center character in the cross
     * @param j         the column index of the center character in the cross
     * @return true if the cross contains "XMAS" or "SAMX", false otherwise
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