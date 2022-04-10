import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystmImpl implements Systm{

    private final Collection<Node> nodes = new ArrayList<>();

    private final List<Node> lastNodes;
    private final Node firstNode;

    private static final int FIRST_NODE_ID = 1;

    public Node getFirstNode() {
        return firstNode;
    }

    public List<Node> getLastNodes() {
        return lastNodes;
    }

    public SystmImpl(Map<Integer, Collection<Integer>> graph, double[] probabilities) {
        for (Map.Entry<Integer, Collection<Integer>> entry : graph.entrySet()) {
            initializeNode(entry.getKey(), probabilities[entry.getKey() - 1], entry.getValue());
        }
        lastNodes = nodes
                .stream()
                .filter(node -> node.linkedNodesIds().size() == 0)
                .collect(Collectors.toList());

        firstNode = nodes
                .stream()
                .filter(node -> node.getId() == FIRST_NODE_ID)
                .findFirst().get();

    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    private void initializeNode(int id, double probability, Collection<Integer> linkedNodesIds) {
        Collection<Integer> linkedNodes = new ArrayList<>(linkedNodesIds);
        nodes.add(new Node(id, probability, linkedNodes));
    }
}
