Helpful NullPointerExceptions

"Unlike Java 8, which only indicated the line where the error occurred, Java 21 identifies exactly which method failed (length()) and which variable or method caused the null value (name()). This drastically reduces debugging time."


== VS .EQUALS():

JAVA 8 (PRIMITIVE VS OBJECTS):
- The '==' operator compares values directly in the STACK for primitive types.
- For objects in Java 8, you would typically need to manually override the
  .equals() method to compare content; otherwise, it behaves like '=='.

MODERN VERSIONS (RECORDS):
- If we use '==' on two different instances, the result will be FALSE even if
  they have the same content. This is because they are two distinct objects
  living in two separate memory addresses in the HEAP. The only way to get
  TRUE is if both variables point to the exact same memory address (the same object).

- If we use .equals() on those same instances, the result will be TRUE.
  This is because Records automatically compare the data inside each field,
  regardless of their different locations in memory.