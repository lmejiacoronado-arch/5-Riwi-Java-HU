package com.riwi.talent.model.interfaces;

/**
 * Task 4 - HU4: Interface defining the promotion behavior contract.
 *
 * INTERFACE EVOLUTION (Java 8+):
 *   Before Java 8, interfaces could only declare abstract methods.
 *   Adding a new method to an interface would break ALL classes that implemented it.
 *
 *   Java 8 introduced DEFAULT METHODS — concrete implementations inside interfaces.
 *   This allows new behavior to be added to an existing interface without forcing
 *   every implementing class to override it. A major step for backwards compatibility.
 *
 * ENCAPSULATION:
 *   All fields in implementing classes use 'private' (own class only) or
 *   'protected' (subclasses and same package). This guarantees full encapsulation.
 */
public interface Promotable {

    /**
     * Abstract method — every implementing class MUST provide its own logic.
     * The promotion bonus calculation depends on the specific role.
     *
     * @return the calculated promotion bonus amount
     */
    double calculatePromotionBonus();

    /**
     * Default method (Java 8+) — provides a shared implementation.
     * Classes that implement Promotable inherit this behavior for free.
     * They can override it if needed, but are not required to.
     *
     * ADVANTAGE over Java 7: Adding this method does not break any existing
     * class that already implements Promotable — they inherit the default.
     */
    default void logPromotionEvent() {
        System.out.println("[PROMOTION LOG] Promotion bonus calculated: $"
                + String.format("%.2f", calculatePromotionBonus()));
        System.out.println("[PROMOTION LOG] Event registered at: "
                + java.time.LocalDate.now());
    }
}
