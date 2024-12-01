import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AoC202401 {

    private static List<Integer>[] readFile(String path) throws FileNotFoundException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            left.add(Integer.parseInt(data.substring(0, data.indexOf(" "))));
            right.add(Integer.parseInt(data.substring(data.indexOf(" ") + 1).trim()));
        }
        scanner.close();
        return new List[]{left, right};
    }

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