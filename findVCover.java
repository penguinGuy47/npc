import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class findVCover 
{

    public static void main(String[] args) throws IOException
    {
        File rfile = new File("graphs.txt");
        Scanner sc = new Scanner(rfile);
        FileWriter wfile = new FileWriter("graphsOutput.txt");
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
}