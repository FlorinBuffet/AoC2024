package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class solves AdventofCode 2024, Day 3.
 *
 * @author Florin Buffet
 * @version V1.2
 */
public class AoC202403 {

    private static final char MUL_SPLIT_CHAR = ',';

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202403() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Strings
     * @throws FileNotFoundException if the file is not found
     */
    @SuppressWarnings({"RegExpAnonymousGroup", "ObjectAllocationInLoop"})
    private static ArrayList<String> readFile(String path) throws FileNotFoundException {
        ArrayList<String> inputDataOne = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String regex = "(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))";
        Pattern pattern = Pattern.compile(regex);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Matcher matcher = pattern.matcher(data);
            while (matcher.find()) {
                inputDataOne.add(matcher.group());
            }
        }
        scanner.close();
        return inputDataOne;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        ArrayList<String> inputData = readFile(path);

        int result = 0;
        while (!inputData.isEmpty()) {
            String currentSample = inputData.removeFirst();
            if (currentSample.contains("mul")) {
                result += calculateMul(currentSample);
            }
        }
        return result;
    }

    /**
     * Calculates the product of two numbers extracted from the given string.
     * The string is expected to be in the format "mul(number1,number2)".
     *
     * @param currentSample the string containing the numbers to be multiplied
     * @return the product of the two numbers
     */
    private static int calculateMul(String currentSample) {
        String number1String = currentSample.substring(4, currentSample.indexOf(MUL_SPLIT_CHAR));
        int number1 = Integer.parseInt(number1String);
        String number2String = currentSample.substring(currentSample.indexOf(MUL_SPLIT_CHAR) + 1, currentSample.length() - 1);
        int number2 = Integer.parseInt(number2String);
        return number1 * number2;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        ArrayList<String> inputData = readFile(path);

        boolean isDo = true;
        int result = 0;
        while (!inputData.isEmpty()) {
            String currentSample = inputData.removeFirst();
            if ("do()".equals(currentSample)) {
                isDo = true;
            } else if ("don't()".equals(currentSample)) {
                isDo = false;
            } else if (currentSample.contains("mul") && isDo) {
                result += calculateMul(currentSample);
            }
        }
        return result;
    }
}