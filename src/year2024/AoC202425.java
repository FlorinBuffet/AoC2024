package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202425 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202425() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        Collection<int[]> keys = new ArrayList<>();
        Collection<int[]> locks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            if (keys.size() + locks.size() != 0) {
                scanner.nextLine();
            }
            boolean isLock = scanner.nextLine().charAt(0) == '#';
            int[] thisItem = new int[5];
            for (int i = 0; i < 5; i++) {
                String line = scanner.nextLine();
                thisItem[0] += line.charAt(0) == '#' ? 1 : 0;
                thisItem[1] += line.charAt(1) == '#' ? 1 : 0;
                thisItem[2] += line.charAt(2) == '#' ? 1 : 0;
                thisItem[3] += line.charAt(3) == '#' ? 1 : 0;
                thisItem[4] += line.charAt(4) == '#' ? 1 : 0;
            }
            scanner.nextLine();
            if (isLock) {
                locks.add(thisItem);
            } else {
                keys.add(thisItem);
            }
        }
        scanner.close();
        return new Object[]{keys, locks};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    public static int partOne(String path) throws FileNotFoundException {
        Object[] data = readFile(path);
        Iterable<int[]> keys = (Iterable<int[]>) data[0];
        Iterable<int[]> locks = (Iterable<int[]>) data[1];
        int possiblePairs = 0;
        for (int[] key : keys) {
            for (int[] lock : locks) {
                if (key[0] + lock[0] <= 5 && key[1] + lock[1] <= 5 && key[2] + lock[2] <= 5 && key[3] + lock[3] <= 5 && key[4] + lock[4] <= 5) {
                    possiblePairs++;
                }
            }
        }
        return possiblePairs;
    }
}