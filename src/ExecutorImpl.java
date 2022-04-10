import java.util.*;

public class ExecutorImpl implements Executor {


    private final Systm system;
    private final Collection<LinkedList<Integer>> routes = new ArrayList<>();




    public ExecutorImpl(Systm system) {
        this.system = system;
        findRoutes();
    }

    public double findSystemProbability() {

        Collection<Double> statesProbabilities = findStatesProbabilities(
                findWorkableStates(
                        generateAllStates(
                                system.getNodes().size()
                        )
                )
        );


        double probability = 0;
        for (double p : statesProbabilities) {
            probability += p;
        }
        return probability;
    }

    private void findRoutes() {
        for (Node lastNode : system.getLastNodes()) {
            depthSearch(system.getFirstNode(), lastNode, new LinkedList<>());
        }
    }

    private Collection<Double> findStatesProbabilities(List<List<Integer>> workableStates) {
        Collection<Double> probabilities = new HashSet<>();

        for (List<Integer> state : workableStates) {
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


    private List<List<Integer>> findWorkableStates(List<List<Integer>> states) {
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

    private static boolean stateIsWorkable(List<Integer> systemState, List<Integer> route) {
        for (int nodeState : route) {

            if (systemState.get(nodeState - 1) != 1) {
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> generateAllStates(int n) {
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


    private void depthSearch(Node currentNode, Node lastNode, LinkedList<Integer> stack) {
        stack.addLast(currentNode.getId());

        if (currentNode.getId() == lastNode.getId()) {
            routes.add(cloneStack(stack));
            return;
        }
        for (Integer id : currentNode.linkedNodesIds()) {
            Node nodeInstance = getNodeById(id);
            depthSearch(nodeInstance, lastNode, stack);
            stack.removeLast();
        }
    }

    private Node getNodeById(int id) {
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
