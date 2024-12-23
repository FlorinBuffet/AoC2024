package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an undirected graph that extends the Graph class.
 *
 * @author Florin Buffet
 * @version V1.0
 */
@SuppressWarnings("ClassWithoutConstructor")
public class UndirectedGraph extends Graph {

    /**
     * Implements the Bron-Kerbosch algorithm to find all maximal cliques in an undirected graph.
     *
     * @param rNodes       the current clique being constructed
     * @param pNodes       the set of candidate nodes to be added to the clique
     * @param xNodes       the set of nodes already processed and not to be added to the clique
     * @param results the list to store all maximal cliques found
     * @return the list of all maximal cliques found
     */
    public List<List<Node>> bronKerbosch(List<Node> rNodes, List<Node> pNodes, List<Node> xNodes, List<List<Node>> results) {
        if (pNodes.isEmpty() && xNodes.isEmpty()) {
            results.add(rNodes);
        } else {
            Iterable<Node> pWorkingCopy = new ArrayList<>(pNodes);
            for (Node node : pWorkingCopy) {
                List<Node> newR = new ArrayList<>(rNodes);
                newR.add(node);
                List<Node> newP = new ArrayList<>(pNodes);
                newP.retainAll(node.getUnweightedNeighbors());
                List<Node> newX = new ArrayList<>(xNodes);
                newX.retainAll(node.getUnweightedNeighbors());
                this.bronKerbosch(newR, newP, newX, results);
                pNodes.remove(node);
                xNodes.add(node);
            }
        }
        return results;
    }
}
