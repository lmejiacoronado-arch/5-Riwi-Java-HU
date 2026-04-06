package org.example.talentHub.BusinessLogic;

public record EmployeeRecord(String name, int employeeId, double baseSalary, double monthlyBonus, int testScore, int age, int headquarterId, boolean isActive) {

    public double calculateFinalSalary(){
        return (baseSalary + (monthlyBonus * 1.10)) - (baseSalary * 0.05);
        /*Execution order:
        1. (monthlyBonus * 1.10) -> Multiplication inside parentheses.
        2. (baseSalary * 0.05)  -> Multiplication inside parentheses (Discount).
        3. baseSalary + Result1 -> Addition.
        4. Result3 - Result2 -> Final subtraction.*/
    }

    public double calculateExtraBonus(){
        int extraBonus = 0; /*Variable initialized in case the condition is met*/
        if ((employeeId % 2 == 0)) {
            extraBonus += 2_000_000;
            /*// The % operator returns the remainder. If it is 0, the number is even.*/
        }
        return extraBonus;
        /*Since the condition was applied, it adds to the initialized variable*/
    }

    public boolean validateEligibility(){
        return (testScore > 85 && age < 30) || (headquarterId == 1 && !isActive);
        /*Operator precedence:
        1. ! (NOT) -> Negates 'isActive' first.
        2. && (AND) -> Evaluates the comparison groups.
        3. || (OR) -> Evaluates the final result between the two blocks.*/
    }
}