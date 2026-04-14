package org.example.talentHub.UserInput;

import java.util.Scanner;

/**
 * Task 2 - HU2: Dynamic data capture with do-while and type inference with 'var' (Java 11+).
 *
 * TYPE INFERENCE COMPARISON:
 *   Java 8  → String name = scanner.nextLine();  (explicit type)
 *   Java 11+ → var name = scanner.nextLine();     (compiler infers the type)
 *
 * IMPORTANT: 'var' is only valid for LOCAL variables.
 * It cannot be used for class fields, method parameters, or return types.
 */
public class UserInputAndVarDemo {

    public static void run(Scanner scanner) {
        int option;

        do {
            System.out.println("\n1. Enter employee data");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            if (option == 1) {

                scanner.nextLine(); // clear buffer after nextInt()

                // Java 11+ var — compiler infers String
                var name = "";
                System.out.print("Enter employee name: ");
                name = scanner.nextLine();

                // Java 11+ var — compiler infers int
                System.out.print("Enter employee age (0-120): ");
                var age = scanner.nextInt();

                // Java 11+ var — compiler infers int
                System.out.print("Enter employee salary: ");
                var salary = scanner.nextInt();

                // Java 11+ var — compiler infers int
                System.out.print("Enter department code (1-999): ");
                var departmentCode = scanner.nextInt();

                // Java 11+ var — compiler infers int
                System.out.print("Is the employee active? (1=Yes / 0=No): ");
                var activeInput = scanner.nextInt();

                // Validations with if / else — ranges based on primitive types:
                // age          → byte    (0 - 120)
                // salary       → int     (> 0)
                // departmentCode → short (1 - 999)
                // activeInput  → boolean (1 or 0)
                if (name.isEmpty()) {
                    System.out.println(">> ERROR: Name cannot be empty.");
                } else if (age < 0 || age > 120) {
                    System.out.println(">> ERROR: Age must be between 0 and 120 (byte range).");
                } else if (salary <= 0) {
                    System.out.println(">> ERROR: Salary must be a positive value (int > 0).");
                } else if (departmentCode < 1 || departmentCode > 999) {
                    System.out.println(">> ERROR: Department code must be between 1 and 999 (short range).");
                } else if (activeInput != 0 && activeInput != 1) {
                    System.out.println(">> ERROR: Active status must be 1 (Yes) or 0 (No).");
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
    }
}
