package org.example.talentHub;

import org.example.talentHub.Architecture.ArchitectureNotes;
import org.example.talentHub.BusinessLogic.EmployeeRecord;
import org.example.talentHub.Collections.EmployeeCollectionManager;
import org.example.talentHub.FactoryMethods.FactoryMethods;
import org.example.talentHub.FilterAndReport.FilterAndReport;
import org.example.talentHub.Menu.SwitchComparison;
import org.example.talentHub.ModernDiagnostics.Versions;
import org.example.talentHub.Qualification.PerformanceMatrixProcessor;
import org.example.talentHub.SequencedCollections.SequencedCollections;
import org.example.talentHub.Status.ExceptionHandlingAndValidation;
import org.example.talentHub.UserInput.UserInputAndVarDemo;
import org.example.talentHub.logic.CompanyRecord;
import org.example.talentHub.logic.Employee;

import java.util.Scanner;

/**
 * Main Orchestrator — Corporate Talent Hub
 *
 * Runs all modules from HU1, HU2, and HU3 in sequence.
 * A single shared Scanner is created here and passed to every module
 * that requires user input. It is closed only at the very end.
 *
 * IMPORTANT: Never create a Scanner(System.in) inside a module and close it,
 * because closing System.in permanently prevents any subsequent reads.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("===== TALENT HUB ORCHESTRATOR =====\n");

        // Single shared Scanner — passed to every module that needs input
        Scanner scanner = new Scanner(System.in);

        // ─────────────────────────────────────────────
        // HU1 — Architecture, Data Modeling, Business Logic
        // ─────────────────────────────────────────────

        System.out.println("--- 1. Architecture & GC Notes ---");
        ArchitectureNotes.run();
        System.out.println();

        System.out.println("--- 2. Business Logic: EmployeeRecord (Record + methods) ---");
        demonstrateEmployeeRecord();
        System.out.println();

        System.out.println("--- 3. CompanyRecord (Java 17 Record vs Java 8 POJO) ---");
        demonstrateCompanyRecord();
        System.out.println();

        System.out.println("--- 4. Modern Diagnostics: == vs .equals() on Heap objects ---");
        demonstrateVersionsComparison();
        System.out.println();

        System.out.println("--- 5. Employee Class — All 8 Primitive Types + += operator ---");
        demonstrateEmployeePrimitives();
        System.out.println();

        // ─────────────────────────────────────────────
        // HU2 — Control Flow, Input, Matrices, Exceptions
        // ─────────────────────────────────────────────

        System.out.println("--- 6. Switch Comparison: Legacy (Java 8) vs Modern (Java 17+) ---");
        SwitchComparison.run(scanner);
        System.out.println();

        System.out.println("--- 7. Exception Handling & Promotion Validation ---");
        ExceptionHandlingAndValidation.run(scanner);
        System.out.println();

        System.out.println("--- 8. Performance Matrix Processor (double[][], casting, ternary) ---");
        PerformanceMatrixProcessor.run(scanner);
        System.out.println();

        System.out.println("--- 9. User Input & var Demo (Java 11+) ---");
        UserInputAndVarDemo.run(scanner);
        System.out.println();

        // ─────────────────────────────────────────────
        // HU3 — Collections Framework (Java 8 → Java 21)
        // ─────────────────────────────────────────────

        System.out.println("--- 10. ArrayList & HashMap — Dynamic Employee Management ---");
        EmployeeCollectionManager.run();
        System.out.println();

        System.out.println("--- 11. Factory Methods: List.of / Map.of (Java 9/11) ---");
        FactoryMethods.run();
        System.out.println();

        System.out.println("--- 12. Sequenced Collections: getFirst / getLast / reversed (Java 21) ---");
        SequencedCollections.run();
        System.out.println();

        System.out.println("--- 13. Filter with removeIf and var — Final Report ---");
        FilterAndReport.run();
        System.out.println();

        // Close the shared scanner only at the very end
        scanner.close();

        System.out.println("===== END OF ORCHESTRATION =====");
    }

    // ─────────────────────────────────────────────
    // HU1 helper methods
    // ─────────────────────────────────────────────

    private static void demonstrateEmployeeRecord() {
        EmployeeRecord emp1 = new EmployeeRecord("Luis", 1, 8_000_000, 3_000_000, 82, 22, 3, false);

        System.out.println("Name: "                   + emp1.name());
        System.out.println("Final Salary: $"          + emp1.calculateFinalSalary());
        System.out.println("Extra Bonus (even ID): $" + emp1.calculateExtraBonus());
        System.out.println("Is eligible?: "           + (emp1.validateEligibility() ? "Yes" : "No"));
    }

    private static void demonstrateCompanyRecord() {
        CompanyRecord company = new CompanyRecord("Riwi", "1234-5", 2023);

        String verbosityComparison = """
                Java 8 (POJO): requires declaring class, fields, constructor,
                getters, setters, and toString manually — very verbose.
                
                Java 17+ (Record): everything in one line. Fields, constructor,
                getters, equals, hashCode, and toString are auto-generated.
                Records are also IMMUTABLE by design — no setters allowed.
                """;

        System.out.println(verbosityComparison);
        System.out.println("Company: " + company.name() + " | NIT: " + company.nit());
    }

    private static void demonstrateVersionsComparison() {
        Versions emp1 = new Versions("Luis", 22);
        Versions emp2 = new Versions("Luis", 22);

        // == compares REFERENCES in the Heap (memory addresses), not values.
        // Two different objects in Heap → different addresses → == returns false.
        System.out.println("emp1 == emp2     : " + (emp1 == emp2));

        // Records auto-override .equals() to compare FIELD VALUES, not references.
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2));
        System.out.println("Explanation: == checks Heap references; .equals() checks field values.");

        // Task 4 - HU1: Helpful NullPointerException demo (Java 14+)
        String nullName = null;
        System.out.println("\n[Null assignment demo]");
        System.out.println("nullName = " + nullName);
        System.out.println("Uncomment the next line to trigger a Helpful NPE (Java 14+):");
        System.out.println("  // nullName.length()");
        System.out.println("Java 14+ message: Cannot invoke \"String.length()\" because \"nullName\" is null");
        System.out.println("Java 8  message : NullPointerException (no context — hard to debug)");
        // System.out.println(nullName.length()); // triggers Helpful NPE
    }

    private static void demonstrateEmployeePrimitives() {
        // All 8 primitive types with correct suffixes
        byte   age            = 28;
        short  departmentCode = 101;
        int    id             = 1001;
        long   annualSalary   = 60_000_000L;   // suffix L required
        float  monthlyBonus   = 1_500_000f;    // suffix f required
        double deductions      = 2_250_000.0;
        char   nameInitial    = 'L';
        boolean isActive      = true;
        String fullName       = "Luis Carlos Rodriguez";

        System.out.println("All 8 primitive types declared successfully.");
        System.out.printf("Employee: %s (%c) | Age: %d | Active: %b%n",
                fullName, nameInitial, age, isActive);
        System.out.printf("Salary: %d | Bonus: %.2f | Deductions: %.2f%n",
                annualSalary, monthlyBonus, deductions);

        // Task 3 - HU1: += compound assignment operator on monthlyBonus
        // This demonstrates the += operator as required by the HU.
        // In the Employee class (mutable), we can also use: employee.setMonthlyBonus(employee.getMonthlyBonus() + 500_000f)
        monthlyBonus += 500_000f;
        System.out.println("Monthly bonus after += 500,000: $" + monthlyBonus);

        // Demonstrating with the Employee class (mutable object with setter)
        Employee employee = new Employee(
                age, departmentCode, id, annualSalary,
                monthlyBonus, deductions, nameInitial, isActive, fullName
        );
    }
}
