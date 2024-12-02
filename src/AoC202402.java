import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AoC202402 {

    private static ArrayList<ArrayList<Integer>> readFile(String path) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> inputData = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            List<String> tmpList = Arrays.stream(data.split("\\s+")).toList();
            ArrayList<Integer> tmpListInt = new ArrayList<>(tmpList.stream().map(Integer::parseInt).toList());
            inputData.add(tmpListInt);
        }
        scanner.close();
        return inputData;
    }

    public static int partOne(String path) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> inputData = readFile(path);

        int safeReports = 0;
        while (!inputData.isEmpty()) {
            List<Integer> currentSample = inputData.removeFirst();
            int lastNumber = currentSample.removeFirst();
            boolean isIncreasing = false;
            boolean isDecreasing = false;
            boolean isSafe = true;

            for (int number : currentSample) {
                int diff = Math.abs(number - lastNumber);
                if (diff < 1 || diff > 3) {
                    isSafe = false;
                    break;
                }
                if (number > lastNumber) {
                    isIncreasing = true;
                } else if (number < lastNumber) {
                    isDecreasing = true;
                }
                lastNumber = number;
            }

            if (isSafe && (isIncreasing ^ isDecreasing)) safeReports++;
        }

        return safeReports;
    }

    public static int partTwo(String path) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> inputData = readFile(path);

        int safeReports = 0;
        while (!inputData.isEmpty()) {
            List<Integer> currentSample = inputData.removeFirst();
            boolean safeVariant = false;
            for (int i = 0; i < currentSample.size(); i++) {
                ArrayList<Integer> currentCase = new ArrayList<>(currentSample);

                currentCase.remove(i);

                int lastNumber = currentCase.removeFirst();
                boolean isIncreasing = false;
                boolean isDecreasing = false;
                boolean isSafe = true;

                for (int number : currentCase) {
                    int diff = Math.abs(number - lastNumber);
                    if (diff < 1 || diff > 3) {
                        isSafe = false;
                        break;
                    }
                    if (number > lastNumber) {
                        isIncreasing = true;
                    } else if (number < lastNumber) {
                        isDecreasing = true;
                    }
                    lastNumber = number;
                }
                if (isSafe && (isIncreasing ^ isDecreasing)) safeVariant = true;
            }
            if (safeVariant) safeReports++;
        }
        return safeReports;
    }
}