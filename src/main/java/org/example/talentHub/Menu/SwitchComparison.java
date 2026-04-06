package org.example.talentHub.Menu;

public class SwitchComparison {

    public static void run(String[] args) {

        int option = 1;

        // LEGACY SWITCH (Java 8)
        switch (option) {
            case 1:
                System.out.println("Register employee");
                break;
            case 2:
                System.out.println("Exit system");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        /*
         * COMPARISON:
         * Java 8 uses "case : break;" structure.
         * Risk: If 'break' is forgotten, fall-through occurs.
         *
         * Java 17+ uses switch expressions with "->"
         * which are safer, shorter, and prevent fall-through.
         */

        int level = 2;
        String category = getSalaryCategory(level);

        System.out.println("Category: " + category);
    }

    // MODERN SWITCH (Java 17/21)
    public static String getSalaryCategory(int level) {
        return switch (level) {
            case 1 -> "Junior";
            case 2 -> "Mid-Level";
            case 3 -> "Senior";
            default -> "Undefined";
        };
    }
}