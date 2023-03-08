import java.util.*;

public class findClique {
    
    public static Set<Integer> findMaxClique(int[][] graph) {
        Set<Integer> maxClique = new HashSet<>();
        Set<Integer> potentialClique = new HashSet<>();
        Set<Integer> remainingVertices = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            remainingVertices.add(i);
        }
        bronKerbosch(maxClique, potentialClique, remainingVertices, graph);
        return maxClique;
    }
    
    private static void bronKerbosch(Set<Integer> maxClique, Set<Integer> potentialClique, Set<Integer> remainingVertices, int[][] graph) {
        if (remainingVertices.isEmpty() && potentialClique.size() > maxClique.size()) {
            maxClique.clear();
            maxClique.addAll(potentialClique);
        } else {
            Set<Integer> candidates = new HashSet<>(remainingVertices);
            for (int v : potentialClique) {
                // Retain only the candidates that are connected to every vertex in potentialClique
                candidates.retainAll(getNeighbors(v, graph));
            }
            for (int v : candidates) {
                Set<Integer> newPotentialClique = new HashSet<>(potentialClique);
                newPotentialClique.add(v);
                Set<Integer> newRemainingVertices = new HashSet<>(remainingVertices);
                newRemainingVertices.retainAll(getNeighbors(v, graph));
                bronKerbosch(maxClique, newPotentialClique, newRemainingVertices, graph);
                remainingVertices.remove(v);
                potentialClique.add(v);
            }
        }
    }
    
    private static Set<Integer> getNeighbors(int v, int[][] graph) {
        Set<Integer> neighbors = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
    
    public static void main(String[] args) {
        // int[][] graph = {
        //     {0, 1, 1, 0, 0, 0},
        //     {1, 0, 1, 1, 0, 0},
        //     {1, 1, 0, 1, 1, 0},
        //     {0, 1, 1, 0, 1, 1},
        //     {0, 0, 1, 1, 0, 1},
        //     {0, 0, 0, 1, 1, 0}
        // };

        int[][] graph = {
            {0, 1, 1, 1, 1},
            {1, 0, 0, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {1, 1, 1, 1, 0},
        };

        Set<Integer> maxClique = findMaxClique(graph);
        System.out.println("Maximum vertex clique: " + maxClique);
    }
}
