import java.util.*;

public class Executor {


    private final Systm system;
    private final Collection<LinkedList<Integer>> routes = new ArrayList<>();

    public Collection<LinkedList<Integer>> findRoutes() {
        for (Systm.Node lastNode : system.getLastNodes()) {
            depthSearch(system.getFirstNode(), lastNode, new LinkedList<>());
        }
        return routes;
    }


    public Executor(Systm system) {
        this.system = system;
    }

    public double findSystemProbability(Collection<Double> statesProbabilities) {
        double probability = 0;
        for (double p : statesProbabilities) {
            probability += p;
        }
        return probability;
    }

    public Collection<Double> findStatesProbabilities(List<List<Integer>> states) {
        Collection<Double> probabilities = new HashSet<>();

        for (List<Integer> state : states) {
            double probability = 1;


            for (int i = 0; i < state.size(); i++) {

                if (state.get(i) == 1) {
                    probability *= getNodeById(i + 1).getProbability();

                } else {
                    probability *= 1 - getNodeById(i + 1).getProbability();
                }
            }


            probabilities.add(probability);
        }

        return probabilities;
    }


    public List<List<Integer>> findWorkableStates(List<List<Integer>> states) {
        List<List<Integer>> workableStates = new ArrayList<>();

        for (List<Integer> currentState : states) {
            for (LinkedList<Integer> route : routes) {
                if (stateIsWorkable(currentState, route) && !workableStates.contains(currentState)) {
                    workableStates.add(currentState);
                }
            }
        }
        return workableStates;
    }

    public static boolean stateIsWorkable(List<Integer> systemState, List<Integer> route) {
        for (int nodeState : route) {

            if (systemState.get(nodeState - 1) != 1) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> generateAllStates(int n) {
        int statesNumber = (int) Math.pow(2, n);
        List<List<Integer>> states = new ArrayList<>();

        for (int i = 0; i < statesNumber; i++) {
            List<Integer> state = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    state.add(j, 1);
                } else {
                    state.add(j, 0);
                }
                states.add(i, state);
            }
        }
        return states;
    }


    private void depthSearch(Systm.Node currentNode, Systm.Node lastNode, LinkedList<Integer> stack) {
        stack.addLast(currentNode.getId());

        if (currentNode.getId() == lastNode.getId()) {
            routes.add(cloneStack(stack));
            return;
        }
        for (Integer id : currentNode.linkedNodesIds()) {
            Systm.Node nodeInstance = getNodeById(id);
            depthSearch(nodeInstance, lastNode, stack);
            stack.removeLast();
        }
    }

    private Systm.Node getNodeById(int id) {
        return system.getNodes().stream()
                .filter(node -> node.getId() == id)
                .findFirst().get();
    }


    private LinkedList<Integer> cloneStack(LinkedList<Integer> stack) {
        LinkedList<Integer> clone = new LinkedList<>();
        for (Integer i : stack) {
            clone.addLast(i);
        }
        return clone;
    }

}
