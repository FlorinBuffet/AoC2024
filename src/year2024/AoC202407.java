package year2024;

import utility.InputParser;
import utility.MathHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 7.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class AoC202407 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202407() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as a List of Lists of Longs
     * @throws FileNotFoundException if the file is not found
     */
    @SuppressWarnings("RegExpAnonymousGroup")
    private static List<List<Long>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<Long>> data = InputParser.parseLongListPerLine(scanner, " ", ":");
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
    public static long partOne(String path) throws FileNotFoundException {
        List<List<Long>> samples = readFile(path);
        long sum = 0;
        for (List<Long> sample : samples) {
            sum += testValueIfValidTwoOperators(sample);
        }
        return sum;
    }

    /**
     * Tests if the given data can produce the target value using two operators (addition and multiplication).
     *
     * @param data the list of Long values where the first element is the target value
     * @return the target value if it can be produced, otherwise 0
     */
    private static long testValueIfValidTwoOperators(List<Long> data) {
        long targetValue = data.removeFirst();
        long returnValue = 0;

        List<String> binaryPermutations = MathHelper.createAllBinaryPermutations(data.size() - 1);

        for (String binary : binaryPermutations) {
            long testValue = data.getFirst();
            for (int i = 0; i < data.size() - 1; i++) {
                if (binary.charAt(i) == '1') { // 1 means add
                    testValue += data.get(i + 1);
                } else { //0 means multiply
                    testValue *= data.get(i + 1);
                }
            }
            if (testValue == targetValue) {
                returnValue = targetValue;
            }
        }
        return returnValue;
    }

    /**
     * Tests if the given data can produce the target value using three operators (addition, multiplication, and concatenation).
     *
     * @param data the list of Long values where the first element is the target value
     * @return the target value if it can be produced, otherwise 0
     */
    @SuppressWarnings("StringConcatenationMissingWhitespace")
    private static long testValueIfValidThreeOperators(List<Long> data) {
        long targetValue = data.removeFirst();
        long returnValue = 0;

        List<String> ternaryPermutations = MathHelper.createAllTernaryPermutations(data.size() - 1);

        for (String ternary : ternaryPermutations) {
            long testValue = data.getFirst();
            for (int i = 0; i < data.size() - 1; i++) {
                if (ternary.charAt(i) == '0') { // 0 means add
                    testValue += data.get(i + 1);
                } else if (ternary.charAt(i) == '1') { // 1 means multiply
                    testValue *= data.get(i + 1);
                } else { // 2 means concatenation
                    testValue = Long.parseLong("" + testValue + data.get(i + 1));
                }
            }
            if (testValue == targetValue) {
                returnValue = targetValue;
            }
        }
        return returnValue;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<List<Long>> samples = readFile(path);
        long sum = 0;
        for (List<Long> sample : samples) {
            sum += testValueIfValidThreeOperators(sample);
        }
        return sum;
    }
}