import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

/**
@author github.com/hcmendes
tempo aproximado: 0.15 ms
*/
class Result {
    
    static long totalTimeInNano = 0;

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        
        long start = System.nanoTime();
        
        List<String[]> anagrams = new ArrayList<String[]>();
        
        for (int n = 1; n < s.length(); n++) {
        
        List<String> size1substrings = getSubstrings(s, n);
                
        for (int i = 0; i < size1substrings.size();  i++) {
            for (int j = 0; j < size1substrings.size(); j++) {
                if (i == j) continue;
                
                String word1 = size1substrings.get(i);
                String word2 = size1substrings.get(j);
                
                boolean existsInAnagramsArray = false;
                for (String[] pair : anagrams) {
                    if ((pair[0] == word1 && pair[1] == word2) ||
                        (pair[0] == word2 && pair[1] == word1)) {
                            // word pair already exists in anagrams array
                            existsInAnagramsArray = true;
                        }
                }
                if (existsInAnagramsArray) continue;
                
                char[] word1chars = word1.toCharArray();
                char[] word2chars = word2.toCharArray();
                
                if (isAnagram(word1chars, word2chars)) {
                    
                    anagrams.add(new String[] {word1, word2});
                }
            }   
        }
        
        }
        
        long end = System.nanoTime();
        totalTimeInNano += (end-start);
        
        System.out.println(totalTimeInNano / 1_000_000.0);
        return anagrams.size();
    }
    
    static List<String> getSubstrings(String s, int substringSize) {
        List<String> substrings = new ArrayList<>();
        
        if (substringSize == 1) {
            for (int k = 0; k < s.length(); k++) {
                substrings.add(s.substring(k, k + 1));
            }
        } else {
        
        for (int j = 0; j < s.length(); j++) {
            int endIndex = j + substringSize;
            if (endIndex > s.length()) continue;
            
            String word = s.substring(j, endIndex);
            
            substrings.add(word);   
        }
        
        
        }
        
        return substrings;
    }
    
    static boolean isAnagram(char[] word1, char[] word2) {
        if (word1.length != word2.length) return false;
        
        char[] word2copy = new char[word2.length];
        System.arraycopy(word2, 0, word2copy, 0, word2.length);
        
        for (char letter1 : word1) {
            for (int j = 0; j < word2copy.length; j++) {
                if (word2copy[j] == '\0' && j == (word2copy.length - 1)) return false;
                if (word2copy[j] == '\0') continue;
                if (letter1 == word2copy[j]) {
                    
                    word2copy[j] = '\0';
                    
                    break;
                } else if(j == (word2copy.length - 1)) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        
        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {

            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
