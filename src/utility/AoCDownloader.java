package utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class automatically downloads the input for the Advent of Code challenges.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class AoCDownloader {

    private static final String PROPERTY_SESSION_TOKEN = "SESSION_TOKEN";
    private static final String PROPERTY_ALLOW_DOWNLOADING = "ALLOW_DOWNLOADING_OF_INPUT_FILES";
    private static final int HTTP_STATUS_OK = 200;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoCDownloader() {
    }

    /**
     * Downloads the input for the specified day and year.
     *
     * @param day          the day of the challenge
     * @param sessionToken the session token from the website
     * @param year         the year of the challenge
     * @return the input as a string
     */
    @SuppressWarnings({"OverlyBroadThrowsClause", "MethodWithMultipleReturnPoints"})
    private static String getInputFromAPI(int year, int day, String sessionToken) throws URISyntaxException, IOException {
        String urlString = "https://adventofcode.com/" + year + "/day/" + day + "/input";
        URL url = new URI(urlString).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Cookie", "session=" + sessionToken);
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HTTP_STATUS_OK) {
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            return response.toString();
        } else {
            System.out.println("Error: " + connection.getResponseCode());
            return connection.getResponseMessage();
        }
    }

    /**
     * Checks if there ia already an input file in the data directory for the specified day and year.
     *
     * @param day  the day of the challenge
     * @param year the year of the challenge
     * @return the input as a string
     */
    private static boolean doesInputExist(int year, int day) {
        String fileName = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
        return Files.exists(Path.of(fileName));
    }

    /**
     * Downloads the input for the specified day and year to a file.
     * If the input is successfully retrieved from the API, it writes the input to a file in the data directory.
     *
     * @param year         the year of the challenge
     * @param day          the day of the challenge
     * @param sessionToken the session token from the website
     * @return true if the input file exists after the download attempt, false otherwise
     * @throws IOException if an I/O error occurs during file writing
     */
    @SuppressWarnings({"BooleanMethodNameMustStartWithQuestion", "MethodWithMultipleReturnPoints"})
    private static boolean downloadInputToFile(int year, int day, String sessionToken) throws IOException, URISyntaxException {
        String input = getInputFromAPI(year, day, sessionToken);
        if ("Internal Server Error".equals(input)) {
            System.out.println("Please check the session token in the AoC.properties file.");
            return false;
        } else {
            String fileName = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
            Files.writeString(Path.of(fileName), input);
            return doesInputExist(year, day);
        }
    }

    /**
     * Checks if the input file for the specified day and year exists or downloads it if allowed.
     * If the input file does not exist, it attempts to download it using the session token from the properties file.
     *
     * @param year          the year of the challenge
     * @param day           the day of the challenge
     * @param printMessages whether to print messages during the process
     * @return true if the input file exists or is successfully downloaded, false otherwise
     * @throws IOException        if an I/O error occurs
     * @throws URISyntaxException if the URI is invalid
     */
    public static boolean doesFileExistOrIsDownloaded(int year, int day, boolean printMessages) throws IOException, URISyntaxException {
        boolean returnBoolean;
        if (doesInputExist(year, day))
            returnBoolean = true;
        else {
            if (printMessages)
                System.out.println("Downloading input file for day " + day + " of year " + year);

            // Load the properties file
            Properties prop = new Properties();
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AoC.properties");
            prop.load(stream);

            if (!"true".equals(prop.getProperty(PROPERTY_ALLOW_DOWNLOADING))) {
                System.out.println("Please provide the input file in the data folder or allow downloading in the AoC.properties file.");
                returnBoolean = false;
            } else if (prop.getProperty(PROPERTY_SESSION_TOKEN) == null || prop.getProperty(PROPERTY_SESSION_TOKEN).isEmpty()) {
                System.out.println("Please provide a session token in the AoC.properties file.");
                returnBoolean = false;
            } else {
                String sessionToken = prop.getProperty(PROPERTY_SESSION_TOKEN);
                returnBoolean = downloadInputToFile(year, day, sessionToken);
            }

            if (printMessages && returnBoolean) {
                System.out.println("The input file was successfully downloaded.");
            }
        }
        return returnBoolean;
    }
}
