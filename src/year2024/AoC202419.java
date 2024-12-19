package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 19.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202419 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202419() {
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
        //noinspection DynamicRegexReplaceableByCompiledPattern
        List<String> patterns = Arrays.stream(scanner.nextLine().replaceAll(" ", "").split(",")).toList();
        scanner.nextLine();
        Collection<String> desiredPatterns = new ArrayList<>();
        while (scanner.hasNextLine()) {
            desiredPatterns.add(scanner.nextLine());
        }
        scanner.close();
        return new Object[]{patterns, desiredPatterns};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        Object[] data = readFile(path);
        List<String> patterns = (List<String>) data[0];
        Iterable<String> desiredPatterns = (Iterable<String>) data[1];

        int possiblePatterns = 0;
        for (String currentPattern : desiredPatterns) {
            possiblePatterns += findSolutions(currentPattern, patterns, new HashMap<>()) >= 1 ? 1 : 0;
        }

        return possiblePatterns;
    }

    private static long findSolutions(String design, List<String> patterns, Map<String, Long> foundPatterns) {
        long solution;
        if (foundPatterns.containsKey(design)) {
            solution = foundPatterns.get(design);
        } else if (design.isEmpty()) {
            solution = 1;
        } else {
            long count = 0;
            for (String pattern : patterns) {
                if (design.startsWith(pattern)) {
                    String remainingDesign = design.substring(pattern.length());
                    count += findSolutions(remainingDesign, patterns, foundPatterns);
                }
            }

            foundPatterns.put(design, count);
            solution = count;
        }
        return solution;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        Object[] data = readFile(path);
        List<String> patterns = (List<String>) data[0];
        Iterable<String> desiredPatterns = (Iterable<String>) data[1];

        long possibleSolutions = 0;
        for (String currentPattern : desiredPatterns) {
            possibleSolutions += findSolutions(currentPattern, patterns, new HashMap<>());
        }
        return possibleSolutions;
    }
}