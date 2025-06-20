import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
* @author github.com/hcmendes
*/
class Result {
    
    static BufferedWriter bufferedWriter = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) throws IOException {
        
        int n = a.size();
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a.get(j) > a.get(j + 1)) {
                    int actual = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, actual);
                    swaps++;
                }
            }
            
        }
    
        bufferedWriter.write("Array is sorted in " + swaps + " swaps.");
        bufferedWriter.write("\n");
        bufferedWriter.write("First Element: " + a.get(0));
        bufferedWriter.write("\n");
        bufferedWriter.write("Last Element: " + a.get(n - 1));
        bufferedWriter.write("\n");
        bufferedWriter.flush();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.countSwaps(a);

        bufferedReader.close();
    }
}
