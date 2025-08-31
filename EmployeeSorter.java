import java.util.*;
import java.util.stream.*;

record Employee(String name, String department, double salary) {}

public class EmployeeSorter {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("John", "IT", 75000),
            new Employee("Alice", "HR", 65000),
            new Employee("Bob", "IT", 85000),
            new Employee("Eve", "Finance", 95000)
        );
        
        List<Employee> sortedEmployees = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .collect(Collectors.toList());
            
        sortedEmployees.forEach(e -> 
            System.out.println(e.name() + " - " + e.department() + " - $" + e.salary()));
    }
}
