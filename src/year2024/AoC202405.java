package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

//TODO: cleanup class
//TODO: implement part 2

/**
 * This class solves AdventofCode 2024, Day 5.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202405 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202405() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path path to the input file
     * @return the data as a list of chars
     * @throws FileNotFoundException
     */
    private static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        Collection<int[]> orderingRules = new ArrayList<>();
        Collection<List<Integer>> pageNumbers = new ArrayList<>();

        boolean emptyLineNotFound = true;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if ("".equals(data)) {
                emptyLineNotFound = false;
            }else if(emptyLineNotFound){
                String[] split = data.split("\\|");
                int[] tmpArray = new int[2];
                tmpArray[0] = Integer.parseInt(split[0]);
                tmpArray[1] = Integer.parseInt(split[1]);
                orderingRules.add(tmpArray);
            }else{
                String[] split = data.split(",");
                List<Integer> currentPageNumbers = new ArrayList<>();
                for (String s : split) {
                    currentPageNumbers.add(Integer.parseInt(s));
                }
                pageNumbers.add(currentPageNumbers);
            }
        }
        scanner.close();
        Object[] objects = new Object[2];
        objects[0] = orderingRules;
        objects[1] = pageNumbers;
        return objects;
    }

    /**
     * @param path path to the input file
     * @return the result for the first part of the challenge
     * @throws FileNotFoundException
     */
    public static int partOne(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Iterable<int[]> orderingRules = (Iterable<int[]>) objects[0];
        Iterable<List<Integer>> pageNumbers = (Iterable<List<Integer>>) objects[1];
        int sum = 0;

        for (List<Integer> currentPageNumbers : pageNumbers) {
            if (arePagesOrdered(currentPageNumbers, orderingRules)) {
                sum += currentPageNumbers.get((currentPageNumbers.size()-1)/2);
            }
        }
        
        return sum;
    }

    /**
     * @param path path to the input file
     * @return the result for the second part of the challenge
     * @throws FileNotFoundException
     */
    public static int partTwo(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Iterable<int[]> orderingRules = (Iterable<int[]>) objects[0];
        Iterable<List<Integer>> pageNumbers = (Iterable<List<Integer>>) objects[1];

        for (List<Integer> currentPageNumbers : pageNumbers) {

        }

        return 0;
    }

    /**
     * Checks if the given pages follow the rules.
     *
     * @param pageNumbers The list of page numbers in the update.
     * @param orderingRules  The list of rules represented as int arrays where rules[i][0] must be before rules[i][1].
     * @return True if the update is correctly ordered according to the rules, false otherwise.
     */
    private static boolean arePagesOrdered(List<Integer> pageNumbers, Iterable<int[]> orderingRules) {
        boolean ordered = true;
        for (int[] rule : orderingRules) {

            //skips rules that contain pages that are no in the current update
            if (pageNumbers.contains(rule[0]) && pageNumbers.contains(rule[1])) {

                //if the first page is after the second page, the update is not ordered
                if (pageNumbers.indexOf(rule[0]) > pageNumbers.indexOf(rule[1])) {
                    ordered = false;
                }
            }
        }
        return ordered;
    }

    /**
     * Sorts the given pages according to the rules.
     *
     * @param pageNumbers The list of page numbers in the update.
     * @param orderingRules  The list of rules represented as int arrays where rules[i][0] must be before rules[i][1].
     * @return True if the update is correctly ordered according to the rules, false otherwise.
     */
    private static boolean sortPages(List<Integer> pageNumbers, Iterable<int[]> orderingRules) {

        return true;
    }
}

