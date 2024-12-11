import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 11.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202411 {

    private static final int ITERATIONS_PART_ONE = 25;
    private static final int YEAR_TO_MULTIPLY_RULE_THREE = 2024;
    private static final int ITERATIONS_PART_TWO = 75;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202411() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of chars
     * @throws FileNotFoundException if the file is not found
     */
    private static List<Long> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();
        List<Long> data = new ArrayList<>();
        for (String s : line.split(" ")) {
            data.add(Long.parseLong(s));
        }
        scanner.close();
        return data;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<Long> data = readFile(path);
        for (int i = 0; i < ITERATIONS_PART_ONE; i++) calculateIterationPartOne(data);
        return data.size();
    }

    /**
     * Calculates one iteration of the challenge in the list format.
     *
     * @param list input list of longs
     */
    private static void calculateIterationPartOne(List<Long> list) {
        for (int i = 0; i < list.size(); i++) {
            long current = list.remove(i);
            if (current == 0L) {
                list.add(i, 1L);
            } else if ((current + "").length() % 2 == 0) {
                String complete = current + "";
                long first = Long.parseLong(complete.substring(0, complete.length() / 2));
                long second = Long.parseLong(complete.substring(complete.length() / 2));
                list.add(i, first);
                list.add(i + 1, second);
                //noinspection AssignmentToForLoopParameter
                i++;
            } else {
                list.add(i, current * YEAR_TO_MULTIPLY_RULE_THREE);
            }
        }
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<Long> data = readFile(path);
        Map<Long, Long> map = new HashMap<>();
        for (long l : data) {
            map.put(l, 1L);
        }
        for (int i = 1; i <= ITERATIONS_PART_TWO; i++) {
            map = calculateIterationPartTwo(map);
        }
        long sum = 0;
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    /**
     * Calculates one iteration of the challenge in the map format.
     *
     * @param map input list of longs
     */
    private static Map<Long, Long> calculateIterationPartTwo(Map<Long, Long> map) {
        Map<Long, Long> newMap = new HashMap<>();
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            long currentKey = entry.getKey();
            long currentValue = entry.getValue();

            if (currentKey == 0) {
                newMap.put(1L, currentValue + newMap.getOrDefault(1L, 0L));
            } else if ((currentKey + "").length() % 2 == 0) {
                String complete = currentKey + "";
                long first = Integer.parseInt(complete.substring(0, complete.length() / 2));
                long second = Integer.parseInt(complete.substring(complete.length() / 2));
                newMap.put(first, currentValue + newMap.getOrDefault(first, 0L));
                newMap.put(second, currentValue + newMap.getOrDefault(second, 0L));
            } else {
                long newValue = currentValue + newMap.getOrDefault(currentKey * YEAR_TO_MULTIPLY_RULE_THREE, 0L);
                newMap.put(currentKey * YEAR_TO_MULTIPLY_RULE_THREE, newValue);
            }
        }
        return newMap;
    }
}

