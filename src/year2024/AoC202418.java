package year2024;

import utility.NodeGrid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 18.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202418 {

    private static final int NUMBER_OF_BLOCKERS_NEEDED = 1024;
    private static final int PLAY_FIELD_SIZE = 71;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202418() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<int[]> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<int[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int[] values = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                values[i] = Integer.parseInt(parts[i]);
            }
            data.add(values);
        }
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
    public static int partOne(String path) throws FileNotFoundException {
        List<int[]> blockers = readFile(path);

        NodeGrid nodeGrid = new NodeGrid(PLAY_FIELD_SIZE, true);

        for (int i = 0; i < NUMBER_OF_BLOCKERS_NEEDED; i++) {
            int[] blocker = blockers.get(i);
            nodeGrid.deleteNode(blocker[1], blocker[0]);
        }

        return nodeGrid.getDistanceFromStartToEnd();
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static String partTwo(String path) throws FileNotFoundException {
        List<int[]> blockers = readFile(path);

        NodeGrid nodeGrid = new NodeGrid(PLAY_FIELD_SIZE, true);

        int currentBlocker = 0;
        do {
            int[] blocker = blockers.get(currentBlocker);
            nodeGrid.deleteNode(blocker[1], blocker[0]);
            currentBlocker++;
        } while (nodeGrid.getDistanceFromStartToEnd() < Integer.MAX_VALUE);
        return blockers.get(currentBlocker - 1)[0] + "," + blockers.get(currentBlocker - 1)[1];
    }
}