package org.example.talentHub.UserInput;

import java.util.Scanner;

public class UserInputAndVarDemo {

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n1. Enter employee data");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            if (option == 1) {

                scanner.nextLine(); // clear buffer

                // ============================================================
                // TASK 2 - Type inference with 'var' (Java 11+)
                // Java 8   → String name = scanner.nextLine();
                // Java 11+ → var name = scanner.nextLine();
                // The compiler infers the type at compile time.
                // 'var' is only valid for local variables, not for class
                // fields, parameters, or return types.
                // ============================================================

                var name = "";
                System.out.print("Enter employee name: ");
                name = scanner.nextLine();

                System.out.print("Enter employee age (0-120): ");
                var age = scanner.nextInt();

                // Java 8 → int salary = scanner.nextInt();
                // Java 11+ → var salary = scanner.nextInt();
                System.out.print("Enter employee salary: ");
                var salary = scanner.nextInt();

                System.out.print("Enter department code (1-999): ");
                var departmentCode = scanner.nextInt();

                System.out.print("Is the employee active? (1=Yes / 0=No): ");
                var activeInput = scanner.nextInt();

                // ============================================================
                // TASK 2 - Validations with if / else
                // Ranges defined according to the system's primitive types:
                // age      → byte  (0 - 120)
                // salary   → int   (> 0)
                // deptCode → short (1 - 999)
                // active   → boolean (1 or 0)
                // ============================================================

                if (name.isEmpty()) {
                    System.out.println(">> ERROR: Name cannot be empty.");
                } else if (age < 0 || age > 120) {
                    System.out.println(">> ERROR: Age must be between 0 and 120 (byte range).");
                } else if (salary <= 0) {
                    System.out.println(">> ERROR: Salary must be a positive value (int > 0).");
                } else if (departmentCode < 1 || departmentCode > 999) {
                    System.out.println(">> ERROR: Department code must be between 1 and 999 (short range).");
                } else if (activeInput != 0 && activeInput != 1) {
                    System.out.println(">> ERROR: Active status must be 1 (Yes) or 0 (No) (boolean).");
                } else {
                    boolean isActive = activeInput == 1;
                    System.out.println("\n>> Employee registered successfully!");
                    System.out.println("   Name:       " + name);
                    System.out.println("   Age:        " + age);
                    System.out.println("   Salary:     $" + salary);
                    System.out.println("   Department: " + departmentCode);
                    System.out.println("   Active:     " + isActive);
                }

            } else if (option != 2) {
                System.out.println(">> Invalid option. Please choose 1 or 2.");
            }

        } while (option != 2);

        scanner.close();
    }
}