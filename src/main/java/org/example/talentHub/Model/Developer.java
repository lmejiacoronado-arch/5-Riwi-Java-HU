package org.example.talentHub.Model;

/**
 * Task 3 - HU4: Concrete subclass representing a Developer profile.
 *
 * 'final' → cannot be extended further. Closes this branch of the hierarchy.
 *
 * Adds the 'mainLanguage' attribute — specific to developers only.
 * This attribute is what Pattern Matching allows us to access
 * without a manual cast.
 */
public final class Developer extends Employee {

    // private — unique to Developer, accessed only via getter
    private final String mainLanguage;

    public Developer(int id, String fullName, String email,
                     double baseSalary, int score, String mainLanguage) {
        super(id, fullName, email, baseSalary, score);
        this.mainLanguage = mainLanguage;
    }

    public String getMainLanguage() { return mainLanguage; }

    /**
     * Task 4 - Promotable contract: Developer bonus = 15% of base salary.
     */
    @Override
    public double calculatePromotionBonus() {
        return baseSalary * 0.15;
    }

    /**
     * Task 1 - Polymorphism: each subclass provides its own role summary.
     */
    @Override
    public String getRoleSummary() {
        return String.format(
                "Developer | %s | Language: %s | Salary: $%.2f | Score: %d",
                fullName, mainLanguage, baseSalary, getScore());
    }
}
