package com.riwi.talent.model.architecture;

public class ArchitectureNotes {

    public static void run() {

        /* Main differences between Java 8 (Legacy) and Java 17/21 (Current LTS). */

        /* Moving to modern versions (11/17/21) increases productivity and performance
           by reducing boilerplate code and optimizing resource usage. */

        String keyDifferences = """
        1. Syntax and Language:
        - Type Inference: Explicit declaration vs. 'var' keyword.
        - Data Classes: Manual getters, setters, equals, hashCode vs. 'Records'.
        - Text Blocks: Use of + or \\n vs. Triple double quotes.
        - Switch: Classic statements (break) vs. Switch Expressions (->).
        - Instanceof: Required manual casting vs. Pattern Matching.

        -------------------------------------------------------
        2. New APIs and Technical Improvements:
        - Client: HttpURLConnection vs. HttpClient (Supports HTTP/2 and WebSockets).
        - Collections API: Simplified creation of immutable lists or maps (List.of).
        - Helpful NullPointerExceptions: Messages now specify exactly which variable was null.

        -------------------------------------------------------
        3. Performance and JVM:
        - GC: Java 8 used Parallel GC by default. Java 11+ uses G1GC and introduced ZGC
        (Java 15+), designed for massive memory management with millisecond pauses.
        - Modularization: Allows the JDK to be split into modules; smaller and more secure.
        - Docker/Kubernetes Support: Java 8 sometimes ignored container RAM limits;
        Modern Java is "container-aware" and respects resource limits.

        -------------------------------------------------------
        4. Support Changes (LTS):
        - Java 8: No new features, only critical security patches.
        - Java 11: First major LTS jump after the new release cycle.
        - Java 17/21: Recommended versions for new projects. Long-term support
        consolidating all aforementioned language improvements.
        """;

        System.out.println(keyDifferences);

        /* How the JVM and Garbage Collector (GC) manage objects to optimize memory.*/

        String jvm = """
        
        The Heap memory (where objects live) is divided into three main areas:
        - Young Gen: Eden space, Survivor spaces (S0 and S1).
        - Old Gen (Tenured): For long-lived objects.
        - Metaspace: Stores class metadata (not the actual objects).

        GC CYCLE:
        1. Allocation: Objects are created in Eden.
        2. Minor GC: Triggered when Eden is full. Referenced objects move to Survivor spaces.
        3. Aging: Objects jump between S0 and S1; each jump increases their age counter.
        4. Promotion: If an object reaches a specific age (usually 15), it moves to Old Gen.
        5. Major GC: A deeper cleaning triggered when Old Gen is full.

        How does the GC know what to delete? (Mark, Sweep, and Compact).

        Modern Strategies (Java 11/17+):
        - G1 (Garbage First): Divides the Heap into small regions. Prioritizes cleaning 
          regions with the most "garbage" first.
        - ZGC: Handles Terabytes of memory with pauses shorter than 1ms.
        """;

        System.out.println(jvm);
    }
}