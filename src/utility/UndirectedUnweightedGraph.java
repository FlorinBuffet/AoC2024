package utility;

/**
 * Represents an undirected, unweighted graph that extends the Graph class.
 *
 * @author Florin Buffet
 * @version V1.0
 */
@SuppressWarnings({"ClassTooDeepInInheritanceTree", "ClassWithoutConstructor"})
public class UndirectedUnweightedGraph extends UndirectedGraph {

    /**
     * Adds an edge between two nodes identified by their names.
     * If both nodes exist, a bidirectional edge with a weight of 1 is added between them.
     *
     * @param name1 the name of the first node
     * @param name2 the name of the second node
     */
    public void addEdge(String name1, String name2) {
        Node node1 = this.getNodeByName(name1);
        Node node2 = this.getNodeByName(name2);
        if (node1 != null && node2 != null) {
            //noinspection LawOfDemeter
            node1.addBidirectionalNeighbor(node2, 1);
        }
    }
}
