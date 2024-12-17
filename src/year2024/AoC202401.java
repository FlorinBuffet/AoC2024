package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 1.
 *
 * @author Florin Buffet
 * @version V1.4
 */
public class AoC202401 {

    private static final char INPUT_FILE_SPLIT_CHARACTER = ' ';

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202401() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of lists
     * @throws FileNotFoundException if the file is not found
     */
    private static List[] readFile(String path) throws FileNotFoundException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            left.add(Integer.parseInt(data.substring(0, data.indexOf(INPUT_FILE_SPLIT_CHARACTER))));
            right.add(Integer.parseInt(data.substring(data.indexOf(INPUT_FILE_SPLIT_CHARACTER) + 1).trim()));
        }
        scanner.close();
        return new List[]{left, right};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        // Read the input file and get the data as two lists
        //noinspection unchecked
        List<Integer>[] lists = readFile(path);
        List<Integer> left = lists[0];
        List<Integer> right = lists[1];

        // Sort both lists
        Collections.sort(left);
        Collections.sort(right);

        int returnVal = 0;
        // Calculate the sum of absolute differences between corresponding elements of the two lists
        for (int i = 0; i < left.size(); i++) {
            returnVal += Math.abs(left.get(i) - right.get(i));
        }
        return returnVal;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        // Read the input file and get the data as two lists
        //noinspection unchecked
        List<Integer>[] lists = readFile(path);
        List<Integer> left = lists[0];
        List<Integer> right = lists[1];

        long returnVal = 0;
        // Calculate the sum of the product of each number in the left list and its frequency in the right list
        for (int num : left) {
            returnVal += (long) Collections.frequency(right, num) * num;
        }
        return returnVal;
    }
}