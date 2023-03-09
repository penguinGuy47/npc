import java.util.*;

public class findClique2 {

    public static List<Integer> findMaxClique(int[][] complementGraph) {
        int n = complementGraph.length;
        Set<Integer> candidates = new HashSet<>();
        Set<Integer> not = new HashSet<>();
        List<Integer> clique = new ArrayList<>();
        List<Integer> maxClique = new ArrayList<>();
        for (int i = 0; i < n; i++)
            candidates.add(i);
        bronKerboschHelper(complementGraph, candidates, not, clique, maxClique);
        return maxClique;
    }
    
    private static void bronKerboschHelper(int[][] complementGraph, Set<Integer> candidates, Set<Integer> not, List<Integer> clique, List<Integer> maxClique) {
        if (candidates.isEmpty() && not.isEmpty()) 
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
                Set<Integer> newNot = new HashSet<>();
                for (int v : candidates) 
                {
                    if (complementGraph[u][v] == 0 && u != v) // vertex is not adjacent to u
                        newCandidates.add(v);
                }
                for (int v : not) {
                    if (complementGraph[u][v] == 0 && u != v) // vertex is not adjacent to u
                        newNot.add(v);
                }
                bronKerboschHelper(complementGraph, newCandidates, newNot, newClique, maxClique);
                candidates.remove(u);
                not.add(u);
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
