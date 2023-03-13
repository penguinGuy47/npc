import java.io.File;
import java.io.IOException;
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
    
    public static void cliqueInput(String readFile) throws IOException
    {
        System.out.println(String.format("* A Maximum Vextex Clique of every graph in %s *", readFile));
        System.out.println("   (|V|, |E|) (size, ms used) Vertex Clique");
        File rfile = new File(readFile);
        Scanner sc = new Scanner(rfile);
        int graphCount = 1;

        while (sc.hasNextLine())
        {
            // get the number of matrix length
            String data = sc.nextLine();

            int max = Integer.parseInt(data);
            int[][] tempM = new int[max][max];

            //start of matrix
            for (int i = 0; i < tempM.length; i++)  
            {
                // check line in the matrix
                data = sc.nextLine();
                String[] newSet = data.split(" ");

                for (int j = 0; j < tempM[i].length; j++)
                {
                   tempM[i][j] = Integer.parseInt(newSet[j]);
                }
            }
            long startTime = System.currentTimeMillis();
            List<Integer> maxClique = findClique.findMaxClique(tempM);
            long endTime = System.currentTimeMillis();

            String stringOutput = String.format("G%d (%d) (ms=%d) ", graphCount, max, endTime - startTime);
            System.out.println("" + stringOutput + maxClique);
            graphCount += 1;
        }
        sc.close();
    }

    public static void main(String[] args) throws IOException{
        String readFile = args[0];
        cliqueInput(readFile);
    }
}
