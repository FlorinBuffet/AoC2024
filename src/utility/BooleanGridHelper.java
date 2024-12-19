package utility;

/**
 * This class implements some helper functions for boolean grids.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class BooleanGridHelper {

    /**
     * Private constructor to prevent instantiation.
     */
    private BooleanGridHelper() {
    }

    /**
     * Counts the number of true values in a 2D boolean array (grid).
     *
     * @param grid the 2D boolean array to count true values in
     * @return the number of true values in the grid
     */
    public static int countTrue(boolean[][] grid) {
        int count = 0;
        for (boolean[] booleans : grid) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Sets the value in the grid at the specified coordinates if they are valid.
     * The coordinates are considered valid if they are within the bounds of the grid.
     *
     * @param grid the 2D boolean array to modify
     * @param x the x-coordinate to set the value at
     * @param y the y-coordinate to set the value at
     * @param value the boolean value to set at the specified coordinates
     */
    @SuppressWarnings("OverlyComplexBooleanExpression")
    public static void setIfValid(boolean[][] grid, int x, int y, boolean value) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = value;
        }
    }
}