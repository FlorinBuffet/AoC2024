package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a graph with a list of nodes.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class Graph {
    private List<Node> nodes;

    /**
     * Constructs a new Graph.
     * Initializes the list of nodes.
     */
    public Graph() {
        nodes = new ArrayList<>();
    }

    /**
     * Resets the visited status of all nodes in the graph.
     */
    public void resetVisited() {
        for (Node node : nodes) {
            node.setVisited(false);
        }
    }

    /**
     * Adds a new node to the graph if a node with the given name does not already exist.
     *
     * @param name the name of the node to be added
     */
    public void addNode(String name) {
        if (this.getNodeByName(name) == null) {
            Node node = new Node(name);
            nodes.add(node);
        }
    }

    /**
     * Retrieves a node by its name.
     *
     * @param name the name of the node to retrieve
     * @return the node with the specified name, or null if no such node exists
     */
    public Node getNodeByName(String name) {
        Node found = null;
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                found = node;
            }
        }
        return found;
    }

    /**
     * Returns the number of nodes in the graph.
     *
     * @return the size of the graph
     */
    public int getSize() {
        return nodes.size();
    }

    /**
     * Returns the number of nodes in the graph.
     *
     * @return the size of the graph
     */
    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    /**
     * Helper method to count the number of cycles of a given length.
     *
     * @param startNode the starting node for the cycle
     * @param currentNode the current node in the cycle
     * @param length the remaining length of the cycle
     * @return the number of cycles of the specified length
     */
    @SuppressWarnings({"MethodWithMultipleReturnPoints", "FeatureEnvy"})
    public int countCyclesOfLength(Node startNode, Node currentNode, int length) {
        if (length == 1) {
            if (startNode.getNeighbors().containsKey(currentNode)) {
                return 1;
            } else {
                return 0;
            }
        }
        startNode.setVisited(true);
        int count = 0;
        for (Node neighbor : currentNode.getNeighbors().keySet()) {
            if (!neighbor.isVisited()) {
                count += this.countCyclesOfLength(startNode, neighbor, length - 1);
            }
        }
        startNode.setVisited(false);
        return count;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return a string indicating the size of the graph
     */
    @Override
    public String toString() {
        return "Graph of size " + nodes.size();
    }
}
