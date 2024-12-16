package utility;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements an own graph network with directed, weighted edges.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class Node implements Comparable<Node> {
    private Map<Node, Integer> neighbors;
    private int lowestCost = Integer.MAX_VALUE;
    private boolean onShortestPath = false;

    /**
     * Sets the lowest cost to reach this node.
     *
     * @param cost the cost to set as the lowest cost
     */
    public void setLowestCost(int cost) {
        lowestCost = cost;
    }

    /**
     * Gets the lowest cost to reach this node.
     *
     * @return the lowest cost to reach this node
     */
    public int getLowestCost() {
        return lowestCost;
    }

    /**
     * Sets the onShortestPath flag to the specified value.
     *
     * @param onShortestPath the value to set the flag to
     */
    public void setOnShortestPath(boolean onShortestPath) {
        this.onShortestPath = onShortestPath;
    }

    /**
     * Gets the onShortestPath flag.
     *
     * @return the onShortestPath flag
     */
    public boolean isOnShortestPath() {
        return onShortestPath;
    }

    /**
     * Constructs a new Node with an empty set of neighbors.
     */
    public Node() {
        neighbors = new HashMap<>();
    }

    /**
     * Adds a neighbor to this node with the specified cost.
     *
     * @param neighbor the neighbor node to add
     * @param cost     the cost associated with the edge between this node and the neighbor node
     */
    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }

    /**
     * Adds a bidirectional neighbor to this node and the specified neighbor node.
     *
     * @param neighbor the neighbor node to add
     * @param cost     the cost associated with the edge between this node and the neighbor node
     */
    public void addBidirectionalNeighbor(Node neighbor, int cost) {
        this.addNeighbor(neighbor, cost);
        neighbor.addNeighbor(this, cost);
    }

    /**
     * Gets the neighbors of this node and their associated costs.
     *
     * @return a map of neighbors and their associated costs
     */
    public Map<Node, Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets the cost to reach a neighbor node.
     *
     * @param node the neighbor node to get the cost to
     * @return the cost to reach the neighbor node or Integer.MAX_VALUE if the neighbor node is not a neighbor
     */
    public int isNeighbor(Node node) {
        return neighbors.getOrDefault(node, Integer.MAX_VALUE);
    }

    /**
     * Compares this node with another node based on the lowest cost to reach them.
     *
     * @param other the other node to compare to
     * @return a negative integer, zero, or a positive integer as this node's lowest cost
     * is less than, equal to, or greater than the other node's lowest cost
     */
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.lowestCost, other.lowestCost);
    }
}
