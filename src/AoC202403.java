import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC202403 {

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

    public static int partOne(String path) throws FileNotFoundException {
        ArrayList<String> inputData = readFile(path);

        int result = 0;
        while (!inputData.isEmpty()) {
            String currentSample = inputData.removeFirst();
            if (currentSample.contains("mul")) {
                int number1 = Integer.parseInt(currentSample.substring(4, currentSample.indexOf(",")));
                int number2 = Integer.parseInt(currentSample.substring(currentSample.indexOf(",") + 1, currentSample.length() - 1));
                result += number1 * number2;
            }
        }
        return result;
    }

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
                int number1 = Integer.parseInt(currentSample.substring(4, currentSample.indexOf(",")));
                int number2 = Integer.parseInt(currentSample.substring(currentSample.indexOf(",") + 1, currentSample.length() - 1));
                result += number1 * number2;
            }
        }
        return result;
    }
}