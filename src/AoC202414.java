import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day 3.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202414 {

    public static final int ROUND_TO_PLAY = 100;
    public static final int FIELD_WIDTH = 101;
    public static final int FIELD_HEIGHT = 103;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202414() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Integers
     * @throws FileNotFoundException if the file is not found
     */
    private static List<int[]> readFile(String path) throws FileNotFoundException {
        List<int[]> sampleSet = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String regex = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)";
        Pattern pattern = Pattern.compile(regex);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Matcher matcher = pattern.matcher(data);
            int[] currentSample = new int[4];
            //noinspection ResultOfMethodCallIgnored
            matcher.find();
            currentSample[0] = Integer.parseInt(matcher.group(1));
            currentSample[1] = Integer.parseInt(matcher.group(2));
            currentSample[2] = Integer.parseInt(matcher.group(3));
            currentSample[3] = Integer.parseInt(matcher.group(4));
            sampleSet.add(currentSample);
        }
        scanner.close();
        return sampleSet;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<int[]> sampleSets = readFile(path);


        for (int i = 0; i < ROUND_TO_PLAY; i++) {
            for (int[] sample : sampleSets) {
                calculateNextPosition(sample);
            }
        }
        return calculateSafetyFactor(sampleSets);
    }

    /**
     * Updates the position of a sample based on its velocity and wraps around the field dimensions.
     *
     * @param sample an array representing the sample, where:
     *               sample[0] is the current x position,
     *               sample[1] is the current y position,
     *               sample[2] is the velocity in the x direction,
     *               sample[3] is the velocity in the y direction.
     */
    private static void calculateNextPosition(int[] sample) {
        sample[0] = (sample[0] + sample[2] + FIELD_WIDTH) % FIELD_WIDTH;
        sample[1] = (sample[1] + sample[3] + FIELD_HEIGHT) % FIELD_HEIGHT;
    }

    /**
     * Calculates the safety factor based on the distribution of samples in the grid.
     *
     * @param sampleSets a list of samples, where each sample is an array of integers
     *                   representing the x position, y position, x velocity, and y velocity.
     * @return the safety factor as an integer, calculated as the product of the number
     *         of samples in each of the four quadrants of the grid.
     */
    private static int calculateSafetyFactor(List<int[]> sampleSets) {
        int[] quadrants = new int[4];
        for (int[] sample : sampleSets) {
            if (sample[0] < FIELD_WIDTH / 2 && sample[1] < FIELD_HEIGHT / 2) {
                quadrants[0]++;
            } else if (sample[0] > FIELD_WIDTH / 2 && sample[1] < FIELD_HEIGHT / 2) {
                quadrants[1]++;
            } else if (sample[0] < FIELD_WIDTH / 2 && sample[1] > FIELD_HEIGHT / 2) {
                quadrants[2]++;
            } else if (sample[0] > FIELD_WIDTH / 2 && sample[1] > FIELD_HEIGHT / 2) {
                quadrants[3]++;
            }
        }
        return quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3];
    }

    /**
     * Calculates the result for the second part of the challenge. Hereby it assumes the resulting tree is aligned approximately in the center of the field as the safety factor is minimized.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        List<int[]> sampleSets = readFile(path);

        int minSafetyFactor = Integer.MAX_VALUE;
        int minSafetyFactorRound = 0;

        for (int i = 0; i < 10000; i++) {
            for (int[] sample : sampleSets) {
                calculateNextPosition(sample);
                int currentSafetyFactor = calculateSafetyFactor(sampleSets);
                if (currentSafetyFactor < minSafetyFactor) {
                    minSafetyFactor = currentSafetyFactor;
                    minSafetyFactorRound = i;
                }
            }
        }
        return minSafetyFactorRound + 1;
    }
}