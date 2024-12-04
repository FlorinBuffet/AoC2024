import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("2024 Day 01, Part 1: "+AoC202401.partOne("data/AoC_2024_01.txt"));
        System.out.println("2024 Day 01, Part 2: "+AoC202401.partTwo("data/AoC_2024_01.txt"));
        System.out.println("2024 Day 02, Part 1: "+AoC202402.partOne("data/AoC_2024_02.txt"));
        System.out.println("2024 Day 02, Part 2: "+AoC202402.partTwo("data/AoC_2024_02.txt"));
        System.out.println("2024 Day 03, Part 1: "+AoC202403.partOne("data/AoC_2024_03.txt"));
        System.out.println("2024 Day 03, Part 2: "+AoC202403.partTwo("data/AoC_2024_03.txt"));
        System.out.println("2024 Day 04, Part 1: "+AoC202404.partOne("data/AoC_2024_04.txt"));
        System.out.println("2024 Day 04, Part 2: "+AoC202404.partTwo("data/AoC_2024_04.txt"));
    }
}