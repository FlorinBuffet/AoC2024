package utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * This class automatically downloads the input for the Advent of Code challenges.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoCDownloader {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoCDownloader() {
    }

    /**
     * Downloads the input for the specified day and year.
     *
     * @param day  the day of the challenge
     * @param sessionToken the session token from the website
     * @param year the year of the challenge
     * @return the input as a string
     */
    public static String downloadInput(int year, int day, String sessionToken) {
        String urlString = "https://adventofcode.com/" + year + "/day/" + day + "/input";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", "session=" + sessionToken);
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if there ia already an input file in the data directory for the specified day and year.
     *
     * @param day  the day of the challenge
     * @param year the year of the challenge
     * @return the input as a string
     */
    @SuppressWarnings("StringConcatenationMissingWhitespace")
    public static boolean doesInputExist(int year, int day) {
        String fileName = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
        return Files.exists(Path.of(fileName));
    }

    public static boolean downloadInputToFile(int year, int day, String sessionToken) {
        if (!doesInputExist(year, day)) {
            String input = downloadInput(year, day, sessionToken);
            if (input != null) {
                String fileName = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
                try {
                    Files.writeString(Path.of(fileName), input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return doesInputExist(year, day);
    }
}
