package org.example.talentHub.logic;

/**
 * Task 2: Modern Data Modeling using Java 17/21 Records.
 */
public record CompanyRecord(String name, String nit, int foundationYear) {
}

class Main {
    public static void main(String[] args) {
        // Instantiating the Record
        CompanyRecord company1 = new CompanyRecord("Riwi", "1234-5", 2023);

        // Analysis using Java Text Blocks (English version)
        String verbosityComparison =
                """
                In Java 8 (POJOs), the process is longer because we must create the class, 
                declare fields, and then write a constructor manually. This makes the code 
                more verbose. 
                
                In contrast, with Records (Java 17+), everything is done in a single line. 
                The class and its attributes are declared together, making the code 
                concise and easier to read.
                """;

        System.out.println(verbosityComparison);
    }
}

/* * Professional Note: In real-world projects, Records are used only to define data
 * (like database tables), while execution logic (the main method) lives in
 * a separate class (such as App or Main).
 */