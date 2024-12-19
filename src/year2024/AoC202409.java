package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

//TODO: Fix Part 2

/**
 * This class solves AdventofCode 2024, Day 9.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202409 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202409() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of chars
     */
    private static List<Integer> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();
        //noinspection DynamicRegexReplaceableByCompiledPattern
        int[] data = Stream.of(line.split("")).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<>();
        int fileNumber = 0;
        boolean isFile = true;
        for (int i : data) {
            for (int j = 0; j < i; j++) {
                list.add(isFile ? fileNumber : -1);
            }
            fileNumber = isFile ? fileNumber + 1 : fileNumber;
            isFile = !isFile;
        }
        return list;
    }

    /**
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static long partOne(String path) throws FileNotFoundException {
        List<Integer> data = readFile(path);
        int start = 0;
        int end = data.size() - 1;
        while (start < end) {
            while (data.get(end) == -1) {
                end--;
            }
            if (data.get(start) > -1) {
                start++;
            } else {
                Collections.swap(data, start, end);
                start++;
                end--;
            }
        }
        return calculateChecksum(data);
    }

    /**
     * Calculate Checksum for the data, data*position of data
     *
     * @param data the data to calculate the checksum
     * @return the checksum
     */
    private static long calculateChecksum(List<Integer> data) {
        long sum = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) != -1) {
                sum += data.get(i) * i;
            }
        }
        return sum;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<Integer> data = readFile(path);
        for (int endRight = data.size() - 1; endRight > 0; endRight--) {
            if (data.get(endRight) != -1) {
                //System.out.println(data.toString());
                int endLeft = endRight;
                while (endLeft >= 0 && data.get(endLeft) != -1 && data.get(endLeft).equals(data.get(endRight))) {
                    endLeft--;
                }

                int size = endRight - endLeft;

                int targetPosition = findTargetPosition(data, size, endLeft);
                if (targetPosition >= 0) {
                    for (int i = 0; i < size; i++) {
                        Collections.swap(data, targetPosition + i, endRight - size + i + 1);
                    }
                } else {
                    //noinspection AssignmentToForLoopParameter
                    endRight = endLeft + 1;
                }
            }
        }
        return calculateChecksum(data);
    }

    /**
     * Finds the target position in the data list where a sequence of a given size can fit.
     * The method scans the list up to the specified end position and looks for a sequence
     * of `-1` values that matches the target size.
     *
     * @param data       the list of integers to search within
     * @param targetSize the size of the sequence to find
     * @param endLeft    the end position to stop the search
     * @return the starting index of the target position if found, otherwise -1
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    private static int findTargetPosition(List<Integer> data, int targetSize, int endLeft) {
        int currentSize = 0;
        for (int i = 0; i < endLeft; i++) {
            if (currentSize >= targetSize) {
                return i - targetSize;
            }
            if (i < data.size() - 1 && data.get(i) == -1) {
                currentSize++;
            } else {
                currentSize = 0;
            }
        }
        return -1;
    }
}

