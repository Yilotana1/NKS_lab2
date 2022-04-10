import java.util.*;
import java.util.stream.Collectors;

public class Main {


    private static final Map<Integer, Collection<Integer>> systemGraph = new HashMap<>();
    private static final double[] probabilities = new double[]{0.5, 0.6, 0.7, 0.8, 0.85, 0.9, 0.92, 0.94};

    private static void dataInput() {
        systemGraph.put(1, new ArrayList<>(Arrays.asList(2, 3)));
        systemGraph.put(2, new ArrayList<>(Arrays.asList(4, 5)));
        systemGraph.put(3, new ArrayList<>(Arrays.asList(4, 6, 8)));
        systemGraph.put(4, new ArrayList<>(Arrays.asList(5, 6, 8)));
        systemGraph.put(5, new ArrayList<>(Arrays.asList(6, 7)));
        systemGraph.put(6, new ArrayList<>(Arrays.asList(7, 8)));
        systemGraph.put(7, new ArrayList<>(Arrays.asList()));
        systemGraph.put(8, new ArrayList<>(Arrays.asList()));


    }

    public static void main(String[] args) {
        dataInput();

        Systm system = new Systm(systemGraph, probabilities);
        Executor executor = new Executor(system);
        Collection<LinkedList<Integer>> routes = executor.findRoutes();
        System.out.println("Routes = " + routes);
        System.out.println("ROutes len = " + routes.size());
        List<List<Integer>> states = executor.generateAllStates(8);
        List<List<Integer>> workableStates = executor.findWorkableStates(states);

        System.out.println("workable states = " + workableStates);

        Collection<Double> statesProbs = executor
                .findStatesProbabilities(workableStates).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("states prob = " + statesProbs);
        System.out.println("systemProb = " + executor.findSystemProbability(executor.findStatesProbabilities(workableStates)));





    }
}
