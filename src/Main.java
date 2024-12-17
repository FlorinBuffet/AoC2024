import java.lang.reflect.Method;

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
     */
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {

        // Please change the year and day to the desired challenge
        int year = 2024;
        int day = 6;

        String filePath = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
        //noinspection StringConcatenationMissingWhitespace
        String className = "AoC" + year + String.format("%02d", day);

        //noinspection OverlyBroadCatchBlock
        try {
            //noinspection StringConcatenationMissingWhitespace
            Class<?> usedClass = Class.forName("year" + year + "." + className);
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