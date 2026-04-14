package org.example.talentHub.Status;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task 4 - HU2: Exception handling and promotion validation.
 *
 * ROBUST INPUT: Wraps Scanner reads in try-catch for InputMismatchException.
 * If the user types text where a number is expected, the program catches
 * the error and asks again instead of crashing.
 *
 * LTS ANALYSIS — Improved error messages (Java 17/21 vs Java 8):
 *
 *   Java 8:
 *     NullPointerException: null
 *     → Generic message, no context. Very hard to debug.
 *
 *   Java 17/21 (JEP 358 - Helpful NullPointerExceptions):
 *     NullPointerException: Cannot invoke "String.length()" because "<local1>" is null
 *     → Specifies exactly which variable was null and what operation failed,
 *     drastically reducing debugging time.
 *
 * TERNARY OPERATOR:
 *   condition ? value_if_true : value_if_false
 *   More concise than an if/else for simple assignments.
 */
public class ExceptionHandlingAndValidation {

    public static void run(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("\nEnter employee average score (0.0 - 10.0): ");
                double average = scanner.nextDouble();

                // Ternary operator for promotion decision
                String status = (average >= 7.0) ? "PROMOTED" : "NOT PROMOTED";

                System.out.println(">> Average: " + average);
                System.out.println(">> Promotion status: " + status);

                validInput = true;

            } catch (InputMismatchException e) {
                System.out.println(">> ERROR: Invalid input. Please enter a numeric value (e.g. 8.5).");
                scanner.nextLine(); // clear buffer to allow a new attempt
            }
        }
    }
}
