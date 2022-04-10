import java.util.Collection;

public class Node {
    private final int id;
    private final double probability;

    // { id: checked }
    private final Collection<Integer> linkedNodesIds;

    Node(int id, double probability, Collection<Integer> linkedNodesIds) {
        this.id = id;
        this.probability = probability;
        this.linkedNodesIds = linkedNodesIds;
    }

    public int getId() {
        return id;
    }

    public Collection<Integer> linkedNodesIds() {
        return linkedNodesIds;
    }

    public double getProbability() {
        return probability;
    }
}