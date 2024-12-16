package year2024;

import utility.Node;
import utility.NodeAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: cleanup class

/**
 * This class solves AdventofCode 2024, Day 16.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class AoC202416 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202416() {
    }

    /**
     * Reads the input file and returns the data for the day's part one of the challenge.
     *
     * @param path the path to the input file
     * @return the data as a list of Integers
     * @throws FileNotFoundException if the file is not found
     */
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String data = scanner.nextLine();
        char[][] grid = new char[data.length()][data.length()];
        for (int i = 0; i < data.length() - 1; i++) {
            grid[i] = data.toCharArray();
            data = scanner.nextLine();
        }
        grid[data.length() - 1] = data.toCharArray();
        scanner.close();
        return grid;
    }

    /**
     * Creates a graph representation from the given grid.
     * Each cell in the grid is represented by four nodes, one for each direction (up, right, down, left).
     * Nodes are connected based on the grid layout and specific rules for start and end nodes.
     *
     * @param grid the grid representing the maze or map
     * @return an array containing the start node and the end node
     */
    @SuppressWarnings("squid:S3776") //disable "Cognitive Complexity of methods should not be too high"
    private static Object[] createGraph(char[][] grid) {
        // 0 = up, 1 = right, 2 = down, 3 = left
        Node[][][] nodes = new Node[grid.length][grid.length][4];
        Node startNode = null;
        Node endNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '#') {

                    //Creates the nodes for each direction
                    nodes[i][j][0] = new Node();
                    nodes[i][j][1] = new Node();
                    nodes[i][j][2] = new Node();
                    nodes[i][j][3] = new Node();

                    //Connects the nodes for each direction with the turning cost of 1000
                    nodes[i][j][0].addBidirectionalNeighbor(nodes[i][j][1], 1000);
                    nodes[i][j][1].addBidirectionalNeighbor(nodes[i][j][2], 1000);
                    nodes[i][j][2].addBidirectionalNeighbor(nodes[i][j][3], 1000);
                    nodes[i][j][3].addBidirectionalNeighbor(nodes[i][j][0], 1000);

                    //Marks the nodes for the start and creates a shared end node that gets reached by all directions
                    if (grid[i][j] == 'S') {
                        startNode = nodes[i][j][1];
                    } else if (grid[i][j] == 'E') {
                        endNode = new Node();
                        nodes[i][j][0].addNeighbor(endNode, 0);
                        nodes[i][j][1].addNeighbor(endNode, 0);
                        nodes[i][j][2].addNeighbor(endNode, 0);
                        nodes[i][j][3].addNeighbor(endNode, 0);
                        endNode.addNeighbor(nodes[i][j][0], 100000); // cost of 100000 to prevent turning
                        endNode.addNeighbor(nodes[i][j][1], 100000); // cost of 100000 to prevent turning
                        endNode.addNeighbor(nodes[i][j][2], 100000); // cost of 100000 to prevent turning
                        endNode.addNeighbor(nodes[i][j][3], 100000); // cost of 100000 to prevent turning
                    }
                }
            }
        }

        //Connects the nodes with the nodes in the same row and column
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                if (grid[i][j] != '#') {
                    if (grid[i - 1][j] != '#') {
                        nodes[i][j][0].addNeighbor(nodes[i - 1][j][0], 1);
                        nodes[i - 1][j][0].addNeighbor(nodes[i][j][0], 100000); // cost of 100000 to prevent turning
                    }
                    if (grid[i][j + 1] != '#') {
                        nodes[i][j][1].addNeighbor(nodes[i][j + 1][1], 1);
                        nodes[i][j + 1][1].addNeighbor(nodes[i][j][1], 100000); // cost of 100000 to prevent turning
                    }
                    if (grid[i + 1][j] != '#') {
                        nodes[i][j][2].addNeighbor(nodes[i + 1][j][2], 1);
                        nodes[i + 1][j][2].addNeighbor(nodes[i][j][2], 100000); // cost of 100000 to prevent turning
                    }
                    if (grid[i][j - 1] != '#') {
                        nodes[i][j][3].addNeighbor(nodes[i][j - 1][3], 1);
                        nodes[i][j - 1][3].addNeighbor(nodes[i][j][3], 100000); // cost of 100000 to prevent turning
                    }
                }
            }
        }

        return new Object[]{startNode, endNode, nodes};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        char[][] grid = readFile(path);
        Object[] input = createGraph(grid);
        Node startNode = (Node) input[0];
        Node endNode = (Node) input[1];
        NodeAlgorithms.dijkstra(startNode);
        return endNode.getLowestCost();
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        char[][] grid = readFile(path);
        Object[] input = createGraph(grid);
        Node startNode = (Node) input[0];
        Node endNode = (Node) input[1];
        Node[][][] nodes = (Node[][][]) input[2];
        NodeAlgorithms.dijkstra(startNode);
        NodeAlgorithms.markShortestPath(endNode);
        return countFieldsOnShortestPath(nodes);
    }

    /**
     * Counts the number of fields that are on at least one shortest path in the given nodes array.
     *
     * @param nodes a 3D array of nodes representing the grid
     * @return the number of fields on the shortest path
     */
    private static int countFieldsOnShortestPath(Node[][][] nodes) {
        int fieldsOnShortestPath = 0;
        for (Node[][] node : nodes) {
            for (Node[] value : node) {
                if (value[0] != null && (value[0].isOnShortestPath() || value[1].isOnShortestPath()
                        || value[2].isOnShortestPath() || value[3].isOnShortestPath()))
                    fieldsOnShortestPath++;
            }
        }
        return fieldsOnShortestPath;
    }
}