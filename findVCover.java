import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class findVCover 
{

    public static void main(String[] args) throws IOException
    {
        String readFile = args[0];
        String outputFile = "graphsOutput.txt";

        vCover(readFile, outputFile);
        
        cliqueInput(outputFile);
    }

    public static void vCover(String readFile, String outputFile) throws IOException
    {
        File rfile = new File(readFile);
        Scanner sc = new Scanner(rfile);
        FileWriter wfile = new FileWriter(outputFile);
        while (sc.hasNextLine())
        {
            // get the number of matrix length
            String data = sc.nextLine();

            int max = Integer.parseInt(data);

            wfile.write(data + "\n");
            for (int i = 0; i < max; i++)  
            {
                String matrix = "";

                // check line in the matrix
                data = sc.nextLine();
                String[] newSet = data.split(" ");

                // check if numbers are 1's or 0's
                for (int j = 0; j < max; j++)
                {
                    if (newSet[j].equals("1"))
                        matrix = matrix + "0 ";
                    else if (newSet[j].equals("0"))
                        matrix = matrix + "1 ";
                }
                wfile.write(matrix + "\n");
            }
        }
        sc.close();
        wfile.close();
    }

    public static void cliqueInput(String readFile) throws IOException
    {
        System.out.println(String.format("* A Minimum Vertex Cover of every graph in %s: (reduced to K-Clique) *", readFile));
        System.out.println("   (|V|) (ms used) Vertex Cover");
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
}
