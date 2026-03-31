package org.example.talentHub.logic;

/**
 * Task 2: Data Modeling for Corporate Talent Hub.
 * Esta clase usa los 8 tipos primitivos de Java y un objeto String.
 */
public class Employee {
    // 1. ATRIBUTOS (FIELDS) - Fuera del main para que pertenezcan al objeto
    private byte age;                   // 1. Tipo byte
    private short departmentCode;       // 2. Tipo short
    private int id;                     // 3. Tipo int
    private long annualSalary;          // 4. Tipo long
    private float bonus;                // 5. Tipo float
    private double deductions;          // 6. Tipo double
    private char nameInitial;           // 7. Tipo char
    private boolean isActive;           // 8. Tipo boolean
    private String fullName;            // Objeto String (No es primitivo)


    public Employee(byte age, short departmentCode, int id, long annualSalary,
                    float bonus, double deductions, char nameInitial,
                    boolean isActive, String fullName) {
        this.age = age;
        this.departmentCode = departmentCode;
        this.id = id;
        this.annualSalary = annualSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.nameInitial = nameInitial;
        this.isActive = isActive;
        this.fullName = fullName;
    }


    public static void main(String[] args) {
        System.out.println("Employee class loaded successfully.");
    }
}