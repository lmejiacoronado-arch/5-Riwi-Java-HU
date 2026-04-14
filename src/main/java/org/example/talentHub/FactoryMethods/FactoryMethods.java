package org.example.talentHub.FactoryMethods;

import java.util.List;
import java.util.Map;

/**
 * Task 2 - HU3: Factory Methods — List.of() and Map.of() (Java 9/11).
 *
 * WHY ARE THESE SAFER THAN ArrayList?
 *   List.of() and Map.of() create IMMUTABLE collections.
 *   Once created, they cannot be modified (.add(), .remove(), .put() all throw
 *   UnsupportedOperationException). This makes them safe for configuration data
 *   that should never change at runtime.
 *
 * TRADE-OFF:
 *   Immutability = safety, but no dynamic modification allowed.
 *   Use ArrayList when you need to add/remove elements at runtime.
 *   Use List.of() / Map.of() for fixed reference data (tech stacks, office locations, etc.)
 */
public class FactoryMethods {

    public static void run() {
        System.out.println("\n=== TASK 2: FACTORY METHODS (List.of / Map.of) ===\n");

        // Java 9/11: List.of() — immutable list of technologies
        List<String> technologies = List.of(
                "Java", "Spring Boot", "React", "Angular", "Docker"
        );

        // Java 9/11: Map.of() — immutable map of office locations
        // Key: office code | Value: city name
        Map<String, String> offices = Map.of(
                "O001", "Medellin",
                "O002", "Bogota",
                "O003", "Cali",
                "O004", "Barranquilla"
        );

        System.out.println("--- TECHNOLOGIES (List.of — Immutable) ---");
        System.out.println("Size: " + technologies.size());
        for (String tech : technologies) {
            System.out.println("  • " + tech);
        }

        System.out.println("\n--- OFFICES (Map.of — Immutable) ---");
        System.out.println("Size: " + offices.size());
        for (Map.Entry<String, String> office : offices.entrySet()) {
            System.out.println("  • " + office.getKey() + " - " + office.getValue());
        }

        System.out.println("\n--- IMMUTABILITY DEMONSTRATION ---");
        System.out.println("List.of and Map.of create READ-ONLY collections.");
        System.out.println("The following lines would throw UnsupportedOperationException:");
        System.out.println("  // technologies.add(\"Python\");");
        System.out.println("  // offices.put(\"O005\", \"Cartagena\");");
        System.out.println("\nADVANTAGE: Safe for configuration data that must not change.");
        System.out.println("TRADE-OFF : Cannot be modified after creation.");
    }
}
