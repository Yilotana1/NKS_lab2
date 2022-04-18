import java.util.*;

public class Lab2 {


    private static final Map<Integer, Collection<Integer>> systemGraph = new HashMap<>();
    private static final double[] probabilities = new double[]{0.88, 0.14, 0.93, 0.04, 0.98, 0.56, 0.59, 0.64};
    private static final Collection<Integer> firstNodes = Arrays.asList(1, 2);

    //Method to define system graph
    private static void dataInput() {
        systemGraph.put(1, Arrays.asList(3, 4));
        systemGraph.put(2, Arrays.asList(3, 5));
        systemGraph.put(3, Arrays.asList(4, 5));
        systemGraph.put(4, Arrays.asList(6, 7));
        systemGraph.put(5, Arrays.asList(6, 8));
        systemGraph.put(6, Arrays.asList(7, 8));
        systemGraph.put(7, Arrays.asList());
        systemGraph.put(8, Arrays.asList());

    }


    public static void main(String[] args) {
        dataInput();

        Systm system = new SystmImpl(systemGraph, probabilities, firstNodes);
        ExecutorImpl executor = new ExecutorImpl(system);

        System.out.println("P(system) = " + executor.findSystemProbability());

        int i = 0;
        for (LinkedList<Integer> route :
                executor.getRoutes()) {
            System.out.println(++i + ") " + route);
        }
    }
}
