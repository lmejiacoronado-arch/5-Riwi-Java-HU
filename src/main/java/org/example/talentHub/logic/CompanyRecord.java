package org.example.talentHub.logic;

/**
 * Task 2: Modern Data Modeling using Java 17/21 Records.
 */
public record CompanyRecord(String name, String nit, int foundationYear) {
}

/* * Professional Note: In real-world projects, Records are used only to define data
 * (like database tables), while execution logic (the main method) lives in
 * a separate class (such as App or Main).
 */