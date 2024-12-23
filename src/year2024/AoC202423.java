package year2024;

import utility.InputParser;
import utility.Node;
import utility.UndirectedUnweightedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves AdventofCode 2024, Day Template.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202423 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202423() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<List<String>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<String>> list = InputParser.parseStringListPerLine(scanner, "-");
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
        List<List<String>> connections = readFile(path);

        UndirectedUnweightedGraph graph = buildGraph(connections);

        int count = 0;
        for (Node node : graph.getNodes()) {
            if (node.getName().charAt(0) == 't') {
                count += graph.countCyclesOfLength(node, node, 3) / 2;
                node.setVisited(true);
            }
        }

        return count;
    }

    private static UndirectedUnweightedGraph buildGraph(List<List<String>> connections) {
        UndirectedUnweightedGraph graph = new UndirectedUnweightedGraph();

        while (!connections.isEmpty()) {
            List<String> connection = connections.removeFirst();
            String nodeA = connection.get(0);
            String nodeB = connection.get(1);
            graph.addNode(nodeA);
            graph.addNode(nodeB);
            graph.addEdge(nodeA, nodeB);
        }

        return graph;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static String partTwo(String path) throws FileNotFoundException {
        List<List<String>> connections = readFile(path);

        UndirectedUnweightedGraph graph = buildGraph(connections);
        List<List<Node>> results = graph.bronKerbosch(new ArrayList<>(), new ArrayList<>(graph.getNodes()), new ArrayList<>(), new ArrayList<>());

        results.sort((o1, o2) -> o2.size() - o1.size());
        List<Node> largestClique = results.getFirst();
        largestClique.sort(Comparator.comparing(Node::getName));

        StringBuilder returnString = new StringBuilder();
        for (Node node : largestClique) {
            returnString.append(node.getName());
            returnString.append(",");
        }
        return returnString.substring(0, returnString.length() - 1);
    }
}