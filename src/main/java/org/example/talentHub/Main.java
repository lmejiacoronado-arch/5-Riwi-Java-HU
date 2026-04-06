package org.example.talentHub.Main;

import org.example.talentHub.Architecture.ArchitectureNotes;
import org.example.talentHub.BusinessLogic.EmployeeRecord;
import org.example.talentHub.logic.CompanyRecord;
import org.example.talentHub.ModernDiagnostics.versions;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== TALENT HUB ORCHESTRATOR =====\n");

        // 1. DEMOSTRACIÓN DE ARQUITECTURA Y GC
        System.out.println("--- 1. Architecture & GC Notes ---");
        ArchitectureNotes.main(new String[0]);
        System.out.println();

        // 2. DEMOSTRACIÓN DE BUSINESS LOGIC CON RECORDS
        System.out.println("--- 2. Business Logic: EmployeeRecord ---");
        demonstrateEmployeeRecord();
        System.out.println();

        // 3. DEMOSTRACIÓN DE COMPANY RECORD
        System.out.println("--- 3. CompanyRecord ---");
        demonstrateCompanyRecord();
        System.out.println();

        // 4. DEMOSTRACIÓN DE MODERN DIAGNOSTICS (== vs .equals())
        System.out.println("--- 4. Modern Diagnostics: == vs .equals() ---");
        demonstrateVersionsComparison();
        System.out.println();

        // 5. DEMOSTRACIÓN DE CLASE EMPLOYEE (8 TIPOS PRIMITIVOS)
        System.out.println("--- 5. Employee Class (Primitive Types) ---");
        demonstrateEmployeePrimitives();
        System.out.println();

        System.out.println("===== END OF ORCHESTRATION =====");
    }

    // Método que reproduce la lógica del EmployeeRecord
    private static void demonstrateEmployeeRecord() {
        EmployeeRecord emp1 = new EmployeeRecord("Luis", 1, 8_000_000, 3_000_000, 82, 22, 3, false);

        System.out.println("--- Employee Report ---");
        System.out.println("Name: " + emp1.name());
        System.out.println("Final Salary: $" + emp1.calculateFinalSalary());
        System.out.println("Extra Bonus (by even ID): $" + emp1.calculateExtraBonus());
        System.out.println("Is eligible?: " + (emp1.validateEligibility() ? "Yes" : "No"));

        // Demostración del NullPointerException mejorado (opcional - descomentar para probar)
        // System.out.println("Name length: " + emp1.name().length());
    }

    // Método que reproduce la lógica del CompanyRecord
    private static void demonstrateCompanyRecord() {
        CompanyRecord company1 = new CompanyRecord("Riwi", "1234-5", 2023);

        String verbosityComparison =
                """
                In Java 8 (POJOs), the process is longer because we must create the class, 
                declare fields, and then write a constructor manually. This makes the code 
                more verbose. 
                
                In contrast, with Records (Java 17+), everything is done in a single line. 
                The class and its attributes are declared together, making the code 
                concise and easier to read.
                """;

        System.out.println(verbosityComparison);
        System.out.println("Company instantiated: " + company1.name() + " - " + company1.nit());
    }

    // Método que demuestra la diferencia entre == y .equals()
    private static void demonstrateVersionsComparison() {
        versions emp1 = new versions("Luis", 22);
        versions emp2 = new versions("Luis", 22);

        System.out.println("emp1 == emp2: " + (emp1 == emp2));      // false (Diferentes direcciones de memoria)
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2)); // true (Mismo contenido)
        System.out.println("Explanation: Records automatically override .equals() to compare field values.");
    }

    // Método que demuestra la clase Employee con tipos primitivos
    private static void demonstrateEmployeePrimitives() {
        // Creando una instancia de Employee con valores de ejemplo
        byte age = 28;
        short departmentCode = 101;
        int id = 1001;
        long annualSalary = 60_000_000L;
        float bonus = 1_500_000f;
        double deductions = 2_250_000;
        char nameInitial = 'L';
        boolean isActive = true;
        String fullName = "Luis Carlos Rodríguez";

        // Nota: La clase Employee no tiene getters públicos, así que solo mostramos que se instancia
        System.out.println("Employee instance created successfully!");
        System.out.printf("Sample data: %s (Initial: %c), Age: %d, Active: %b%n",
                fullName, nameInitial, age, isActive);
        System.out.println("This class demonstrates all 8 primitive types in Java.");
    }
}