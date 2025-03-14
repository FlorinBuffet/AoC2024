package year2024;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 2.
 *
 * @author Florin Buffet
 * @version V1.3
 */
public class AoC202402 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202402() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of lists of integers
     * @throws FileNotFoundException if the file is not found
     */
    private static List<List<Integer>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<Integer>> input = InputParser.parseIntegerListPerLine(scanner, " ");
        scanner.close();
        return input;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<List<Integer>> inputData = readFile(path);

        int safeReports = 0;
        while (!inputData.isEmpty()) {
            List<Integer> currentSample = inputData.removeFirst();

            boolean[] checkResult = checkSample(currentSample);
            boolean isSafe = checkResult[0];
            boolean isIncreasing = checkResult[1];
            boolean isDecreasing = checkResult[2];

            if (isSafe && (isIncreasing ^ isDecreasing)) safeReports++;
        }

        return safeReports;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        List<List<Integer>> inputData = readFile(path);

        int safeReports = 0;
        while (!inputData.isEmpty()) {
            List<Integer> currentSample = inputData.removeFirst();
            boolean safeVariant = false;
            for (int i = 0; i < currentSample.size(); i++) {
                //noinspection ObjectAllocationInLoop
                List<Integer> currentCase = new ArrayList<>(currentSample);

                //noinspection SuspiciousListRemoveInLoop
                currentCase.remove(i);

                boolean[] checkResult = checkSample(currentCase);
                boolean isSafe = checkResult[0];
                boolean isIncreasing = checkResult[1];
                boolean isDecreasing = checkResult[2];

                if (isSafe && (isIncreasing ^ isDecreasing)) safeVariant = true;
            }
            if (safeVariant) safeReports++;
        }
        return safeReports;
    }


    /**
     * Checks if the sample is safe, increasing, or decreasing.
     *
     * @param currentSample the sample to check
     * @return an array of booleans: [isSafe, isIncreasing, isDecreasing]
     */
    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    private static boolean[] checkSample(List<Integer> currentSample) {
        int lastNumber = currentSample.removeFirst();
        boolean isIncreasing = false;
        boolean isDecreasing = false;
        boolean isSafe = true;

        for (int number : currentSample) {
            int diff = Math.abs(number - lastNumber);
            if (diff < 1 || diff > 3) {
                isSafe = false;
            } else if (number > lastNumber) {
                isIncreasing = true;
            } else if (number < lastNumber) {
                isDecreasing = true;
            }
            lastNumber = number;
        }
        boolean[] results = new boolean[3];
        results[0] = isSafe;
        results[1] = isIncreasing;
        results[2] = isDecreasing;

        return results;
    }
}