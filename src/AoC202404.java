import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class AoC202404 {

    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        char[][] playField = new char[0][0];
        int currentLine = 0;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (playField.length == 0) {
                playField = new char[data.length()][data.length()];
            }
            for (int i = 0; i < data.length(); i++) {
                playField[currentLine][i] = data.charAt(i);
            }
            currentLine++;
        }
        scanner.close();
        return playField;
    }

    public static int partOne(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);
        int found = 0;

        //find horizontal matches
        for(int i = 0; i < playField.length; i++) {
            String thisLine = new String(playField[i]);
            found += StringUtils.countMatches(thisLine, "XMAS");
            found += StringUtils.countMatches(thisLine, "SAMX");
        }


        char lastChar = ' ';
        for (int i = 0; i < playField.length; i++) {
            for (int j = 0; j < playField[i].length; j++) {

            }
        }
        
        return found;
    }

    public static int partTwo(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);

        return 0;
    }
}