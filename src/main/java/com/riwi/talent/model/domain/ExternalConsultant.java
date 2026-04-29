package com.riwi.talent.model.domain;

import com.riwi.talent.model.interfaces.Promotable;

/**
 * Task 1 - HU4: Second permitted subclass of the sealed Person hierarchy.
 *
 * ExternalConsultant is 'final' — it cannot be extended further.
 * It is NOT an Employee (no baseSalary, no score), which is why
 * it extends Person directly and not Employee.
 *
 * This demonstrates the power of sealed classes: the hierarchy is
 * precisely modeled. The compiler knows Person can ONLY be a
 * Developer, Manager, or ExternalConsultant — nothing else.
 *
 * Implements Promotable independently — consultants have their own
 * bonus logic based on their hourly rate, not a base salary.
 */
public final class ExternalConsultant extends Person implements Promotable {

    // private — full encapsulation
    private final String specialty;
    private final double hourlyRate;

    public ExternalConsultant(int id, String fullName, String email,
                              String specialty, double hourlyRate) {
        super(id, fullName, email);
        this.specialty  = specialty;
        this.hourlyRate = hourlyRate;
    }

    public String getSpecialty()  { return specialty; }
    public double getHourlyRate() { return hourlyRate; }

    /**
     * Consultant bonus = 10 hours of their hourly rate.
     */
    @Override
    public double calculatePromotionBonus() {
        return hourlyRate * 10;
    }

    @Override
    public String getRoleSummary() {
        return String.format(
                "ExternalConsultant | %s | Specialty: %s | Rate: $%.2f/hr",
                fullName, specialty, hourlyRate);
    }
}
