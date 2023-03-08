import java.util.*;

public class findClique2 {

    public static List<Integer> findMaxClique(int[][] graph) {
        List<Integer> maxClique = new ArrayList<>();
        int n = graph.length;
        int[] currentClique = new int[n];
        for (int i = n; i >= 1; i--) {
            if (maxClique.size() >= i) {
                break;
            }
            findMaxCliqueHelper(graph, i, 0, 0, currentClique, maxClique);
        }
        return maxClique;
    }

    public static void findMaxCliqueHelper(int[][] graph, int size, int depth, int start, int[] currentClique, List<Integer> maxClique) {
        int n = graph.length;
        if (depth == size) {
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (graph[currentClique[i]][currentClique[j]] == 0) {
                        return;
                    }
                }
            }
            maxClique.clear();
            for (int i = 0; i < size; i++) {
                maxClique.add(currentClique[i]);
            }
        } else {
            for (int i = start; i < n; i++) {
                currentClique[depth] = i;
                findMaxCliqueHelper(graph, size, depth + 1, i + 1, currentClique, maxClique);
            }
        }
    }

    public static void main(String[] args) {
        // int[][] graph = {{0, 1, 1, 0},
        //                  {1, 0, 1, 1},
        //                  {1, 1, 0, 1},
        //                  {0, 1, 1, 0}};

        int[][] graph = {
            {0, 1, 1, 1, 1},
            {1, 0, 0, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {1, 1, 1, 1, 0},
        };
        List<Integer> maxClique = findMaxClique(graph);
        System.out.println("Max clique: " + maxClique);
    }
}
