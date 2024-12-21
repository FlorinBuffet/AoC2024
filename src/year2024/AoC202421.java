package year2024;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202421 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202421() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<List<Character>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<Character>> list = InputParser.parseCharListPerLine(scanner);
        scanner.close();
        return list;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        List<List<Character>> input = readFile(path);
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            List<Character> list = input.get(i);
            List<Character> keyPad1 = getCharOfNumbers(list);
            List<Character> keyPad2 = getCharOfDirections(keyPad1);
            List<Character> keyPad3 = getCharOfDirections(keyPad2);
            int keypadNumbers = Integer.parseInt("" + list.get(0) + list.get(1) + list.get(2));
            System.out.println(keypadNumbers);
            sum += keyPad3.size() * keypadNumbers;
        }
        return sum;
    }

    public static List<Character> getCharOfNumbers(List<Character> list) {
        char[][] keypadNumbers = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
                {'X', '0', 'A'}
        };

        int x = 3;
        int y = 2;

        List<Character> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            int[] charPos = findChar(keypadNumbers, c);
            int targetX = charPos[0];
            int targetY = charPos[1];

            if (x == 3){
                while(x > targetX){
                    result.add('^');
                    x--;
                }
                while(y > targetY){
                    result.add('<');
                    y--;
                }
                while(y < targetY){
                    result.add('>');
                    y++;
                }
            }else{
                while(y > targetY){
                    result.add('<');
                    y--;
                }
                while(y < targetY){
                    result.add('>');
                    y++;
                }
                while(x > targetX){
                    result.add('^');
                    x--;
                }
                while(x < targetX){
                    result.add('v');
                    x++;
                }
            }
            result.add('A');
        }


        return result;
    }

    public static List<Character> getCharOfDirections(List<Character> list) {
        char[][] keypadNumbers = {
                {'X', '^', 'A'},
                {'<', 'v', '>'},
        };

        int x = 0;
        int y = 2;

        List<Character> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            int[] charPos = findChar(keypadNumbers, c);
            int targetX = charPos[0];
            int targetY = charPos[1];

            if (x == 0){
                while(x < targetX){
                    result.add('v');
                    x++;
                }
                while(y > targetY){
                    result.add('<');
                    y--;
                }
                while(y < targetY){
                    result.add('>');
                    y++;
                }
            }else{
                while(y > targetY){
                    result.add('<');
                    y--;
                }
                while(y < targetY){
                    result.add('>');
                    y++;
                }
                while(x > targetX){
                    result.add('^');
                    x--;
                }
            }
            result.add('A');
        }


        return result;
    }

    public static int[] findChar(char[][] grid, char c) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        return 0;
    }
}