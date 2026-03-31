Corporate Talent Hub - Module 1: Java Fundamentals (HU1)
Project Overview

This project is part of the Software Development and Cybersecurity bootcamp. The main goal of this User Story (HU1) is to establish the architectural foundations and core logic for a Talent Management System using Java 21.

The project covers fundamental concepts such as JVM architecture, data modeling (POJOs vs Records), memory management (Stack vs Heap), and modern error handling.
Tasks Completed
Task 1: Architecture and JVM Theory

    Analysis of the Java Virtual Machine (JVM), JRE, and JDK.

    Understanding the compilation process: from .java source code to .class bytecode.

    Setup of the development environment in Debian Linux using VS Code.

Task 2: Data Modeling & 8 Primitive Types

    Implementation of the Employee class using all 8 primitive types (byte, short, int, long, float, double, char, boolean) and String.

    Comparison between Legacy Java (POJOs) and Modern Java (Records).

    Demonstration of verbosity vs. conciseness in data structures.

Task 3: Business Logic & Operators

    Calculation of final salaries and bonuses using Arithmetic Operators (+, -, *, /, %).

    Implementation of eligibility rules using Relational (>, <) and Logical Operators (&&, ||, !).

    Analysis of Operator Precedence to ensure mathematical accuracy.

Task 4: Memory Management & Diagnostics

    Stack vs Heap: Analysis of object equality using == (reference) and .equals() (content).

    Helpful NullPointerExceptions: Practical experiment triggering an NPE to demonstrate how Java 21 identifies the specific null source, reducing debugging time.

Technical Stack

    Language: Java 21 (LTS)

    Build Tool: Maven

    Environment: Debian GNU/Linux

    IDE: IntelliJ IDEA / VS Code

    Version Control: Git & GitHub

Key Takeaways
Equality Comparison

    "In Java, == compares the memory address (the container), while .equals() compares the actual data (the content). Records automate this process, making them more efficient for DTOs."

Modern Debugging

    "Java 21's Helpful NullPointerExceptions are a game-changer for developers, pinpointing exactly where a null value was invoked, which was not possible in Java 8."

How to Run??

To compile and run any task from the terminal, use:

javac org/example/talentHub/(**Dirname**)/(**FileName.java**) && java org.example.talentHub.(**Dirname**).Main