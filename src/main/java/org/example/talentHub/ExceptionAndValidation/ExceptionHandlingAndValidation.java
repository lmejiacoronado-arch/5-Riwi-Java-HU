package org.example.talentHub.ExceptionAndValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingAndValidation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter employee average score: ");
            double average = scanner.nextDouble();

            // Ternary operator for promotion decision
            String status = (average >= 7) ? "PROMOTED" : "NOT PROMOTED";

            System.out.println("Status: " + status);

            /*
             * Java 17/21 improvements:
             * Exception messages are more descriptive
             * and provide better debugging compared to Java 8.
             */

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input detected.");
        }

        scanner.close();
    }
}