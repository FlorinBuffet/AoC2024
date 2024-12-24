package year2024;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//TODO: automate part 2

/**
 * This class solves AdventofCode 2024, Day 24.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202424 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202424() {
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
        List<List<String>> values = InputParser.parseStringListPerLine(scanner, " ", ":");
        List<List<String>> ops = InputParser.parseStringListPerLine(scanner, " ", "-> ");

        Map<String, Integer> valuesMap = new TreeMap<>();
        for (List<String> val : values) {
            valuesMap.put(val.get(0), Integer.parseInt(val.get(1)));
        }

        scanner.close();
        return new Object[]{valuesMap, ops};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partOne(String path) throws FileNotFoundException {
        Object[] obj = readFile(path);
        Map<String, Integer> values = (Map<String, Integer>) obj[0];
        List<List<String>> ops = (List<List<String>>) obj[1];

        while (!ops.isEmpty()) {
            List<String> currentOp = ops.removeFirst();
            String var1 = currentOp.get(0);
            String var2 = currentOp.get(2);


            if (values.containsKey(var1) && values.containsKey(var2)) {
                int result = 0;
                switch (currentOp.get(1)) {
                    case "AND" -> result = values.get(var1) & values.get(var2);
                    case "OR" -> result = values.get(var1) | values.get(var2);
                    case "XOR" -> result = values.get(var1) ^ values.get(var2);
                }
                values.put(currentOp.get(3), result);
            } else {
                ops.add(currentOp);
            }
        }

        return Long.parseLong(calculateZAppended(values), 2);
    }

    private static String calculateZAppended(Map<String, Integer> map) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!entry.getKey().isEmpty() && entry.getKey().charAt(0) == 'z') {
                result.insert(0, entry.getValue());
            }
        }
        return result.toString();
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {


        return 0;
    }
}