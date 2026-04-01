package org.example.talentHub.Matrix;

import java.util.Scanner;

public class PerformanceMatrixProcessor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Matrix: 1 employee, 3 quarters
        double[][] performance = new double[1][3];

        for (int i = 0; i < performance.length; i++) {
            for (int j = 0; j < performance[i].length; j++) {
                System.out.print("Enter score for quarter " + (j + 1) + ": ");
                performance[i][j] = scanner.nextDouble();
            }
        }

        double sum = 0;

        for (int i = 0; i < performance.length; i++) {
            for (int j = 0; j < performance[i].length; j++) {
                sum += performance[i][j];
            }
        }

        double average = sum / 3;

        // Explicit casting (double -> int)
        int simplifiedScore = (int) average;

        /*
         * Casting causes loss of precision:
         * Example: 8.9 becomes 8
         */

        System.out.println("Average score: " + average);
        System.out.println("Simplified score: " + simplifiedScore);

        scanner.close();
    }
}