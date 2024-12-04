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

        //find vertical matches
        for(int j = 0; j<playField.length; j++){
            String thisLine = "";
            for(int i = 0; i<playField.length; i++){
                thisLine += playField[i][j];
            }
            found += StringUtils.countMatches(thisLine, "XMAS");
            found += StringUtils.countMatches(thisLine, "SAMX");
        }

        for(int i = 3; i<playField.length; i++){
            for(int j = 3; j<playField.length;j++){
                if (playField[i-3][j-3]=='X' && playField[i-2][j-2]=='M' && playField[i-1][j-1]=='A'&&playField[i][j]=='S'){
                    found++;
                }else if (playField[i-3][j-3]=='S' && playField[i-2][j-2]=='A' && playField[i-1][j-1]=='M'&&playField[i][j]=='X'){
                    found++;
                }
            }
            for (int j = playField.length-4; j>=0; j--){
                if (playField[i-3][j+3]=='X' && playField[i-2][j+2]=='M' && playField[i-1][j+1]=='A'&&playField[i][j]=='S'){
                    found++;
                }else if (playField[i-3][j+3]=='S' && playField[i-2][j+2]=='A' && playField[i-1][j+1]=='M'&&playField[i][j]=='X'){
                    found++;
                }
            }
        }
        
        return found;
    }

    public static int partTwo(String path) throws FileNotFoundException {
        char[][] playField = readFile(path);
        int found = 0;

        for(int i = 1; i<playField.length-1; i++){
            for(int j = 1; j<playField.length-1;j++){
                if (playField[i-1][j-1]=='M'&&playField[i][j]=='A'&&playField[i+1][j+1]=='S'){
                    if (playField[i-1][j+1]=='M'&&playField[i+1][j-1]=='S'){
                        found++;
                    }else if (playField[i-1][j+1]=='S'&&playField[i+1][j-1]=='M'){
                        found++;
                    }
                }else if (playField[i-1][j-1]=='S'&&playField[i][j]=='A'&&playField[i+1][j+1]=='M'){
                    if (playField[i-1][j+1]=='M'&&playField[i+1][j-1]=='S'){
                        found++;
                    }else if (playField[i-1][j+1]=='S'&&playField[i+1][j-1]=='M'){
                        found++;
                    }
                }
            }
        }

        return found;
    }
}