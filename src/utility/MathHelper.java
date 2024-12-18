package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements some math helper functions.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class MathHelper {

    /**
     * Private constructor to prevent instantiation.
     */
    private MathHelper() {
    }

    /**
     * Generates all binary permutations of a given length.
     *
     * @param length the length of the binary permutations
     * @return a list of binary strings representing all permutations
     */
    public static List<String> createAllBinaryPermutations(int length) {
        // creates an empty list of strings
        List<String> binaryStrings = new ArrayList<>();

        // for each number from 0 to 2^length
        for (int i = 0; i < StrictMath.pow(2, length); i++) {
            // convert the number to binary
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
            while (binary.length() < length) {
                binary.insert(0, "0");
            }

            // add the binary string to the list
            binaryStrings.add(binary.toString());
        }

        // return the list of binary strings
        return binaryStrings;
    }

    /**
     * Generates all ternary permutations of a given length.
     *
     * @param length the length of the binary permutations
     * @return a list of ternary strings representing all permutations
     */
    public static List<String> createAllTernaryPermutations(int length) {
        // creates an empty list of strings
        List<String> ternaryStrings = new ArrayList<>();

        // for each number from 0 to 2^length
        for (int i = 0; i < StrictMath.pow(3, length); i++) {
            // convert the number to binary
            StringBuilder ternary = new StringBuilder(BaseConverters.convertDecimalToTernary(i));
            while (ternary.length() < length) {
                ternary.insert(0, "0");
            }

            // add the binary string to the list
            ternaryStrings.add(ternary.toString());
        }

        // return the list of binary strings
        return ternaryStrings;
    }
}