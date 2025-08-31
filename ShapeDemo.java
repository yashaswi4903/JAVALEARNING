public sealed abstract class Shape permits Circle, Rectangle, Triangle {
    public abstract double area();
}

final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle extends Shape {
    private final double width;
    private final double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
}

final class Triangle extends Shape {
    private final double base;
    private final double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4);
        
        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());
    }
}
////////
import java.util.*;

public class Fibonacci {
    // Recursive approach (inefficient)
    public static long fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    // Memoization approach (efficient)
    public static long fibonacciMemo(int n) {
        return fibonacciMemo(n, new HashMap<>());
    }
    
    private static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    public static void main(String[] args) {
        int n = 10;
        
        long startTime = System.nanoTime();
        long recursiveResult = fibonacciRecursive(n);
        long recursiveTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long memoResult = fibonacciMemo(n);
        long memoTime = System.nanoTime() - startTime;
        
        System.out.println("Fibonacci(" + n + ") = " + recursiveResult);
        System.out.println("Recursive time: " + recursiveTime + " ns");
        System.out.println("Memoization time: " + memoTime + " ns");
        System.out.println("Memoization is " + (recursiveTime / memoTime) + " times faster");
    }
}


////
public class GradeClassifier {
    public static String classifyGrade(int score) {
        return switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }
    
    public static void main(String[] args) {
        int[] scores = {95, 85, 75, 65, 55, 45};
        
        for (int score : scores) {
            String grade = classifyGrade(score);
            System.out.println("Score: " + score + " -> Grade: " + grade);
        }
    }
}
///////
public class GradeClassifier {
    public static String classifyGrade(int score) {
        return switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }
    
    public static void main(String[] args) {
        int[] scores = {95, 85, 75, 65, 55, 45};
        
        for (int score : scores) {
            String grade = classifyGrade(score);
            System.out.println("Score: " + score + " -> Grade: " + grade);
        }
    }
}
////////
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;
import java.nio.file.*;
import java.net.http.*;
import java.net.*;
import java.io.*;
import java.time.*;

// 8. Switch Expression to Classify Grades (Alternative)
class GradeClassifier2 {
    public static String classifyGrade(int score) {
        return switch (score) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> "A";
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> "B";
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> "C";
            case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> "D";
            default -> "F";
        };
    }
    
    public static void demo() {
        System.out.println("\n=== Grade Classifier 2 ===");
        int[] scores = {95, 85, 75, 65, 55};
        
        for (int score : scores) {
            String grade = classifyGrade(score);
            System.out.println("Score: " + score + " -> Grade: " + grade);
        }
    }
}

// 9. ATM Simulation with Exception Handling
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) { super(message); }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) { super(message); }
}

class ATM {
    private double balance;
    
    public ATM(double initialBalance) { this.balance = initialBalance; }
    
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive");
        if (amount > balance) throw new InsufficientFundsException("Insufficient funds. Available: " + balance);
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + ". Remaining balance: $" + balance);
    }
    
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive");
        balance += amount;
        System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
    }
    
    public double getBalance() { return balance; }
}

class ATMSimulation {
    public static void demo() {
        System.out.println("\n=== ATM Simulation ===");
        ATM atm = new ATM(1000);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit");
            System.out.print("Choose option: ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.println("Current balance: $" + atm.getBalance());
                        break;
                    case 4:
                        System.out.println("Thank you for using our ATM!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
            } catch (InsufficientFundsException | InvalidAmountException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

// 10. Regex to Extract Email IDs from Text
class EmailExtractor {
    public static List<String> extractEmails(String text) {
        List<String> emails = new ArrayList<>();
        String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            emails.add(matcher.group());
        }
        
        return emails;
    }
    
    public static void demo() {
        System.out.println("\n=== Email Extractor ===");
        String text = "Contact us at john.doe@example.com or support@company.com. " +
                     "For sales, email sales@company.org. Invalid emails: user@, @domain.com";
        
        List<String> emails = extractEmails(text);
        System.out.println("Emails found: " + emails);
    }
}

// 11. Cache using WeakHashMap
class WeakHashMapCache<K, V> {
    private final Map<K, V> cache = new WeakHashMap<>();
    
    public void put(K key, V value) { cache.put(key, value); }
    public V get(K key) { return cache.get(key); }
    public boolean containsKey(K key) { return cache.containsKey(key); }
    public int size() { return cache.size(); }
    
    public static void demo() {
        System.out.println("\n=== WeakHashMap Cache ===");
        WeakHashMapCache<String, String> cache = new WeakHashMapCache<>();
        
        String key1 = new String("key1");
        String key2 = new String("key2");
        
        cache.put(key1, "value1");
        cache.put(key2, "value2");
        
        System.out.println("Cache size: " + cache.size());
        System.out.println("key1: " + cache.get(key1));
        
        key1 = null;
        System.gc();
        
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        System.out.println("Cache size after GC: " + cache.size());
    }
}

// 12. Console Tic-Tac-Toe Game
class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }
    
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
        }
        
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
        
