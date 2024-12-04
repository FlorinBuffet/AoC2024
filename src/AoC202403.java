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
 * @version V1.1
 */
public class AoC202403 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202403() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of strings
     * @throws FileNotFoundException
     */
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
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException
     */
    public static int partOne(String path) throws FileNotFoundException {
        ArrayList<String> inputData = readFile(path);

        int result = 0;
        while (!inputData.isEmpty()) {
            String currentSample = inputData.removeFirst();
            if (currentSample.contains("mul")) {
                int number1 = Integer.parseInt(currentSample.substring(4, currentSample.indexOf(',')));
                int number2 = Integer.parseInt(currentSample.substring(currentSample.indexOf(',') + 1, currentSample.length() - 1));
                result += number1 * number2;
            }
        }
        return result;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException
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
                int number1 = Integer.parseInt(currentSample.substring(4, currentSample.indexOf(',')));
                int number2 = Integer.parseInt(currentSample.substring(currentSample.indexOf(',') + 1, currentSample.length() - 1));
                result += number1 * number2;
            }
        }
        return result;
    }
}