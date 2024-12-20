package utility;

/**
 * This class implements a grid of nodes with undirected, unweighted edges.
 *
 * @author Florin Buffet
 * @version V1.2
 */

public class NodeGrid {
    private Node[][] nodes;
    private Node start;
    private Node end;

    /**
     * Constructs a NodeGrid of the specified size and optionally links elements.
     * Default start is top left, end is bottom right.
     *
     * @param size           the size of the grid (size x size)
     * @param elementsLinked if true, adjacent nodes will be linked bidirectionally
     */
    public NodeGrid(int size, boolean elementsLinked) {
        nodes = new Node[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nodes[i][j] = new Node();
                if (elementsLinked) {
                    if (j > 0) nodes[i][j].addBidirectionalNeighbor(nodes[i][j - 1], 1);
                    if (i > 0) nodes[i][j].addBidirectionalNeighbor(nodes[i - 1][j], 1);
                }
            }
        }
        start = nodes[0][0];
        end = nodes[size - 1][size - 1];
    }

    /**
     * Returns the size of the grid.
     *
     * @return the size of the grid (number of rows or columns)
     */
    public int getSize() {
        return nodes.length;
    }

    /**
     * Checks if a node exists at the specified coordinates.
     *
     * @param x the x-coordinate of the node
     * @param y the y-coordinate of the node
     * @return true if the node exists, false otherwise
     */
    public boolean doesNodeExist(int x, int y) {
        return nodes[x][y] != null;
    }

    /**
     * Deletes the node at the specified coordinates and removes all its bidirectional neighbors.
     *
     * @param x the x-coordinate of the node to delete
     * @param y the y-coordinate of the node to delete
     */
    public void deleteNode(int x, int y) {
        if (nodes[x][y] != null) {
            nodes[x][y].removeAllBidirectionalNeighbors();
            //noinspection AssignmentToNull
            nodes[x][y] = null;
        }
    }

    /**
     * Sets the start node to the node at the specified coordinates.
     *
     * @param x the x-coordinate of the start node
     * @param y the y-coordinate of the start node
     */
    public void setStart(int x, int y) {
        start = nodes[x][y];
    }

    /**
     * Sets the end node to the node at the specified coordinates.
     *
     * @param x the x-coordinate of the end node
     * @param y the y-coordinate of the end node
     */
    public void setEnd(int x, int y) {
        end = nodes[x][y];
    }

    /**
     * Calculates the shortest distance from the start node to the end node using Dijkstra's algorithm.
     *
     * @return the lowest cost from the start node to the end node
     */
    public int calculateDistances() {
        this.resetDistances();
        NodeAlgorithms.dijkstra(start);
        return end.getLowestCost();
    }

    /**
     * Resets the distance from the start node to the end node for all nodes in the grid.
     * Sets the lowest cost of each node to Integer.MAX_VALUE.
     */
    private void resetDistances() {
        for (Node[] node : nodes) {
            for (int j = 0; j < nodes[0].length; j++) {
                if (node[j] != null) node[j].setLowestCost(Integer.MAX_VALUE);
            }
        }
    }

    /**
     * Returns the lowest cost from the start node to the node at the specified coordinates without calculating it new.
     *
     * @param x the x-coordinate of the node
     * @param y the y-coordinate of the node
     * @return the lowest cost from the start node to the node at (x, y)
     */
    public int getDistanceFromStartToHere(int x, int y) {
        return nodes[x][y].getLowestCost();
    }

    /**
     * Returns a string representation of the NodeGrid.
     * 'S' represents the start node, 'E' represents the end node, 'X' represents other nodes,
     * and ' ' represents null nodes.
     *
     * @return a string representation of the NodeGrid
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node[] node : nodes) {
            for (int j = 0; j < nodes[0].length; j++) {
                if (node[j] == null) sb.append(" ");
                else if (node[j] == start) sb.append("S");
                else if (node[j] == end) sb.append("E");
                else sb.append("X");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
