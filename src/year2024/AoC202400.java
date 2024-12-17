package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202400 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202400() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static int readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        scanner.close();
        return 0;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        return 0;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        return 0;
    }
}