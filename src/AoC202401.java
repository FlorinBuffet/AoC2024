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
 * @version V1.1
 */
public class AoC202401 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202401() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of lists
     * @throws FileNotFoundException
     */
    private static List[] readFile(String path) throws FileNotFoundException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            left.add(Integer.parseInt(data.substring(0, data.indexOf(' '))));
            right.add(Integer.parseInt(data.substring(data.indexOf(' ') + 1).trim()));
        }
        scanner.close();
        return new List[]{left, right};
    }

    /**
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<Integer>[] lists = readFile(path);
        List<Integer> left = lists[0];
        List<Integer> right = lists[1];
        Collections.sort(left);
        Collections.sort(right);

        int returnVal = 0;
        for (int i = 0; i < left.size(); i++) {
            returnVal += Math.abs(left.get(i) - right.get(i));
        }
        return returnVal;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<Integer>[] lists = readFile(path);
        List<Integer> left = lists[0];
        List<Integer> right = lists[1];

        long returnVal = 0;
        for (int num : left) {
            returnVal += (long) Collections.frequency(right, num) * num;
        }
        return returnVal;
    }
}