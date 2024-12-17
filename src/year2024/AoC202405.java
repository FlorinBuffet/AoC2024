package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day 5.
 *
 * @author Florin Buffet
 * @version V1.1
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
     * @param path the path to the input file
     * @return the data as a list of lists
     * @throws FileNotFoundException if the file is not found
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
            } else if (emptyLineNotFound) {
                String[] split = data.split("\\|");
                int[] tmpArray = new int[2];
                tmpArray[0] = Integer.parseInt(split[0]);
                tmpArray[1] = Integer.parseInt(split[1]);
                orderingRules.add(tmpArray);
            } else {
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
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Iterable<int[]> orderingRules = (Iterable<int[]>) objects[0];
        List<List<Integer>> pageNumbers = (List<List<Integer>>) objects[1];

        return sumOfOrderedPages(pageNumbers, orderingRules);
    }

    /**
     * Sums the values of the middle elements of the ordered pages and removes them from the list.
     *
     * @param pageNumbers   the list of lists of page numbers
     * @param orderingRules the iterable collection of ordering rules
     * @return the sum of the middle elements of the ordered pages
     */
    private static int sumOfOrderedPages(List<? extends List<Integer>> pageNumbers, Iterable<int[]> orderingRules) {
        int sum = 0;
        for (int current = 0; current < pageNumbers.size(); current++) {
            if (arePagesOrdered(pageNumbers.get(current), orderingRules)) {
                sum += pageNumbers.get(current).get((pageNumbers.get(current).size() - 1) / 2);
                pageNumbers.remove(current); //remove the current file, if it is ordered
                //noinspection AssignmentToForLoopParameter
                current--; //decrement the current index to avoid skipping the next file
            }
        }
        return sum;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Iterable<int[]> orderingRules = (Iterable<int[]>) objects[0];
        List<List<Integer>> pageNumbers = (List<List<Integer>>) objects[1];

        //remove the ordered pages from the list as they are not relevant for the second part
        sumOfOrderedPages(pageNumbers, orderingRules);

        //sort the remaining pages according to the rules
        for (List<Integer> currentPageNumbers : pageNumbers) {
            //noinspection OverlyLongLambda
            currentPageNumbers.sort((o1, o2) -> {
                for (int[] rule : orderingRules) {
                    if (rule[0] == o1 && rule[1] == o2) {
                        return -1;
                    } else if (rule[0] == o2 && rule[1] == o1) {
                        return 1;
                    }
                }
                return 0;
            });
        }

        return sumOfOrderedPages(pageNumbers, orderingRules);
    }

    /**
     * Checks if the given pages follow the rules.
     *
     * @param pageNumbers   The list of page numbers in the update.
     * @param orderingRules The list of rules represented as int arrays where rules[i][0] must be before rules[i][1].
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
}

