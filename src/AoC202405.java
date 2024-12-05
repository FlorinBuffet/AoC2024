import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AoC202405 {

    private static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        Map<Integer,Integer> orderingRules = new HashMap<>();
        List<List<Integer>> pageNumbers = new ArrayList<>();

        boolean emptyLineFound = false;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if ("".equals(data)) {
                emptyLineFound = true;
            }else if(!emptyLineFound){
                String[] split = data.split("\\|");
                orderingRules.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
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

    public static int partOne(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Map<Integer,Integer> orderingRules = (Map<Integer, Integer>) objects[0];
        List<List<Integer>> pageNumbers = (List<List<Integer>>) objects[1];
        int sum = 0;

        for (List<Integer> currentPageNumbers : pageNumbers) {
            boolean validResult = true;
            for (Map.Entry<Integer, Integer> entry : orderingRules.entrySet()) {
                if (currentPageNumbers.contains(entry.getKey()) && currentPageNumbers.contains(entry.getValue())) {
                    if (currentPageNumbers.indexOf(entry.getKey()) > currentPageNumbers.indexOf(entry.getValue())) {
                        validResult = false;
                    }
                }
            }
            if (validResult) {
                sum += currentPageNumbers.get((currentPageNumbers.size()-1)/2);
            }
        }
        
        return sum;
    }

    public static int partTwo(String path) throws FileNotFoundException {
        Object[] objects = readFile(path);
        Map<Integer,Integer> orderingRules = (Map<Integer, Integer>) objects[0];
        List<List<Integer>> pageNumbers = (List<List<Integer>>) objects[1];

        return 0;
    }
}