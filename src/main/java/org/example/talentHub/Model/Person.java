package org.example.talentHub.Model;

/**
 * Task 1 - HU4: Sealed class hierarchy (Java 17+).
 *
 * LEGACY (Java 8/11) — Open inheritance:
 *   An abstract class with no restriction allows ANY class in ANY package
 *   to extend it. This creates an open, uncontrolled hierarchy where the
 *   compiler cannot guarantee which subclasses exist at compile time.
 *   Example: public abstract class Person { }
 *   Risk: unknown third-party subclasses break switch/pattern matching logic.
 *
 * MODERN (Java 17+) — Sealed class with 'permits':
 *   The 'sealed' keyword restricts which classes are allowed to extend Person.
 *   Only the classes listed in 'permits' can be direct subclasses.
 *   This gives the compiler FULL KNOWLEDGE of the hierarchy, enabling:
 *     1. Exhaustive pattern matching in switch expressions (no default needed).
 *     2. Safer API design — the domain is closed and controlled.
 *     3. Better encapsulation — no unexpected subclasses from outside the module.
 *
 * ENCAPSULATION:
 *   - 'private'   → accessible only within this class.
 *   - 'protected' → accessible within this class, subclasses, and same package.
 *   Subclasses (Developer, Manager, ExternalConsultant) access inherited
 *   fields exclusively through protected getters — never directly.
 */
public abstract sealed class Person
        permits Employee, ExternalConsultant {

    // private — only this class can access it directly
    private final int id;

    // protected — subclasses can read it via getter or access directly
    protected final String fullName;
    protected final String email;

    protected Person(int id, String fullName, String email) {
        this.id       = id;
        this.fullName = fullName;
        this.email    = email;
    }

    // Public getter — the only way external code accesses 'id'
    public int getId()         { return id; }
    public String getFullName(){ return fullName; }
    public String getEmail()   { return email; }

    /**
     * Abstract method — every subclass must define how it presents itself.
     * Polymorphism: the same call produces different output depending on the type.
     */
    public abstract String getRoleSummary();

    @Override
    public String toString() {
        return String.format("[%s] %s | %s", getClass().getSimpleName(), fullName, email);
    }
}
