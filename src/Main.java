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
        System.out.println("2024 Day 09, Part 1: "+AoC202409.partOne("data/AoC_2024_09.txt"));
        System.out.println("2024 Day 09, Part 2: "+AoC202409.partTwo("data/AoC_2024_09.txt"));
    }
}