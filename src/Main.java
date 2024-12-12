import java.io.FileNotFoundException;
import java.lang.reflect.Method;

/**
 * This class solves AdventofCode 2024
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class Main {
    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args command line arguments
     * @throws FileNotFoundException if the specified file is not found
     */
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws FileNotFoundException {

        // Please change the year and day to the desired challenge
        int year = 2024;
        int day = 12;

        String filePath = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
        //noinspection StringConcatenationMissingWhitespace
        String className = "AoC" + year + String.format("%02d", day);

        //noinspection OverlyBroadCatchBlock
        try {
            Class<?> usedClass = Class.forName(className);
            Method partOneMethod = usedClass.getMethod("partOne", String.class);
            Method partTwoMethod = usedClass.getMethod("partTwo", String.class);

            System.out.println("2024 Day " + day + ", Part 1: " + partOneMethod.invoke(null, filePath));
            System.out.println("2024 Day " + day + ", Part 2: " + partTwoMethod.invoke(null, filePath));
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}