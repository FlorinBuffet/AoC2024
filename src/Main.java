import utility.AoCDownloader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * This class solves AdventofCode 2024
 *
 * @author Florin Buffet
 * @version V1.2
 */
public class Main {
    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args command line arguments
     * @throws IOException if the file is not found
     * @throws URISyntaxException if the URI is invalid
     */
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws IOException, URISyntaxException {

        // Please change the year and day to the desired challenge
        int year = 2024;
        int day = 19;

        if (AoCDownloader.doesFileExistOrIsDownloaded(year, day, true)) {
            System.out.println("Calculating the result for the challenge...");
            String filePath = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
            //noinspection StringConcatenationMissingWhitespace
            String className = "AoC" + year + String.format("%02d", day);

            //noinspection OverlyBroadCatchBlock
            try {
                //noinspection StringConcatenationMissingWhitespace
                Class<?> usedClass = Class.forName("year" + year + "." + className);
                Method partOneMethod = usedClass.getMethod("partOne", String.class);
                Method partTwoMethod = usedClass.getMethod("partTwo", String.class);

                System.out.println(year + " Day " + day + ", Part 1: " + partOneMethod.invoke(null, filePath));
                System.out.println(year + " Day " + day + ", Part 2: " + partTwoMethod.invoke(null, filePath));
            } catch (Exception e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        } else {
            System.out.println("The software exits now.");
        }
    }
}