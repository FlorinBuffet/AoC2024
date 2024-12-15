/**
 * This class is a helper for AdventofCode 2024, Day 12, Part 2.
 *
 * @author Florin Buffet
 * @version V1.3
 */

//TODO: cleanup class

public class AoC202412_Bot {
    private int currentX;
    private int currentY;
    private int directionX;
    private int directionY;
    private char[][] playField;
    private int originX;
    private int originY;

    /**
     * Constructor for the bot.
     *
     * @param playField the playField as a 2D char array
     * @param currentX  the starting x coordinate
     * @param currentY  the starting y coordinate
     */
    public AoC202412_Bot(char[][] playField, int currentX, int currentY) {
        this.playField = playField;
        this.currentX = currentX;
        this.currentY = currentY;
        this.originX = currentX;
        this.originY = currentY;
        this.directionX = 0;
        this.directionY = 1;
    }

    public int countStraights() {
        int turns = 1;
        do {
            printField();
            if (canTurnLeft()) {
                turnLeft();
                moveForward();
                turns++;
            } else if (canMoveForward()) {
                moveForward();
            } else {
                turnRight();
                turns++;
            }
        } while (!isAtOriginLookingRight());
        return turns;
    }

    private void printField() {
        for (int i = 0; i < playField.length; i++) {
            for (int j = 0; j < playField[i].length; j++) {
                if (i == currentX && j == currentY) {
                    if (directionY == 1) {
                        System.out.print(">");
                    } else if (directionY == -1) {
                        System.out.print("<");
                    } else if (directionX == 1) {
                        System.out.print("v");
                    } else {
                        System.out.print("^");
                    }
                } else {
                    System.out.print(playField[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Checks if the bot is at its origin position looking up.
     *
     * @return true if the bot is at the origin position, false otherwise
     */
    private boolean isAtOriginLookingRight() {
        return currentX == originX && currentY == originY && directionX == -1 && directionY == 0;
    }

    /**
     * Turns the bot 90 degrees to the left.
     * Updates the directionX and directionY based on the current direction.
     */
    private void turnLeft() {
        int tmp = directionX;
        if (directionX == 1) {
            directionX = 0;
            directionY = 1;
        } else if (directionX == -1) {
            directionX = 0;
            directionY = -1;
        } else if (directionY == 1) {
            directionX = -1;
            directionY = 0;
        } else {
            directionX = 1;
            directionY = 0;
        }
    }

    /**
     * Turns the bot 90 degrees to the right.
     * Updates the directionX and directionY based on the current direction.
     */
    private void turnRight() {
        int tmp = directionX;
        if (directionX == 1) {
            directionX = 0;
            directionY = -1;
        } else if (directionX == -1) {
            directionX = 0;
            directionY = 1;
        } else if (directionY == 1) {
            directionX = 1;
            directionY = 0;
        } else {
            directionX = -1;
            directionY = 0;
        }
    }

    /**
     * Moves the bot forward in the current direction.
     * Updates the currentX and currentY coordinates based on the directionX and directionY.
     */
    private void moveForward() {
        currentX += directionX;
        currentY += directionY;
    }

    /**
     * Checks if the bot can turn left and move forward.
     * Turns the bot left, checks if it can move forward, then turns the bot back to its original direction.
     *
     * @return true if the bot can move forward after turning left, false otherwise
     */
    private boolean canTurnLeft() {
        turnLeft();
        boolean returnValue = canMoveForward();
        turnRight();
        return returnValue;
    }

    /**
     * Checks if the bot can turn right and move forward.
     * Turns the bot right, checks if it can move forward, then turns the bot back to its original direction.
     *
     * @return true if the bot can move forward after turning right, false otherwise
     */
    private boolean canTurnRight() {
        turnRight();
        boolean returnValue = canMoveForward();
        turnLeft();
        return returnValue;
    }

    /**
     * Checks if the bot can move forward in the current direction.
     * Determines if the next field in the playField is the same as the current field.
     *
     * @return true if the bot can move forward, false otherwise
     */
    private boolean canMoveForward() {
        boolean returnValue;
        char currentField = playField[currentX][currentY];
        //noinspection ProhibitedExceptionCaught
        try {
            char nextField = playField[currentX + directionX][currentY + directionY];
            returnValue = currentField == nextField;
        } catch (ArrayIndexOutOfBoundsException e) {
            returnValue = false;
        }
        return returnValue;
    }
}