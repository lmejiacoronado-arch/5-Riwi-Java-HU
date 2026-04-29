package com.riwi.talent.model.domain;

/**
 * Task 3 - HU4: Concrete subclass representing a Manager profile.
 *
 * 'final' → cannot be extended further.
 *
 * Adds 'monthlyBudget' — the attribute specific to managers.
 */
public final class Manager extends Employee {

    // private — unique to Manager
    private final double monthlyBudget;

    public Manager(int id, String fullName, String email,
                   double baseSalary, int score, double monthlyBudget) {
        super(id, fullName, email, baseSalary, score);
        this.monthlyBudget = monthlyBudget;
    }

    public double getMonthlyBudget() { return monthlyBudget; }

    /**
     * Task 4 - Promotable contract: Manager bonus = 25% of base salary.
     */
    @Override
    public double calculatePromotionBonus() {
        return baseSalary * 0.25;
    }

    /**
     * Task 1 - Polymorphism: Manager-specific role summary.
     */
    @Override
    public String getRoleSummary() {
        return String.format(
                "Manager | %s | Budget: $%.2f | Salary: $%.2f | Score: %d",
                fullName, monthlyBudget, baseSalary, getScore());
    }
}
