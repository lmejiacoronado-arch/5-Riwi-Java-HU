package com.riwi.talent.view.menu;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Task 1 - HU2: Switch Legacy (Java 8) vs Modern Switch Expression (Java 17/21).
 *
 * LEGACY (Java 8): Uses 'case : break;' syntax.
 *   RISK — "fall-through": if 'break' is forgotten, execution automatically
 *   continues into the next case, producing unexpected behavior.
 *
 * MODERN (Java 17/21): Uses Switch Expression with '->' syntax.
 *   BENEFIT — No 'break' needed. The compiler guarantees each branch
 *   is independent, completely eliminating the fall-through risk.
 *   Also more concise and readable.
 */
public class SwitchComparison {

    public static void run(Scanner scanner) {
        int option;

        do {
            System.out.println("\n===== TALENT HUB - MAIN MENU =====");
            System.out.println("1. Register employee");
            System.out.println("2. View salary category");
            System.out.println("3. Exit");
            option = readInt(scanner, "Choose an option: ");

            // LEGACY SWITCH (Java 8) - case : break;
            switch (option) {
                case 1:
                    System.out.println(">> Employee registration selected.");
                    break;
                case 2:
                    int level = readInt(scanner, "Enter salary level (1=Junior, 2=Mid, 3=Senior): ");
                    String category = getSalaryCategory(level);
                    System.out.println(">> Salary Category: " + category);
                    break;
                case 3:
                    System.out.println(">> Exiting menu. Goodbye!");
                    break;
                default:
                    System.out.println(">> Invalid option. Please try again.");
                    break;
            }

        } while (option != 3);
    }

    /**
     * Task 1 - MODERN SYNTAX (Java 17/21): Switch Expression with arrow ->
     * Each '->' directly maps to the returned value.
     * No 'break' needed; eliminates fall-through risk entirely.
     */
    public static String getSalaryCategory(int level) {
        return switch (level) {
            case 1 -> "Junior";
            case 2 -> "Mid-Level";
            case 3 -> "Senior";
            default -> "Undefined";
        };
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                return value;
            } catch (InputMismatchException e) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println(">> Enter a valid integer.");
            } catch (NoSuchElementException e) {
                throw e;
            }
        }
    }
}