        return false;
    }
    
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return true;
    }
    
    public void switchPlayer() { currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; }
    public char getCurrentPlayer() { return currentPlayer; }
    
    public static void demo() {
        System.out.println("\n=== Tic-Tac-Toe Game ===");
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic-Tac-Toe!");
        
        while (true) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + "'s turn");
            System.out.print("Enter row (0-2) and column (0-2): ");
            
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                
                if (game.makeMove(row, col)) {
                    if (game.checkWin()) {
                        game.printBoard();
                        System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                        break;
                    } else if (game.isBoardFull()) {
                        game.printBoard();
                        System.out.println("It's a draw!");
                        break;
                    } else {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid numbers!");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}

// 13. Pattern Matching for instanceof
class PatternMatchingDemo {
    public static void processObject(Object obj) {
        if (obj instanceof String s) {
            System.out.println("It's a String: " + s.toUpperCase());
        } else if (obj instanceof Integer i && i > 0) {
            System.out.println("It's a positive Integer: " + (i * 2));
        } else if (obj instanceof List<?> list && !list.isEmpty()) {
            System.out.println("It's a non-empty List: " + list.size() + " elements");
        } else if (obj instanceof Double d) {
            System.out.println("It's a Double: " + (d / 2));
        } else {
            System.out.println("Unknown object type: " + obj.getClass().getSimpleName());
        }
    }
    
    public static void demo() {
        System.out.println("\n=== Pattern Matching ===");
        processObject("hello");
        processObject(42);
        processObject(Arrays.asList(1, 2, 3));
        processObject(3.14);
        processObject(-5);
        processObject(new ArrayList<>());
    }
}

// 14. Parse CSV into Records
record Person(String name, int age, String city) {
    public static Person fromCsv(String line) {
        String[] parts = line.split(",");
        return new Person(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }
}

class CsvParser {
    public static List<Person> parseCsv(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
            .skip(1)
            .map(Person::fromCsv)
            .collect(Collectors.toList());
    }
    
    public static void demo() {
        System.out.println("\n=== CSV Parser ===");
        try {
            String csvContent = "name,age,city\nJohn,25,New York\nAlice,30,London\nBob,22,Paris";
            Files.write(Paths.get("people.csv"), csvContent.getBytes());
            
            List<Person> people = parseCsv("people.csv");
            people.forEach(System.out::println);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// 15. Handle Null Values with Optional
class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public Optional<String> getName() { return Optional.ofNullable(name); }
    public Optional<String> getEmail() { return Optional.ofNullable(email); }
}

class OptionalDemo {
    public static void demo() {
        System.out.println("\n=== Optional Demo ===");
        User user1 = new User("John", "john@example.com");
        User user2 = new User(null, null);
        
        String name1 = user1.getName().orElse("Unknown");
        String email1 = user1.getEmail().orElse("No email");
        
        String name2 = user2.getName().orElse("Unknown");
        String email2 = user2.getEmail().orElse("No email");
        
        System.out.println("User 1: " + name1 + " - " + email1);
        System.out.println("User 2: " + name2 + " - " + email2);
        
        user1.getEmail().ifPresent(email -> System.out.println("Sending email to: " + email));
            
        user2.getEmail().ifPresentOrElse(
            email -> System.out.println("Sending email to: " + email),
            () -> System.out.println("No email available for user2")
        );
    }
}

// 16. Monitor Directory with WatchService
class DirectoryMonitor {
    public static void monitorDirectory(String directoryPath) throws IOException, InterruptedException {
        Path path = Paths.get(directoryPath);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, 
                     StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
        
        System.out.println("Monitoring directory: " + directoryPath);
        
        while (true) {
            WatchKey key = watchService.take();
            
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path fileName = (Path) event.context();
                
                System.out.println(kind.name() + ": " + fileName);
            }
            
            if (!key.reset()) break;
        }
    }
    
    public static void demo() {
        System.out.println("\n=== Directory Monitor ===");
        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            System.out.println("Monitoring temp directory: " + tempDir);
            // monitorDirectory(tempDir); // Uncomment to actually monitor
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 17. Deadlock Demonstration and Resolution
class DeadlockDemo {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2");
            }
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " acquired lock2");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1");
            }
        }
    }
    
    public void method1Resolved() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2");
            }
        }
    }
    
    public void method2Resolved() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2");
            }
        }
    }
    
    public static void demo() {
        System.out.println("\n=== Deadlock Demo ===");
        DeadlockDemo demo = new DeadlockDemo();
        
        Thread t1 = new Thread(() -> demo.method1(), "Thread-1");
        Thread t2 = new Thread(() -> demo.method2(), "Thread-2");
        
        System.out.println("Creating deadlock...");
        t1.start();
        t2.start();
        
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        
        System.out.println("\nUsing resolved methods...");
        Thread t3 = new Thread(() -> demo.method1Resolved(), "Thread-3");
        Thread t4 = new Thread(() -> demo.method2Resolved(), "Thread-4");
        
        t3.start();
        t4.start();
    }
}

