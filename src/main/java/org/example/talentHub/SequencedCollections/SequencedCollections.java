package org.example.talentHub.SequencedCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * Task 3 - HU3: Sequenced Collections (Java 21 — LTS Modern).
 *
 * EVOLUTION IN COLLECTION ACCESS:
 *
 *   Java 8/11 (Legacy) — Manual index access:
 *     list.get(0)                → first element (risk: IndexOutOfBoundsException)
 *     list.get(list.size() - 1) → last element  (risk: off-by-one error)
 *
 *   Java 21 (Modern) — Sequenced Collections API:
 *     list.getFirst()  → first element (throws NoSuchElementException if empty — clearer)
 *     list.getLast()   → last element
 *     list.reversed()  → reversed view without manual sorting algorithms
 *
 * READABILITY IMPROVEMENT: getFirst() and getLast() express intent clearly.
 * ERROR PREVENTION: Eliminates off-by-one bugs from manual index calculations.
 */
public class SequencedCollections {

    public static void run() {
        System.out.println("\n=== TASK 3: SEQUENCED COLLECTIONS (Java 21) ===\n");

        List<String> employees = new ArrayList<>();
        employees.add("Ana Lopez");
        employees.add("Carlos Ruiz");
        employees.add("Maria Gomez");
        employees.add("Luis Perez");

        System.out.println("Original list: " + employees);

        // LEGACY (Java 8/11) — manual index access
        System.out.println("\n--- LEGACY SYNTAX (Java 8/11) ---");
        String firstLegacy = employees.get(0);
        String lastLegacy  = employees.get(employees.size() - 1);
        System.out.println("First employee : " + firstLegacy);
        System.out.println("Last employee  : " + lastLegacy);
        System.out.println("Risk: IndexOutOfBoundsException if list is empty.");

        // MODERN (Java 21) — Sequenced Collections API
        System.out.println("\n--- MODERN SYNTAX (Java 21) ---");
        String firstModern = employees.getFirst();
        String lastModern  = employees.getLast();
        System.out.println("First employee : " + firstModern);
        System.out.println("Last employee  : " + lastModern);

        List<String> reversedEmployees = employees.reversed();
        System.out.println("Reversed order : " + reversedEmployees);

        // Safety comparison with empty list
        System.out.println("\n--- EMPTY LIST COMPARISON ---");
        List<String> emptyList = new ArrayList<>();
        System.out.println("Legacy  (Java 8)  : emptyList.get(0)       → IndexOutOfBoundsException");
        System.out.println("Modern  (Java 21) : emptyList.getFirst()   → NoSuchElementException (clearer message)");

        System.out.println("\nBest practice: always check isEmpty() before accessing elements.");
        if (!emptyList.isEmpty()) {
            System.out.println(emptyList.getFirst());
        } else {
            System.out.println("  List is empty — safely handled with isEmpty() check.");
        }

        System.out.println("\n--- JAVA 21 ADVANTAGES ---");
        System.out.println("1. getFirst() / getLast() — self-documenting, no index math needed.");
        System.out.println("2. reversed()             — no manual sorting algorithm required.");
        System.out.println("3. Prevents off-by-one errors that are common with list.size()-1.");
    }
}
