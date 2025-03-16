import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
* @author github.com/hcmendes
*/
class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) throws IOException {
    // Write your code here
    int bribes = 0;
    boolean tooChaotic = false;
    BufferedWriter bufferedWriter = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );
    
    for (int i = 0; i < q.size(); i++) {
        if (q.get(i) > (i + 1)) {
            int distance = q.get(i) - (i + 1);
            if (distance > 2) {
                tooChaotic = true;
                break;
            }
        }
    }
    
    if (tooChaotic) {
        bufferedWriter.write("Too chaotic");
        bufferedWriter.write("\n");
        bufferedWriter.flush();
        return;
    }
    
    boolean stillUnordered = true;
    while (stillUnordered) {
    
    boolean foundUnordered = false;
    for (int i = 0; i < q.size(); i++) {
        boolean isLast = i == q.size() - 1;
        if (isLast) break;
        if (q.get(i + 1) < q.get(i)) {
            foundUnordered = true;
            bribes += 1;
            int actual = q.get(i);
            q.set(i, q.get(i + 1));
            q.set(i + 1, actual);
        }
    }
    
    if (foundUnordered == false) stillUnordered = false;
    
    }
    
    bufferedWriter.write(bribes + ""); 
    bufferedWriter.write("\n");
    bufferedWriter.flush();
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
