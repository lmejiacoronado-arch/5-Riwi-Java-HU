package org.example.talentHub.UserInput;

import java.util.Scanner;

public class UserInputAndVarDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n1. Enter employee data");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            if (option == 1) {

                scanner.nextLine(); // clear buffer

                // Java 11+ type inference
                var name = "";

                System.out.print("Enter employee name: ");
                name = scanner.nextLine();

                System.out.print("Enter employee age: ");
                var age = scanner.nextInt();

                // Validation using if/else
                if (age >= 0 && age <= 120) {
                    System.out.println("Valid data");
                } else {
                    System.out.println("Invalid age");
                }

                /*
                 * Java 8:
                 * String name = scanner.nextLine();
                 * int age = scanner.nextInt();
                 *
                 * Java 11+:
                 * var simplifies local variable declaration.
                 */
            }

        } while (option != 2);

        scanner.close();
    }
}