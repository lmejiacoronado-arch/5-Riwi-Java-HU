package org.example.talentHub;

// HU1
import org.example.talentHub.Architecture.ArchitectureNotes;
import org.example.talentHub.BusinessLogic.EmployeeRecord;
import org.example.talentHub.ModernDiagnostics.Versions;
import org.example.talentHub.logic.CompanyRecord;
import org.example.talentHub.logic.Employee;

// HU2
import org.example.talentHub.Menu.SwitchComparison;
import org.example.talentHub.Status.ExceptionHandlingAndValidation;
import org.example.talentHub.Qualification.PerformanceMatrixProcessor;
import org.example.talentHub.UserInput.UserInputAndVarDemo;

// HU3
import org.example.talentHub.Collections.EmployeeCollectionManager;
import org.example.talentHub.FactoryMethods.FactoryMethods;
import org.example.talentHub.SequencedCollections.SequencedCollections;
import org.example.talentHub.FilterAndReport.FilterAndReport;

// HU4
import org.example.talentHub.Model.Person;
import org.example.talentHub.Model.Developer;
import org.example.talentHub.Model.Manager;
import org.example.talentHub.Model.ExternalConsultant;
import org.example.talentHub.Interfaces.Promotable;
import org.example.talentHub.Reports.PerformanceReport;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TALENT HUB ORCHESTRATOR =====\n");

        Scanner scanner = new Scanner(System.in);

        // ── HU1 ───────────────────────────────────────────────────────────

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

        // ── HU2 ───────────────────────────────────────────────────────────

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

        // ── HU3 ───────────────────────────────────────────────────────────

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

        // ── HU4 ───────────────────────────────────────────────────────────

        System.out.println("--- 14. Sealed Hierarchy & Polymorphism ---");
        demonstrateSealedHierarchy();
        System.out.println();

        System.out.println("--- 15. Immutable PerformanceReport Records ---");
        demonstratePerformanceReports();
        System.out.println();

        System.out.println("--- 16. Pattern Matching for instanceof ---");
        demonstratePatternMatching();
        System.out.println();

        System.out.println("--- 17. Promotable Interface (abstract + default method) ---");
        demonstratePromotable();
        System.out.println();

        scanner.close();
        System.out.println("===== END OF ORCHESTRATION =====");
    }

    // ── HU1 helpers ───────────────────────────────────────────────────

    private static void demonstrateEmployeeRecord() {
        EmployeeRecord emp1 = new EmployeeRecord(
                "Luis", 1, 8_000_000, 3_000_000, 82, 22, 3, false);

        System.out.println("Name: "                   + emp1.name());
        System.out.println("Final Salary: $"          + emp1.calculateFinalSalary());
        System.out.println("Extra Bonus (even ID): $" + emp1.calculateExtraBonus());
        System.out.println("Is eligible?: "
                + (emp1.validateEligibility() ? "Yes" : "No"));
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

        System.out.println("emp1 == emp2     : " + (emp1 == emp2));
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2));
        System.out.println(
                "Explanation: == checks Heap references; .equals() checks field values.");

        String nullName = null;
        System.out.println("\n[Null assignment demo]");
        System.out.println("nullName = " + nullName);
        System.out.println(
                "Java 14+ message: Cannot invoke \"String.length()\" because \"nullName\" is null");
        System.out.println(
                "Java 8  message : NullPointerException (no context — hard to debug)");
        // System.out.println(nullName.length()); // triggers Helpful NPE
    }

    private static void demonstrateEmployeePrimitives() {
        byte    age            = 28;
        short   departmentCode = 101;
        int     id             = 1001;
        long    annualSalary   = 60_000_000L;
        float   monthlyBonus   = 1_500_000f;
        double  deductions     = 2_250_000.0;
        char    nameInitial    = 'L';
        boolean isActive       = true;
        String  fullName       = "Luis Carlos Rodriguez";

        System.out.println("All 8 primitive types declared successfully.");
        System.out.printf("Employee: %s (%c) | Age: %d | Active: %b%n",
                fullName, nameInitial, age, isActive);
        System.out.printf("Salary: %d | Bonus: %.2f | Deductions: %.2f%n",
                annualSalary, monthlyBonus, deductions);

        monthlyBonus += 500_000f;
        System.out.println("Monthly bonus after += 500,000: $" + monthlyBonus);

        Employee employee = new Employee(
                age, departmentCode, id, annualSalary,
                monthlyBonus, deductions, nameInitial, isActive, fullName);
        employee.setMonthlyBonus(employee.getMonthlyBonus() + 200_000f);
        System.out.println(
                "Employee monthlyBonus after setter +=: $" + employee.getMonthlyBonus());
    }

    // ── HU4 helpers ───────────────────────────────────────────────────

    // Lista compartida entre los 4 métodos de HU4
    private static final List<Person> TEAM = List.of(
            new Developer(1, "Ana Lopez",   "ana@riwi.io",       5_000_000, 9, "Java"),
            new Developer(2, "Carlos Ruiz",  "carlos@riwi.io",    4_200_000, 6, "Python"),
            new Manager  (3, "Maria Gomez",  "maria@riwi.io",     8_000_000, 8, 20_000_000),
            new ExternalConsultant(4, "Luis Perez", "luis@consulting.io",
                    "Cloud Architecture", 350_000)
    );

    // Task 1 — Sealed hierarchy + polymorphism
    private static void demonstrateSealedHierarchy() {
        for (Person p : TEAM) {
            System.out.println(p.getRoleSummary());
        }
    }

    // Task 2 — Immutable records
    private static void demonstratePerformanceReports() {
        List<PerformanceReport> reports = List.of(
                PerformanceReport.of(1, 9.2),
                PerformanceReport.of(2, 6.1),
                PerformanceReport.of(3, 8.4)
        );
        for (PerformanceReport r : reports) {
            System.out.println(r.toFormattedReport());
            System.out.println();
        }
    }

    // Task 3 — Pattern Matching for instanceof
    private static void demonstratePatternMatching() {
        for (Person p : TEAM) {

            // LEGACY (Java 8): instanceof + manual cast — error-prone
            // if (p instanceof Developer) {
            //     Developer dev = (Developer) p;
            //     System.out.println(dev.getMainLanguage());
            // }

            // MODERN (Java 16+): Pattern Matching — declare + cast in one expression
            if (p instanceof Developer dev) {
                System.out.printf("Developer  %-18s → Language: %s%n",
                        dev.getFullName(), dev.getMainLanguage());
            } else if (p instanceof Manager mgr) {
                System.out.printf("Manager    %-18s → Budget: $%.2f%n",
                        mgr.getFullName(), mgr.getMonthlyBudget());
            } else if (p instanceof ExternalConsultant ext) {
                System.out.printf("Consultant %-18s → Specialty: %s%n",
                        ext.getFullName(), ext.getSpecialty());
            }
        }
    }

    // Task 4 — Promotable: abstract method + default method (Java 8+)
    private static void demonstratePromotable() {
        for (Person p : TEAM) {
            if (p instanceof Promotable promotable) {
                System.out.printf("%-20s → Bonus: $%.2f%n",
                        p.getFullName(), promotable.calculatePromotionBonus());
                promotable.logPromotionEvent(); // default method — inherited for free
                System.out.println();
            }
        }
    }
}