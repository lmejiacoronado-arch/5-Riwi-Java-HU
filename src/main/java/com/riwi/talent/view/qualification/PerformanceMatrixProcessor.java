package com.riwi.talent.view.qualification;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task 3 - HU2: Multidimensional performance matrix with explicit casting.
 *
 * CASTING (double → int):
 *   The decimal part is TRUNCATED (not rounded).
 *   Example: 8.9 → 8 | 7.1 → 7
 *   Useful for simplified score reports where only the integer value matters.
 *   This is a data precision trade-off that must be documented.
 */
public class PerformanceMatrixProcessor {

    public static void run(Scanner scanner) {

        // Matrix: rows = employees, columns = 3 quarters (Q1, Q2, Q3)
        int numEmployees = readInt(scanner, "\nHow many employees do you want to evaluate? ");

        double[][] performance = new double[numEmployees][3];

        // Nested for loops — outer: each employee, inner: 3 quarters
        for (int i = 0; i < performance.length; i++) {
            System.out.println("\n--- Employee " + (i + 1) + " ---");
            for (int j = 0; j < performance[i].length; j++) {
                performance[i][j] = readDouble(scanner,
                        "Enter score for quarter " + (j + 1) + " (0.0 - 10.0): ");
            }
        }

        System.out.println("\n===== PERFORMANCE REPORT =====");

        for (int i = 0; i < performance.length; i++) {
            double sum = 0;

            for (int j = 0; j < performance[i].length; j++) {
                sum += performance[i][j];
            }

            double average = sum / performance[i].length;

            // Explicit casting: double → int (truncates decimal part)
            int simplifiedScore = (int) average;

            // Ternary operator — Task 4: promotion decision based on average
            String promotionStatus = (average >= 7.0) ? "PROMOTED" : "NOT PROMOTED";

            System.out.println("Employee " + (i + 1) + ":");
            System.out.println("  Average score    (double): " + average);
            System.out.println("  Simplified score (int)   : " + simplifiedScore
                    + "  <- casting truncates decimals");
            System.out.println("  Promotion status         : " + promotionStatus);
        }
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
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                return value;
            } catch (InputMismatchException e) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println(">> Enter a valid number.");
            }
        }
    }
}
