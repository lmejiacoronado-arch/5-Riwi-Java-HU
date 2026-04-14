package org.example.talentHub.ExceptionAndValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingAndValidation {

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        // ====================================================================
        // TASK 4 - try-catch to handle InputMismatchException
        // If the user enters text where a number is expected, the Scanner
        // throws InputMismatchException. Without this block, the program
        // crashes abruptly. With it, the system reports the error and keeps running.
        //
        // LTS ANALYSIS - Improved error messages (Java 17/21 vs Java 8):
        //
        // Java 8:
        //   NullPointerException: null
        //   → Generic message, no context. Hard to debug.
        //
        // Java 17/21 (JEP 358 - Helpful NullPointerExceptions):
        //   NullPointerException: Cannot invoke "String.length()" because
        //   "<local1>" is null
        //   → The message specifies exactly which variable was null and what
        //   operation failed, drastically reducing debugging time.
        //
        // The same applies to other exceptions: stack traces and messages
        // are more descriptive and actionable in modern LTS versions.
        // ====================================================================

        while (!validInput) {
            try {
                System.out.print("\nEnter employee average score (0.0 - 10.0): ");
                double average = scanner.nextDouble();

                // ============================================================
                // TASK 4 - Ternary operator for promotion decision
                // Condition ? value_if_true : value_if_false
                // More concise than an if/else for simple assignments.
                // ============================================================
                String status = (average >= 7.0) ? "PROMOTED" : "NOT PROMOTED";

                System.out.println(">> Average: " + average);
                System.out.println(">> Promotion status: " + status);

                validInput = true; // exit the loop if input was valid

            } catch (InputMismatchException e) {
                System.out.println(">> ERROR: Invalid input. Please enter a numeric value (e.g. 8.5).");
                scanner.nextLine(); // clear the buffer to allow a new attempt
            }
        }

        scanner.close();
    }
}