import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class PalindromeDetector {
    public static boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) return true;
        String clean = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return new StringBuilder(clean).reverse().toString().equals(clean);
    }
    
    public static void main(String[] args) {
        try {
            List<String> palindromes = Files.lines(Paths.get("words.txt"))
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .filter(PalindromeDetector::isPalindrome)
                .collect(Collectors.toList());
                
            System.out.println("Palindrome words found: " + palindromes);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
