package utility;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class implements Dijkstra on the Node Class in the same package.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class Dijkstra {

    /**
     * Calculates the shortest paths from the start node to all other nodes using Dijkstra's algorithm.
     *
     * @param startNode the starting node for the algorithm
     */
    public static void calculateShortestPaths(Node startNode) {
        startNode.setLowestCost(0);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentCost = currentNode.getLowestCost();

            for (Map.Entry<Node, Integer> neighbor : currentNode.getNeighbors().entrySet()) {
                Node neighborNode = neighbor.getKey();
                int neighborCost = neighbor.getValue();

                if (currentCost + neighborCost < neighborNode.getLowestCost()) {
                    neighborNode.setLowestCost(currentCost + neighborCost);
                    queue.add(neighborNode);
                }
            }
        }
    }
}