// 18. LRU Cache using LinkedHashMap
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
    
    public static void demo() {
        System.out.println("\n=== LRU Cache ===");
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        
        System.out.println("Cache: " + cache);
        cache.get(1);
        System.out.println("After accessing 1: " + cache);
        cache.put(4, "Four");
        System.out.println("After adding 4: " + cache);
        cache.get(3);
        System.out.println("After accessing 3: " + cache);
        cache.put(5, "Five");
        System.out.println("After adding 5: " + cache);
    }
}

// 19. Fetch Data from REST API using HttpClient
class HttpClientDemo {
    public static void demo() {
        System.out.println("\n=== HTTP Client Demo ===");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// 20. Quicksort Recursive Implementation
class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void demo() {
        System.out.println("\n=== QuickSort ===");
        int[] arr = {64, 34, 25, 12, 22, 11, 90, 5};
        
        System.out.println("Original array: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

// 21. Balanced Parentheses Check using Stack
class BalancedParentheses {
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if ((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void demo() {
        System.out.println("\n=== Balanced Parentheses ===");
        String[] testCases = {"()", "()[]{}", "(]", "([)]", "{[]}", "((()))", "((())"};
        
        for (String test : testCases) {
            System.out.println(test + " : " + isBalanced(test));
        }
    }
}

// 22. Flatten List of Lists using Streams
class ListFlattener {
    public static void demo() {
        System.out.println("\n=== List Flattener ===");
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        
        List<Integer> flattenedList = listOfLists.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
            
        System.out.println("Original: " + listOfLists);
        System.out.println("Flattened: " + flattenedList);
    }
}

// 23. Word Auto-complete using Trie
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private final TrieNode root;
    
    public Trie() { root = new TrieNode(); }
    
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }
    
    public List<String> autoComplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = findNode(prefix);
        if (node != null) findAllWords(node, prefix, results);
        return results;
    }
    
    private TrieNode findNode(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) return null;
        }
        return current;
    }
    
    private void findAllWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) results.add(prefix);
        for (char ch : node.children.keySet()) {
            findAllWords(node.children.get(ch), prefix + ch, results);
        }
    }
}

class AutoComplete {
    public static void demo() {
        System.out.println("\n=== Auto Complete ===");
        Trie trie = new Trie();
        
        String[] words = {"apple", "application", "banana", "band", "bandage", "cat", "catch"};
        
        for (String word : words) trie.insert(word);
        
        System.out.println("Auto-complete for 'app': " + trie.autoComplete("app"));
        System.out.println("Auto-complete for 'ban': " + trie.autoComplete("ban"));
        System.out.println("Auto-complete for 'cat': " + trie.autoComplete("cat"));
    }
}

// 24. Hangman Game in Console
class Hangman {
    private static final String[] WORDS = {"java", "programming", "computer", "algorithm", "software"};
    private String word;
    private Set<Character> guessedLetters;
    private int attemptsLeft;
    
    public Hangman() {
        Random random = new Random();
        word = WORDS[random.nextInt(WORDS.length)];
        guessedLetters = new HashSet<>();
        attemptsLeft = 6;
    }
    
    public void play() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Hangman!");
        
