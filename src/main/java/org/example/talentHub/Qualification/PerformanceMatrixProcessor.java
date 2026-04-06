package org.example.talentHub.Qualification;

import java.util.Scanner;

public class PerformanceMatrixProcessor {

    public static void run() {

        Scanner scanner = new Scanner(System.in);

        // ====================================================================
        // TASK 3 - Performance matrix (double[][])
        // Rows → employees | Columns → 3 quarters (Q1, Q2, Q3)
        // ====================================================================

        System.out.print("\nHow many employees do you want to evaluate? ");
        int numEmployees = scanner.nextInt();

        // Matrix definition: [employees][3 quarters]
        double[][] performance = new double[numEmployees][3];

        // ====================================================================
        // TASK 3 - Nested for loops to fill the matrix
        // Outer loop → iterates over each employee
        // Inner loop → iterates over the 3 quarters of that employee
        // ====================================================================

        for (int i = 0; i < performance.length; i++) {
            System.out.println("\n--- Employee " + (i + 1) + " ---");
            for (int j = 0; j < performance[i].length; j++) {
                System.out.print("Enter score for quarter " + (j + 1) + " (0.0 - 10.0): ");
                performance[i][j] = scanner.nextDouble();
            }
        }

        // ====================================================================
        // TASK 3 - Nested for loops to calculate averages and casting
        // ====================================================================

        System.out.println("\n===== PERFORMANCE REPORT =====");

        for (int i = 0; i < performance.length; i++) {
            double sum = 0;

            for (int j = 0; j < performance[i].length; j++) {
                sum += performance[i][j];
            }

            double average = sum / performance[i].length;

            // ================================================================
            // TASK 3 - Explicit casting: double → int
            // The decimal part is lost (truncation, NOT rounding).
            // Example: 8.9 → 8 | 7.1 → 7
            // Useful for reports where only the integer score matters.
            // ================================================================
            int simplifiedScore = (int) average;

            System.out.println("Employee " + (i + 1) + ":");
            System.out.println("  Average score    (double): " + average);
            System.out.println("  Simplified score (int)   : " + simplifiedScore
                    + "  <- casting truncates decimals");
        }

        scanner.close();
    }
}