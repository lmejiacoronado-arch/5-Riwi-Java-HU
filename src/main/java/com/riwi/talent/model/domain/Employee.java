package com.riwi.talent.model.domain;

import com.riwi.talent.model.interfaces.Promotable;

/**
 * Task 1 & 3 - HU4: Abstract intermediate class in the sealed hierarchy.
 *
 * 'non-sealed' → allows its own subclasses (Developer, Manager) to be freely extended.
 *   This is required by the sealed class rules: every permitted subclass must be
 *   declared as one of: final, sealed, or non-sealed.
 *
 * Implements Promotable to enforce the promotion contract on all employees.
 *
 * ENCAPSULATION:
 *   - baseSalary is 'protected' so Developer and Manager can read it
 *     when calculating their specific promotion bonuses.
 *   - score is 'private' — accessed only through the getter.
 */
public abstract non-sealed class Employee extends Person implements Promotable {

    // protected — accessible by subclasses (Developer, Manager)
    protected final double baseSalary;

    // private — not accessible by subclasses directly
    private final int score;

    protected Employee(int id, String fullName, String email,
                       double baseSalary, int score) {
        super(id, fullName, email);
        this.baseSalary = baseSalary;
        this.score      = score;
    }

    public double getBaseSalary() { return baseSalary; }
    public int getScore()         { return score; }
}
