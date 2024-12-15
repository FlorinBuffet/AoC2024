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
public class AoC202413 {

    public static final int BUTTON_A_COST = 3;
    public static final int BUTTON_B_COST = 1;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202413() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Integers
     * @throws FileNotFoundException if the file is not found
     */
    private static List<Integer> readFile(String path) throws FileNotFoundException {
        List<Integer> sampleSets = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String regex = "[XY]\\+(\\d+)|[XY]=(\\d+)";
        Pattern pattern = Pattern.compile(regex);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Matcher matcher = pattern.matcher(data);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    sampleSets.add(Integer.parseInt(matcher.group(1)));
                } else {
                    sampleSets.add(Integer.parseInt(matcher.group(2)));
                }
            }
        }
        scanner.close();
        return sampleSets;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<Integer> lists = readFile(path);

        int sum = 0;

        while (lists.size() > 1) {
            int buttonAX = lists.removeFirst();
            int buttonAY = lists.removeFirst();
            int buttonBX = lists.removeFirst();
            int buttonBY = lists.removeFirst();
            int targetX = lists.removeFirst();
            int targetY = lists.removeFirst();

            double[] solution = solveSystem(buttonAX, buttonBX, targetX, buttonAY, buttonBY, targetY);
            if (solution[0] == Math.floor(solution[0]) && solution[1] == Math.floor(solution[1])) {
                sum += BUTTON_A_COST * (int) solution[0] + BUTTON_B_COST * (int) solution[1];
            }
        }
        return sum;
    }

    /**
     * Solves a system of linear equations of the form:
     * a1 * x + b1 * y = c1
     * a2 * x + b2 * y = c2
     *
     * @param a1 coefficient of x in the first equation
     * @param b1 coefficient of y in the first equation
     * @param c1 constant term in the first equation
     * @param a2 coefficient of x in the second equation
     * @param b2 coefficient of y in the second equation
     * @param c2 constant term in the second equation
     * @return an array containing the values of x and y that solve the system of equations
     */
    private static double[] solveSystem(int a1, int b1, long c1, int a2, int b2, long c2) {
        double y = (double) (c2 * a1 - c1 * a2) / (a1 * b2 - a2 * b1);
        double x = (c1 - b1 * y) / a1;
        return new double[]{x, y};
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<Integer> lists = readFile(path);

        long sum = 0;

        while (lists.size() > 1) {
            int buttonAX = lists.removeFirst();
            int buttonAY = lists.removeFirst();
            int buttonBX = lists.removeFirst();
            int buttonBY = lists.removeFirst();
            int targetX = lists.removeFirst();
            int targetY = lists.removeFirst();

            double[] solution = solveSystem(buttonAX, buttonBX, targetX + 10000000000000L, buttonAY, buttonBY, targetY + 10000000000000L);
            if (solution[0] == Math.floor(solution[0]) && solution[1] == Math.floor(solution[1])) {
                sum += BUTTON_A_COST * (long) solution[0] + BUTTON_B_COST * (long) solution[1];
            }
        }
        return sum;
    }
}