        while (attemptsLeft > 0 && !isWordGuessed()) {
            displayGameState();
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter!");
                continue;
            }
            
            char guess = input.charAt(0);
            
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }
            
            guessedLetters.add(guess);
            
            if (word.indexOf(guess) == -1) {
                attemptsLeft--;
                System.out.println("Wrong guess! Attempts left: " + attemptsLeft);
            }
        }
        
        displayGameState();
        
        if (isWordGuessed()) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Game over! The word was: " + word);
        }
    }
    
    private boolean isWordGuessed() {
        for (char ch : word.toCharArray()) {
            if (!guessedLetters.contains(ch)) return false;
        }
        return true;
    }
    
    private void displayGameState() {
        System.out.println("\nWord: " + getMaskedWord());
        System.out.println("Guessed letters: " + guessedLetters);
        System.out.println("Attempts left: " + attemptsLeft);
    }
    
    private String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (char ch : word.toCharArray()) {
            masked.append(guessedLetters.contains(ch) ? ch : '_').append(' ');
        }
        return masked.toString();
    }
    
    public static void demo() {
        System.out.println("\n=== Hangman Game ===");
        Hangman game = new Hangman();
        game.play();
    }
}

// 25. Calculate Age from DOB using java.time
class AgeCalculator {
    public static void demo() {
        System.out.println("\n=== Age Calculator ===");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        String dobString = scanner.nextLine();
        
        try {
            LocalDate dob = LocalDate.parse(dobString, formatter);
            LocalDate now = LocalDate.now();
            
            Period age = Period.between(dob, now);
            System.out.println("Age: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days");
            
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD");
        }
        scanner.close();
    }
}

// 26. Sudoku Solver using Backtracking
class SudokuSolver {
    private static final int SIZE = 9;
    
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int c = 0; c < SIZE; c++) if (board[row][c] == num) return false;
        for (int r = 0; r < SIZE; r++) if (board[r][col] == num) return false;
        
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[boxRow + r][boxCol + c] == num) return false;
            }
        }
        
        return true;
    }
    
    public void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void demo() {
        System.out.println("\n=== Sudoku Solver ===");
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        SudokuSolver solver = new SudokuSolver();
        System.out.println("Original Sudoku:");
        solver.printBoard(board);
        
        if (solver.solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            solver.printBoard(board);
        } else {
            System.out.println("No solution exists!");
        }
    }
}

// 27. Observer Pattern for Stock Price Updates
interface StockObserver { void update(String stockSymbol, double price); }
interface StockSubject {
    void registerObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers();
}

class Stock implements StockSubject {
    private String symbol;
    private double price;
    private List<StockObserver> observers = new ArrayList<>();
    
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }
    
    @Override
    public void registerObserver(StockObserver observer) { observers.add(observer); }
    @Override
    public void removeObserver(StockObserver observer) { observers.remove(observer); }
    @Override
    public void notifyObservers() {
        for (StockObserver observer : observers) observer.update(symbol, price);
    }
}

class StockDisplay implements StockObserver {
    private String name;
    public StockDisplay(String name) { this.name = name; }
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println(name + " - " + stockSymbol + " price updated: $" + price);
    }
}

class ObserverPatternDemo {
    public static void demo() {
        System.out.println("\n=== Observer Pattern ===");
        Stock appleStock = new Stock("AAPL", 150.0);
        Stock googleStock = new Stock("GOOGL", 2800.0);
        
        StockDisplay display1 = new StockDisplay("Mobile App");
        StockDisplay display2 = new StockDisplay("Web Dashboard");
        StockDisplay display3 = new StockDisplay("Email Alert");
        
        appleStock.registerObserver(display1);
        appleStock.registerObserver(display2);
        googleStock.registerObserver(display2);
        googleStock.registerObserver(display3);
        
        appleStock.setPrice(152.5);
        googleStock.setPrice(2825.0);
        appleStock.setPrice(151.0);
    }
}

// 28. Group Employees by Department using Streams
record Employee(String name, String department, double salary) {}

class EmployeeGrouper {
    public static void demo() {
        System.out.println("\n=== Employee Grouper ===");
        List<Employee> employees = Arrays.asList(
            new Employee("John", "IT", 75000),
            new Employee("Alice", "HR", 65000),
            new Employee("Bob", "IT", 85000),
            new Employee("Eve", "Finance", 95000),
            new Employee("Charlie", "HR", 70000),
            new Employee("David", "Finance", 90000)
        );
        
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::department));
            
        System.out.println("Employees by department:");
        byDepartment.forEach((dept, empList) -> {
            System.out.println(dept + ": " + empList.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", ")));
        });
    }
}

