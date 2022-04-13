import java.util.*;

public class Lab2 {


    private static final Map<Integer, Collection<Integer>> systemGraph = new HashMap<>();
    private static final double[] probabilities = new double[]{0.5, 0.6, 0.7, 0.8, 0.85, 0.9, 0.92, 0.94};


    //Method to define system graph
    private static void dataInput() {
        systemGraph.put(1, Arrays.asList(2, 3));
        systemGraph.put(2, Arrays.asList(5, 4));
        systemGraph.put(3, Arrays.asList(4, 6, 8));
        systemGraph.put(4, Arrays.asList(5, 6, 8));
        systemGraph.put(5, Arrays.asList(6, 7));
        systemGraph.put(6, Arrays.asList(7, 8));
        systemGraph.put(7, Arrays.asList());
        systemGraph.put(8, Arrays.asList());


    }


    public static void main(String[] args) {
        dataInput();

        Systm system = new SystmImpl(systemGraph, probabilities);
        Executor executor = new ExecutorImpl(system);

        System.out.println("P(system) = " + executor.findSystemProbability());

    }
}
