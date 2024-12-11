import java.io.FileNotFoundException;

/**
 * This class solves AdventofCode 2024
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class Main {
    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args
     * @throws FileNotFoundException
     */
    @SuppressWarnings("DuplicateStringLiteralInspection")
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("2024 Day 11, Part 1: " + AoC202411.partOne("data/AoC_2024_11.txt"));
        System.out.println("2024 Day 11, Part 2: " + AoC202411.partTwo("data/AoC_2024_11.txt"));
    }
}