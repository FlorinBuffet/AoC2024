package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class implements different Algorithms on the Node Class in the same package.
 *
 * @author Florin Buffet
 * @version V1.1
 */
public class NodeAlgorithms {

    /**
     * Private constructor to prevent instantiation.
     */
    private NodeAlgorithms() {
    }

    /**
     * Calculates the shortest paths from the start node to all other nodes using Dijkstra's algorithm.
     *
     * @param startNode the starting node for the algorithm
     */
    public static void dijkstra(Node startNode) {
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

    public static void markShortestPath(Node endNode) {
        List<Node> queue = new ArrayList<>();
        endNode.setOnShortestPath(true);
        queue.add(endNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            if (currentNode.isOnShortestPath()){
                for (Node neighbor : currentNode.getNeighbors().keySet()) {
                    if (neighbor.getLowestCost() + neighbor.distanceToNeighbor(currentNode) == currentNode.getLowestCost()){
                        neighbor.setOnShortestPath(true);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }


}
