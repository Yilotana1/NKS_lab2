import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SystmImpl implements Systm {

    private final Collection<Node> nodes = new ArrayList<>();

    private final Collection<Node> lastNodes;
    private final Collection<Node> firstNodes;

    private static final int FIRST_NODE_ID = 1;

    public Collection<Node> getFirstNodes() {
        return firstNodes;
    }

    public Collection<Node> getLastNodes() {
        return lastNodes;
    }

    public SystmImpl(Map<Integer, Collection<Integer>> graph, double[] probabilities, Collection<Integer> firstNodes) {
        for (Map.Entry<Integer, Collection<Integer>> entry : graph.entrySet()) {
            initializeNode(entry.getKey(), probabilities[entry.getKey() - 1], entry.getValue());
        }
        lastNodes = nodes
                .stream()
                .filter(node -> node.linkedNodesIds().size() == 0)
                .collect(Collectors.toList());

        this.firstNodes = nodes
                .stream()
                .filter(node -> firstNodes.contains(node.getId())).collect(Collectors.toList());

    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    private void initializeNode(int id, double probability, Collection<Integer> linkedNodesIds) {
        Collection<Integer> linkedNodes = new ArrayList<>(linkedNodesIds);
        nodes.add(new Node(id, probability, linkedNodes));
    }
}
