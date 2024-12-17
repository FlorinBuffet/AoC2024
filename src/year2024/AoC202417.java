package year2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class solves AdventofCode 2024, Day 17.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202417 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202417() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the data as an Object array, containing the register values and the commands
     * @throws FileNotFoundException if the file is not found
     */
    @SuppressWarnings({"RegExpAnonymousGroup", "ResultOfMethodCallIgnored"})
    private static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        Pattern pattern = Pattern.compile("Register [A-C]: (\\d+)");
        Matcher matcher = pattern.matcher(scanner.nextLine());
        matcher.find();
        long registerA = Long.parseLong(matcher.group(1));
        matcher = pattern.matcher(scanner.nextLine());
        matcher.find();
        long registerB = Long.parseLong(matcher.group(1));
        matcher = pattern.matcher(scanner.nextLine());
        matcher.find();
        long registerC = Long.parseLong(matcher.group(1));
        scanner.nextLine();
        String lastLine = scanner.nextLine();
        scanner.close();
        lastLine = lastLine.substring(9);
        List<Integer> commands =
                Arrays.stream(lastLine.split(",")).map(Integer::parseInt).toList();
        return new Object[]{registerA, registerB, registerC, commands};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as a String.
     * @throws FileNotFoundException if the file is not found
     */
    public static String partOne(String path) throws FileNotFoundException {
        Object[] input = readFile(path);
        long registerA = (long) input[0];
        long registerB = (long) input[1];
        long registerC = (long) input[2];
        //noinspection unchecked
        List<Integer> commands = (List<Integer>) input[3];
        return String.join(",", doProgram(commands, registerA, registerB, registerC));
    }

    /**
     * Calculates a value based on the given combo and register values.
     *
     * @param combo     the combo value which determines the calculation
     * @param registerA the value of register A
     * @param registerB the value of register B
     * @param registerC the value of register C
     * @return the calculated result based on the combo value
     * @throws IllegalStateException if the combo value is invalid
     */
    @SuppressWarnings("ImplicitNumericConversion")
    private static long calculateCombo(int combo, long registerA, long registerB, long registerC) {
        return switch (combo) {
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> combo;
        };
    }

    /**
     * Executes the program based on the given commands and register values.
     *
     * @param prog the list of commands to execute
     * @param registerA the initial value of register A
     * @param registerB the initial value of register B
     * @param registerC the initial value of register C
     * @return the result of the program execution as a list of strings
     */
    @SuppressWarnings({"AssignmentToMethodParameter", "MagicNumber"})
    private static List<String> doProgram(List<Integer> prog, Long registerA, Long registerB, Long registerC) {
        List<String> out = new ArrayList<>();
        for (int insPointer = 0; insPointer < prog.size(); insPointer += 2) {
            int lit = prog.get(insPointer + 1);
            long combo = calculateCombo(lit, registerA, registerB, registerC);
            switch (prog.get(insPointer)) {
                case 0 -> registerA /= (int) StrictMath.pow(2.0, combo); // adv
                case 1 -> registerB ^= lit; // bxl
                case 2 -> registerB = combo % 8; // bst
                case 3 -> //noinspection AssignmentToForLoopParameter
                        insPointer = (registerA == 0) ? insPointer : lit - 2; // jnz
                case 4 -> registerB ^= registerC; // bxc
                case 5 -> out.add(String.valueOf(combo % 8)); // out
                case 6 -> registerB = (long) (registerA / StrictMath.pow(2.0, combo)); // bdv
                case 7 -> registerC = (long) (registerA / StrictMath.pow(2.0, combo)); // cdv
            }
        }
        return out;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path path to the input file
     * @return the result as a String.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        Object[] input = readFile(path);
        //noinspection unchecked
        List<Integer> commands = (List<Integer>) input[3];
        List<Integer> expected = new ArrayList<>(commands);
        Collections.reverse(expected);

        return findRegisterA(0L, 0, expected, commands);
    }

    /**
     * Recursively finds the value of 'a' that matches the target sequence.
     *
     * @param a the current value being tested
     * @param depth the current depth of recursion
     * @param target the target sequence to match
     * @param prog the list of program commands
     * @return the value of 'a' that matches the target sequence, or 0 if no match is found
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    private static Long findRegisterA(Long a, int depth, List<Integer> target, List<Integer> prog) {
        if (depth == target.size()) {
            return a;
        }
        for (int i = 0; i < 8; i++) {
            List<String> output = doProgram(prog, (a << 3) + i, 0L, 0L);
            if (!output.isEmpty() && Long.parseLong(output.getFirst()) == target.get(depth)) {
                Long result = findRegisterA((a << 3) + i, depth + 1, target, prog);
                if (result != 0) {
                    return result;
                }
            }
        }
        return 0L;
    }
}