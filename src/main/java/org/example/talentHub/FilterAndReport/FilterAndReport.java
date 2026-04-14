package org.example.talentHub.FilterAndReport;

import org.example.talentHub.Collections.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Task 4 - HU3: Advanced filtering with removeIf and type inference with var.
 *
 * removeIf(lambda):
 *   Removes all elements from the collection that satisfy the given condition.
 *   More concise than iterating with an iterator and calling remove() manually.
 *
 * TYPE INFERENCE COMPARISON:
 *   Java 8  → double salarySum = 0.0;
 *             for (Employee emp : employees) { ... }
 *   Java 11+ → var salarySum = 0.0;           (inferred as double)
 *              for (var emp : employees) { ... } (inferred as Employee)
 *
 * 'var' reduces verbosity without losing type safety — the type is still
 * resolved at compile time, not at runtime.
 */
public class FilterAndReport {

    public static void run() {
        System.out.println("\n=== TASK 4: FILTER WITH removeIf AND var ===\n");

        // Build employee list with sample data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E001", "Ana Lopez",     3_500_000, 85));
        employees.add(new Employee("E002", "Carlos Ruiz",   2_800_000, 45));
        employees.add(new Employee("E003", "Maria Gomez",   4_200_000, 92));
        employees.add(new Employee("E004", "Luis Perez",    3_100_000, 38));
        employees.add(new Employee("E005", "Sofia Ramirez", 3_900_000, 60));

        System.out.println("--- ORIGINAL LIST ---");
        showEmployees(employees);

        // removeIf — removes employees who do not meet the minimum score
        int minScore = 70;
        System.out.println("\n--- FILTERING WITH removeIf ---");
        System.out.println("Removing employees with score < " + minScore);
        employees.removeIf(emp -> emp.getScore() < minScore);
        showEmployees(employees);

        // var for type inference (Java 11+)
        System.out.println("\n--- TYPE INFERENCE WITH var (Java 11+) ---");

        // Java 8: double salarySum = 0.0;
        var salarySum = 0.0; // inferred as double

        // Java 8: for (Employee emp : employees)
        for (var emp : employees) { // inferred as Employee
            salarySum += emp.getSalary();
        }

        var salaryAverage = employees.isEmpty() ? 0 : salarySum / employees.size();

        System.out.println("Total employees : " + employees.size());
        System.out.println("Total salaries  : $" + salarySum);
        System.out.println("Average salary  : $" + salaryAverage);

        System.out.println("\n--- var COMPARISON ---");
        System.out.println("JAVA 8  (explicit) : double sum = 0.0; for (Employee emp : list) {}");
        System.out.println("JAVA 11+ (var)     : var sum = 0.0;    for (var emp : list) {}");

        // Final report
        System.out.println("\n--- FINAL REPORT ---");
        generateReport(employees, salaryAverage);
    }

    private static void showEmployees(List<Employee> employees) {
        System.out.println("Total: " + employees.size());
        for (var emp : employees) {
            System.out.println("  " + emp);
        }
    }

    private static void generateReport(List<Employee> employees, double average) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║         TALENT HUB REPORT                 ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║ Active employees   : %-20d ║%n", employees.size());
        System.out.printf("║ Average salary     : $%-17.2f ║%n", average);
        System.out.println("║                                            ║");
        System.out.println("║ Employees meeting minimum score:           ║");
        for (var emp : employees) {
            System.out.printf("║   • %-15s (score: %d)          ║%n",
                    emp.getName(), emp.getScore());
        }
        System.out.println("╚════════════════════════════════════════════╝");
    }
}
