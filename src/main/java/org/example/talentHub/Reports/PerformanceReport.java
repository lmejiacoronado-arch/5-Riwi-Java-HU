package org.example.talentHub.Reports;

/**
 * Task 2 - HU4: Immutable performance report using a Record (Java 17+).
 *
 * LEGACY COMPARISON (Java 8/11 — POJO style):
 *   To achieve the same immutability manually, you would need to write:
 *   - A class declaration
 *   - 3 private final fields
 *   - A constructor assigning all fields
 *   - 3 getters (getId, getAverage, getFeedback)
 *   - equals() and hashCode() overrides
 *   - toString() override
 *   That is approximately 40+ lines of boilerplate code.
 *
 * MODERN (Java 17+ Record):
 *   All of the above is generated automatically by the compiler in ONE line.
 *   Records are:
 *     - IMMUTABLE by design (all fields are private final implicitly)
 *     - Transparent (toString shows all field values automatically)
 *     - Safe to compare (equals compares field values, not references)
 *
 * USE CASE:
 *   PerformanceReport is emitted at end-of-month for each employee.
 *   It is read-only data — no modification needed after creation.
 *   This makes it a perfect candidate for a Record.
 */
public record PerformanceReport(int employeeId, double average, String feedback) {

    /**
     * Compact constructor — validates data before storing.
     * Records allow this without repeating field assignments.
     */
    public PerformanceReport {
        if (average < 0 || average > 10) {
            throw new IllegalArgumentException(
                    "Average must be between 0.0 and 10.0, got: " + average);
        }
        if (feedback == null || feedback.isBlank()) {
            throw new IllegalArgumentException("Feedback cannot be null or blank.");
        }
    }

    /**
     * Utility method — generates a formatted report string.
     * Records can have instance methods; they just cannot have mutable state.
     */
    public String toFormattedReport() {
        return String.format(
                "╔══════════════════════════════════════╗%n" +
                        "║       END-OF-MONTH REPORT            ║%n" +
                        "╠══════════════════════════════════════╣%n" +
                        "║ Employee ID : %-22d ║%n" +
                        "║ Average     : %-22.2f ║%n" +
                        "║ Feedback    : %-22s ║%n" +
                        "╚══════════════════════════════════════╝",
                employeeId, average, feedback);
    }

    /**
     * Factory method — builds feedback label based on average score.
     * Keeps creation logic centralized.
     */
    public static PerformanceReport of(int employeeId, double average) {
        String feedback = switch ((int) average) {
            case 9, 10 -> "Excellent — promotion candidate";
            case 7, 8  -> "Good — meets expectations";
            case 5, 6  -> "Average — needs improvement";
            default    -> "Below standard — performance plan required";
        };
        return new PerformanceReport(employeeId, average, feedback);
    }
}
