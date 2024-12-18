import utility.AoCDownloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * This class solves AdventofCode 2024
 *
 * @author Florin Buffet
 * @version V1.2
 */
public class Main {
    private static final String PROPERTY_SESSION_TOKEN = "SESSION_TOKEN";

    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args command line arguments
     */
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws IOException {

        // Please change the year and day to the desired challenge
        int year = 2024;
        int day = 4;
        String sessionToken = "";

        if (!AoCDownloader.doesInputExist(year, day)) {
            System.out.println("Downloading input file for day " + day + " of year " + year);
            Properties prop = new Properties();
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AoC.properties");
            prop.load(stream);
            if ("true".equals(prop.getProperty("ALLOW_DOWNLOADING_OF_INPUT_FILES"))) {
                if (prop.getProperty(PROPERTY_SESSION_TOKEN) == null || prop.getProperty(PROPERTY_SESSION_TOKEN).isEmpty()) {
                    System.out.println("Please provide a session token in the AoC.properties file.");
                    System.exit(1);
                } else {
                    sessionToken = prop.getProperty(PROPERTY_SESSION_TOKEN);
                    AoCDownloader.downloadInputToFile(year, day, sessionToken);
                }
            } else {
                System.out.println("Please provide the input file in the data folder or allow downloading in the AoC.properties file.");
                System.exit(1);
            }
        }

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