// 29. Merge Two Sorted Arrays
class MergeSortedArrays {
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length) {
            result[k++] = (arr1[i] <= arr2[j]) ? arr1[i++] : arr2[j++];
        }
        
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        
        return result;
    }
    
    public static void demo() {
        System.out.println("\n=== Merge Sorted Arrays ===");
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        
        int[] merged = merge(arr1, arr2);
        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        System.out.println("Merged: " + Arrays.toString(merged));
    }
}

// 30. Binary to Decimal and Vice Versa Conversion
class BinaryDecimalConverter {
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;
        
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') decimal += Math.pow(2, power);
            power++;
        }
        
        return decimal;
    }
    
    public static String decimalToBinary(int decimal) {
        if (decimal == 0) return "0";
        StringBuilder binary = new StringBuilder();
        
        while (decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }
        
        return binary.toString();
    }
    
    public static void demo() {
        System.out.println("\n=== Binary Decimal Converter ===");
        String binary = "1010";
        int decimal = 10;
        
        System.out.println(binary + " (binary) = " + binaryToDecimal(binary) + " (decimal)");
        System.out.println(decimal + " (decimal) = " + decimalToBinary(decimal) + " (binary)");
    }
}

// 31. Validate Strong Passwords with Regex
class PasswordValidator {
    public static boolean isStrongPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(regex, password);
    }
    
    public static void demo() {
        System.out.println("\n=== Password Validator ===");
        String[] passwords = {
            "Password123!", "weak", "password", "PASSWORD123", "Pass123", "StrongPass1!"
        };
        
        for (String password : passwords) {
            boolean isValid = isStrongPassword(password);
            System.out.println(password + " : " + (isValid ? "Strong" : "Weak"));
        }
    }
}

// 32. BFS & DFS Traversal in a Graph
class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    
    public Graph() { adjacencyList = new HashMap<>(); }
    
    public void addEdge(int source, int destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
    }
    
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);
        
        System.out.print("BFS: ");
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            
            for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
    
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsRecursive(start, visited);
        System.out.println();
    }
    
    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");
        
        for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) dfsRecursive(neighbor, visited);
        }
    }
    
    public static void demo() {
        System.out.println("\n=== Graph Traversal ===");
        Graph graph = new Graph();
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        
        graph.bfs(0);
        graph.dfs(0);
    }
}

// 33. Run Async Tasks with CompletableFuture
class CompletableFutureDemo {
    public static CompletableFuture<String> asyncTask1() {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            return "Task 1 completed";
        });
    }
    
    public static CompletableFuture<String> asyncTask2() {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            return "Task 2 completed";
        });
    }
    
    public static CompletableFuture<String> asyncTask3() {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            return "Task 3 completed";
        });
    }
    
    public static void demo() {
        System.out.println("\n=== Completable Future ===");
        
        // Run in parallel
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
            asyncTask1().thenAccept(System.out::println),
            asyncTask2().thenAccept(System.out::println),
            asyncTask3().thenAccept(System.out::println)
        );
        allTasks.join();
    }
}

// Main class to run all demos
public class JavaSEDemos {
    public static void main(String[] args) {
        // Run all demonstrations
        GradeClassifier2.demo();
        // ATMSimulation.demo(); // Interactive - uncomment to run
        EmailExtractor.demo();
        WeakHashMapCache.demo();
        // TicTacToe.demo(); // Interactive - uncomment to run
        PatternMatchingDemo.demo();
        CsvParser.demo();
        OptionalDemo.demo();
        DirectoryMonitor.demo();
        DeadlockDemo.demo();
        LRUCache.demo();
        HttpClientDemo.demo();
        QuickSort.demo();
        BalancedParentheses.demo();
        ListFlattener.demo();
        AutoComplete.demo();
        // Hangman.demo(); // Interactive - uncomment to run
        // AgeCalculator.demo(); // Interactive - uncomment to run
        SudokuSolver.demo();
        ObserverPatternDemo.demo();
        EmployeeGrouper.demo();
        MergeSortedArrays.demo();
        BinaryDecimalConverter.demo();
        PasswordValidator.demo();
        Graph.demo();
        CompletableFutureDemo.demo();
        
        System.out.println("\n=== All demos completed ===");
    }
}