package org.example.talentHub.Maiin;

import java.util.Scanner;

public class SwitchComparison {

    public static void run(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        // =====================================================================
        // TASK 1 - LEGACY SYNTAX (Java 8): Main menu using classic switch
        // Structure: 'case : break;'
        // FALL-THROUGH RISK: If 'break' is forgotten, execution automatically
        // falls into the next case without stopping, producing unexpected
        // behavior that is hard to debug.
        //
        // MODERN SYNTAX (Java 17/21): Switch Expression with '->'
        // Benefits: no 'break' needed, more concise, and the compiler
        // guarantees each branch is independent, eliminating fall-through.
        // =====================================================================

        do {
            System.out.println("\n===== TALENT HUB - MAIN MENU =====");
            System.out.println("1. Register employee");
            System.out.println("2. View salary category");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            // LEGACY SWITCH (Java 8) - case : break;
            switch (option) {
                case 1:
                    System.out.println(">> Employee registration selected.");
                    break;
                case 2:
                    System.out.print("Enter salary level (1=Junior, 2=Mid, 3=Senior): ");
                    int level = scanner.nextInt();
                    String category = obtenerCategoriaSalarial(level);
                    System.out.println(">> Salary Category: " + category);
                    break;
                case 3:
                    System.out.println(">> Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println(">> Invalid option. Please try again.");
                    break;
            }

        } while (option != 3);

        scanner.close();
    }

    // =========================================================================
    // TASK 1 - MODERN SYNTAX (Java 17/21): Switch Expression with arrow ->
    // Each '->' directly maps to the returned value.
    // No 'break' needed and completely eliminates the fall-through risk.
    // =========================================================================
    public static String obtenerCategoriaSalarial(int level) {
        return switch (level) {
            case 1 -> "Junior";
            case 2 -> "Mid-Level";
            case 3 -> "Senior";
            default -> "Undefined";
        };
    }
}