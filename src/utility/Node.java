package utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements an own graph network with directed, weighted edges.
 * Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @author Florin Buffet
 * @version V1.2
 */
@SuppressWarnings({"unused", "CompareToUsesNonFinalVariable", "ClassHasNoToStringMethod"})
public class Node implements Comparable<Node> {
    private Map<Node, Integer> neighbors;
    private int lowestCost = Integer.MAX_VALUE;
    @SuppressWarnings("RedundantFieldInitialization")
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
        if (neighbor != null) {
            //noinspection UnnecessaryThis
            this.addNeighbor(neighbor, cost);
            neighbor.addNeighbor(this, cost);
        }
    }

    /**
     * Gets the neighbors of this node and their associated costs.
     *
     * @return a map of neighbors and their associated costs
     */
    public Map<Node, Integer> getNeighbors() {
        return Collections.unmodifiableMap(neighbors);
    }

    /**
     * Gets the cost to reach a neighbor node.
     *
     * @param node the neighbor node to get the cost to
     * @return the cost to reach the neighbor node or Integer.MAX_VALUE if the neighbor node is not a neighbor
     */
    public int distanceToNeighbor(Node node) {
        return neighbors.getOrDefault(node, Integer.MAX_VALUE);
    }

    /**
     * Removes all neighbors from this node.
     * Only removes the neighbors from this node, meaning the neighbors still have this node as a neighbor.
     */
    public void removeAllNeighbors() {
        neighbors.clear();
    }

    /**
     * Removes a neighbor from this node.
     *
     * @param node the neighbor to remove
     */
    @SuppressWarnings("WeakerAccess")
    public void removeNeighbor(Node node) {
        neighbors.remove(node);
    }

    /**
     * Removes a neighbor from this node.
     * Also removes this node from the neighbor's neighbors.
     *
     * @param node the neighbor to remove
     */
    public void removeBidirectionalNeighbor(Node node) {
        //noinspection UnnecessaryThis
        this.removeNeighbor(node);
        node.removeNeighbor(this);
    }

    /**
     * Removes all neighbors from this node.
     * Also removes this node from all neighbors' neighbors.
     */
    public void removeAllBidirectionalNeighbors() {
        for (Node neighbor : neighbors.keySet()) {
            neighbor.removeNeighbor(this);
        }
        neighbors.clear();
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
        //noinspection UnnecessaryThis
        return Integer.compare(this.lowestCost, other.lowestCost);
    }
}
