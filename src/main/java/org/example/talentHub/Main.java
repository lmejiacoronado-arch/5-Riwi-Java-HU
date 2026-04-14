package org.example.talentHub;

import org.example.talentHub.Architecture.ArchitectureNotes;
import org.example.talentHub.BusinessLogic.EmployeeRecord;
import org.example.talentHub.Menu.SwitchComparison;
import org.example.talentHub.ModernDiagnostics.Versions;
import org.example.talentHub.Qualification.PerformanceMatrixProcessor;
import org.example.talentHub.Status.ExceptionHandlingAndValidation;
import org.example.talentHub.UserInput.UserInputAndVarDemo;
import org.example.talentHub.logic.CompanyRecord;
import org.example.talentHub.logic.Employee;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TALENT HUB ORCHESTRATOR =====\n");

        // Single shared Scanner — never close it inside a module
        Scanner scanner = new Scanner(System.in);

        // ── HU1 ───────────────────────────────────────

        System.out.println("--- 1. Architecture & GC Notes ---");
        ArchitectureNotes.run();
        System.out.println();

        System.out.println("--- 2. Business Logic: EmployeeRecord ---");
        demonstrateEmployeeRecord();
        System.out.println();

        System.out.println("--- 3. CompanyRecord (Record vs POJO) ---");
        demonstrateCompanyRecord();
        System.out.println();

        System.out.println("--- 4. Modern Diagnostics: == vs .equals() ---");
        demonstrateVersionsComparison();
        System.out.println();

        System.out.println("--- 5. Employee — All 8 Primitive Types + += ---");
        demonstrateEmployeePrimitives();
        System.out.println();

        // ── HU2 ───────────────────────────────────────

        System.out.println("--- 6. Switch: Legacy (Java 8) vs Modern (Java 17+) ---");
        SwitchComparison.run(scanner);
        System.out.println();

        System.out.println("--- 7. Exception Handling & Promotion Validation ---");
        ExceptionHandlingAndValidation.run(scanner);
        System.out.println();

        System.out.println("--- 8. Performance Matrix (double[][], casting, ternary) ---");
        PerformanceMatrixProcessor.run(scanner);
        System.out.println();

        System.out.println("--- 9. User Input & var Demo (Java 11+) ---");
        UserInputAndVarDemo.run(scanner);
        System.out.println();

        scanner.close();
        System.out.println("===== END OF ORCHESTRATION =====");
    }

    // ── Helpers HU1 ───────────────────────────────

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
        System.out.println("Company: " + company.name()
                + " | NIT: " + company.nit());
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
                "Java 14+ NPE: Cannot invoke \"String.length()\" because \"nullName\" is null");
        System.out.println(
                "Java 8  NPE : NullPointerException (no context — hard to debug)");
        // System.out.println(nullName.length()); // uncomment to trigger Helpful NPE
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
        System.out.printf(
                "Employee: %s (%c) | Age: %d | Active: %b%n",
                fullName, nameInitial, age, isActive);

        monthlyBonus += 500_000f;
        System.out.println("Monthly bonus after +=: $" + monthlyBonus);

        Employee employee = new Employee(
                age, departmentCode, id, annualSalary,
                monthlyBonus, deductions, nameInitial, isActive, fullName);
    }
}