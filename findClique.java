import java.util.*;

public class findClique {

    public static List<Integer> findMaxClique(int[][] complementGraph) {
        int n = complementGraph.length;
        Set<Integer> candidates = new HashSet<>();
        Set<Integer> explored = new HashSet<>();
        List<Integer> clique = new ArrayList<>();
        List<Integer> maxClique = new ArrayList<>();
        for (int i = 0; i < n; i++)
            candidates.add(i);
        bronKerbosch(complementGraph, candidates, explored, clique, maxClique);
        return maxClique;
    }
    
    private static void bronKerbosch(int[][] complementGraph, Set<Integer> candidates, Set<Integer> explored, List<Integer> clique, List<Integer> maxClique) {
        if (candidates.isEmpty() && explored.isEmpty()) 
        {
            if (clique.size() > maxClique.size()) 
            {
                maxClique.clear();
                maxClique.addAll(clique);
            }
        } 
        else 
        {
            Set<Integer> candidatesCopy = new HashSet<>(candidates);
            for (int u : candidatesCopy) 
            {
                List<Integer> newClique = new ArrayList<>(clique);
                newClique.add(u);
                Set<Integer> newCandidates = new HashSet<>();
                Set<Integer> newSet = new HashSet<>();
                for (int v : candidates) 
                {
                    if (complementGraph[u][v] == 0 && u != v) // vertex is not adjacent to u
                        newCandidates.add(v);
                }
                for (int v : explored) {
                    if (complementGraph[u][v] == 0 && u != v) // vertex is not adjacent to u
                        newSet.add(v);
                }
                bronKerbosch(complementGraph, newCandidates, newSet, newClique, maxClique);
                candidates.remove(u);
                explored.add(u);
            }
        }
    }
    

    public static void main(String[] args){

        int[][] graph = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0}
        };
        List<Integer> maxClique = findMaxClique(graph);
        System.out.println("Max clique: " + maxClique);
    }
}
