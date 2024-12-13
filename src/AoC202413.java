import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        int sum = 0 ;

        while (lists.size() > 1) {
            int buttonAX = lists.removeFirst();
            int buttonAY = lists.removeFirst();
            int buttonBX = lists.removeFirst();
            int buttonBY = lists.removeFirst();
            int targetX = lists.removeFirst();
            int targetY = lists.removeFirst();

            int[][][] dp = new int[targetX+1][targetY+1][3];
            dp[0][0][0] = 1;
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;
            for (int x = Math.min(buttonAX, buttonBX); x <= targetX; x++) {
                for (int y = Math.min(buttonAY, buttonBY); y <= targetY; y++) {
                    int buttonApreviousCost = Integer.MAX_VALUE - BUTTON_A_COST;
                    int buttonBpreviousCost = Integer.MAX_VALUE - BUTTON_B_COST;

                    if (x - buttonAX >= 0 && y - buttonAY >= 0 && dp[x - buttonAX][y - buttonAY][0] == 1) {
                        buttonApreviousCost = dp[x - buttonAX][y - buttonAY][1] * BUTTON_A_COST + dp[x - buttonAX][y - buttonAY][2] * BUTTON_B_COST;
                    }
                    if (x - buttonBX >= 0 && y - buttonBY >= 0 && dp[x - buttonBX][y - buttonBY][0] == 1) {
                        buttonBpreviousCost = dp[x - buttonBX][y - buttonBY][1] * BUTTON_A_COST + dp[x - buttonBX][y - buttonBY][2] * BUTTON_B_COST;
                    }
                    if (buttonApreviousCost == Integer.MAX_VALUE - BUTTON_A_COST && buttonBpreviousCost == Integer.MAX_VALUE - BUTTON_B_COST) {
                        dp[x][y][0] = 0;
                    } else if (buttonApreviousCost + BUTTON_A_COST <= buttonBpreviousCost + BUTTON_B_COST) {
                        dp[x][y][0] = 1;
                        dp[x][y][1] = dp[x - buttonAX][y - buttonAY][1] + 1;
                        dp[x][y][2] = dp[x - buttonAX][y - buttonAY][2];
                    } else {
                        dp[x][y][0] = 1;
                        dp[x][y][1] = dp[x - buttonBX][y - buttonBY][1];
                        dp[x][y][2] = dp[x - buttonBX][y - buttonBY][2] + 1;
                    }
                }
            }
            System.out.println(dp[targetX][targetY][0]);
            if (dp[targetX][targetY][0] == 1){
                sum += dp[targetX][targetY][1]*BUTTON_A_COST + dp[targetX][targetY][2]*BUTTON_B_COST;
            }
        }

        return sum;
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
        return 0;
    }
}