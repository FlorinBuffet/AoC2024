package utility;

/**
 * This class implements some base conversion helper functions.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class BaseConverters {
    /**
     * Private constructor to prevent instantiation.
     */
    private BaseConverters() {
    }

    /**
     * Converts a decimal number to ternary.
     *
     * @param toConvert the number to convert
     * @return the ternary representation of the number as a string
     */
    public static String convertDecimalToTernary(int toConvert) {
        StringBuilder ternary = new StringBuilder();
        if (toConvert == 0) {
            ternary.insert(0, 0);
        }
        while (toConvert > 0) {
            ternary.insert(0, toConvert % 3);
            //noinspection AssignmentToMethodParameter
            toConvert /= 3;
        }
        return ternary.toString();
    }
}
