package com.riwi.talent.model.report;

import com.riwi.talent.model.entity.EmployeeEntity;

/**
 * Task 4 - HU5: Immutable report using a Record (Java 17+).
 *
 * MODERN vs LEGACY COMPARISON:
 *
 *   LEGACY (Java 8 — POJO): ~50 lines of boilerplate:
 *     private final int id; private final String fullName; ...
 *     public EmployeeReport(int id, ...) { this.id = id; ... }
 *     public int getId() { return id; }
 *     // + equals(), hashCode(), toString() manually
 *
 *   MODERN (Java 17 Record): ONE line — compiler auto-generates everything.
 *     Records are immutable by design (all fields are private final).
 *
 * RECORDS + JDBC:
 *   When mapping ResultSet rows to Records, the data cannot be accidentally
 *   mutated after being read from the DB. With Java 8 POJOs, a developer
 *   could call a setter after reading — Records make that impossible at
 *   compile time, eliminating entire categories of stale-data bugs.
 */
public record EmployeeReport(
        int    id,
        String fullName,
        String role,
        double baseSalary,
        int    score,
        double promotionBonus
) {
    public static EmployeeReport from(EmployeeEntity e) {
        double bonus = switch (e.getRole()) {
            case "Manager"            -> e.getBaseSalary() * 0.25;
            case "Developer"          -> e.getBaseSalary() * 0.15;
            case "ExternalConsultant" -> e.getBaseSalary() * 0.10;
            default                   -> 0;
        };
        return new EmployeeReport(
                e.getId(), e.getFullName(), e.getRole(),
                e.getBaseSalary(), e.getScore(), bonus);
    }

    /**
     * Task 4 — Text Block row (Java 15+).
     * Compare to Java 8: "| " + id + " | " + fullName + ... — unreadable.
     */
    public String toFormattedRow() {
        return String.format(
                "| %-4d | %-20s | %-22s | $%,12.0f | %3d | $%,12.0f |",
                id, fullName, role, baseSalary, score, promotionBonus);
    }
}
