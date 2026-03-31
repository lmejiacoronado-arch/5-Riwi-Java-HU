package org.example.talentHub.ModernDiagnostics;

public record versions(String name, int age) {
}

class Main {
    public static void main(String[] args) {
        /* == VS EQUALS ANALYSIS:

        PRIMITIVE TYPES (Stack):
        The == operator compares the values directly in the stack.
        If two variables have the same value, it returns true.

        OBJECTS & RECORDS (Heap):
        1. Using == : If we use == on two different instances, it will be FALSE
           even if they have the same content. This is because they are two
           different objects living in two different memory addresses.
           The only way to get TRUE is if both variables point to the
           exact same memory address (the same object).

        2. Using .equals() : If we use .equals() on those same instances,
           it will be TRUE because it compares the data inside each object
           regardless of their different memory locations.
           (Records override .equals() automatically to compare fields). */

        versions emp1 = new versions("Luis", 22);
        versions emp2 = new versions("Luis", 22);

        System.out.println(emp1 == emp2);      // false (Different locations in the Heap)
        System.out.println(emp1.equals(emp2)); // true  (Same data inside)
    }
}