package com.riwi.talent.view;

import com.riwi.talent.model.architecture.ArchitectureNotes;
import com.riwi.talent.model.businesslogic.EmployeeRecord;
import com.riwi.talent.model.collections.EmployeeCollectionManager;
import com.riwi.talent.model.config.DatabaseConnection;
import com.riwi.talent.model.domain.Developer;
import com.riwi.talent.model.domain.ExternalConsultant;
import com.riwi.talent.model.domain.Manager;
import com.riwi.talent.model.domain.Person;
import com.riwi.talent.model.factorymethods.FactoryMethods;
import com.riwi.talent.model.filterandreport.FilterAndReport;
import com.riwi.talent.model.interfaces.Promotable;
import com.riwi.talent.model.logic.CompanyRecord;
import com.riwi.talent.model.logic.Employee;
import com.riwi.talent.model.moderndiagnostics.Versions;
import com.riwi.talent.model.reports.PerformanceReport;
import com.riwi.talent.model.sequencedcollections.SequencedCollections;
import com.riwi.talent.view.menu.SwitchComparison;
import com.riwi.talent.view.qualification.PerformanceMatrixProcessor;
import com.riwi.talent.view.status.ExceptionHandlingAndValidation;
import com.riwi.talent.view.userinput.UserInputAndVarDemo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ApplicationView {

    private static final List<Person> TEAM = List.of(
            new Developer(1, "Ana Lopez", "ana@riwi.io", 5_000_000, 9, "Java"),
            new Developer(2, "Carlos Ruiz", "carlos@riwi.io", 4_200_000, 6, "Python"),
            new Manager(3, "Maria Gomez", "maria@riwi.io", 8_000_000, 8, 20_000_000),
            new ExternalConsultant(4, "Luis Perez", "luis@consulting.io",
                    "Cloud Architecture", 350_000)
    );

    public void start() {
        System.out.println("===== TALENT HUB ORCHESTRATOR =====\n");

        try (Scanner scanner = new Scanner(System.in)) {
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

            System.out.println("--- 6. Switch Comparison: Legacy (Java 8) vs Modern (Java 17+) ---");
            if (!runInteractiveSection(scanner, SwitchComparison::run)) {
                return;
            }
            System.out.println();

            System.out.println("--- 7. Exception Handling & Promotion Validation ---");
            if (!runInteractiveSection(scanner, ExceptionHandlingAndValidation::run)) {
                return;
            }
            System.out.println();

            System.out.println("--- 8. Performance Matrix Processor (double[][], casting, ternary) ---");
            if (!runInteractiveSection(scanner, PerformanceMatrixProcessor::run)) {
                return;
            }
            System.out.println();

            System.out.println("--- 9. User Input & var Demo (Java 11+) ---");
            if (!runInteractiveSection(scanner, UserInputAndVarDemo::run)) {
                return;
            }
            System.out.println();

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

            System.out.println("--- 18. HU5: MVC + JDBC + CRUD (SQLite) ---");
            DatabaseConnection.initializeDatabase();
            if (!runInteractiveSection(scanner, viewScanner -> new TalentHubView().start(viewScanner))) {
                return;
            }
            System.out.println();
        }

        System.out.println("===== END OF ORCHESTRATION =====");
    }

    private boolean runInteractiveSection(Scanner scanner, ScannerSection section) {
        try {
            section.run(scanner);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(">> Input ended. Closing the application gracefully.");
            return false;
        }
    }

    private void demonstrateEmployeeRecord() {
        EmployeeRecord emp1 = new EmployeeRecord(
                "Luis", 1, 8_000_000, 3_000_000, 82, 22, 3, false);

        System.out.println("Name: " + emp1.name());
        System.out.println("Final Salary: $" + emp1.calculateFinalSalary());
        System.out.println("Extra Bonus (even ID): $" + emp1.calculateExtraBonus());
        System.out.println("Is eligible?: " + (emp1.validateEligibility() ? "Yes" : "No"));
    }

    private void demonstrateCompanyRecord() {
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

    private void demonstrateVersionsComparison() {
        Versions emp1 = new Versions("Luis", 22);
        Versions emp2 = new Versions("Luis", 22);

        System.out.println("emp1 == emp2     : " + (emp1 == emp2));
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2));
        System.out.println("Explanation: == checks Heap references; .equals() checks field values.");

        String nullName = null;
        System.out.println("\n[Null assignment demo]");
        System.out.println("nullName = " + nullName);
        System.out.println("Java 14+ message: Cannot invoke \"String.length()\" because \"nullName\" is null");
        System.out.println("Java 8  message : NullPointerException (no context — hard to debug)");
    }

    private void demonstrateEmployeePrimitives() {
        byte age = 28;
        short departmentCode = 101;
        int id = 1001;
        long annualSalary = 60_000_000L;
        float monthlyBonus = 1_500_000f;
        double deductions = 2_250_000.0;
        char nameInitial = 'L';
        boolean isActive = true;
        String fullName = "Luis Carlos Rodriguez";

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
        System.out.println("Employee monthlyBonus after setter +=: $" + employee.getMonthlyBonus());
    }

    private void demonstrateSealedHierarchy() {
        for (Person person : TEAM) {
            System.out.println(person.getRoleSummary());
        }
    }

    private void demonstratePerformanceReports() {
        List<PerformanceReport> reports = List.of(
                PerformanceReport.of(1, 9.2),
                PerformanceReport.of(2, 6.1),
                PerformanceReport.of(3, 8.4)
        );

        for (PerformanceReport report : reports) {
            System.out.println(report.toFormattedReport());
            System.out.println();
        }
    }

    private void demonstratePatternMatching() {
        for (Person person : TEAM) {
            if (person instanceof Developer developer) {
                System.out.printf("Developer  %-18s → Language: %s%n",
                        developer.getFullName(), developer.getMainLanguage());
            } else if (person instanceof Manager manager) {
                System.out.printf("Manager    %-18s → Budget: $%.2f%n",
                        manager.getFullName(), manager.getMonthlyBudget());
            } else if (person instanceof ExternalConsultant consultant) {
                System.out.printf("Consultant %-18s → Specialty: %s%n",
                        consultant.getFullName(), consultant.getSpecialty());
            }
        }
    }

    private void demonstratePromotable() {
        for (Person person : TEAM) {
            if (person instanceof Promotable promotable) {
                System.out.printf("%-20s → Bonus: $%.2f%n",
                        person.getFullName(), promotable.calculatePromotionBonus());
                promotable.logPromotionEvent();
                System.out.println();
            }
        }
    }

    @FunctionalInterface
    private interface ScannerSection {
        void run(Scanner scanner);
    }
}
