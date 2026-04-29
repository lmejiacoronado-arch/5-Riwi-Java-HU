package com.riwi.talent.model.entity;

/**
 * Task 3 - HU5: Model layer entity (MVC — Model).
 * Mutable class used for INSERT and UPDATE operations.
 * Named EmployeeEntity to avoid conflict with logic.Employee (HU1).
 */
public class EmployeeEntity {

    private Integer id;
    private String  fullName;
    private String  email;
    private String  role;
    private double  baseSalary;
    private int     score;

    public EmployeeEntity() {}

    public EmployeeEntity(Integer id, String fullName, String email,
                          String role, double baseSalary, int score) {
        this.id         = id;
        this.fullName   = fullName;
        this.email      = email;
        this.role       = role;
        this.baseSalary = baseSalary;
        this.score      = score;
    }

    public Integer getId()         { return id; }
    public String  getFullName()   { return fullName; }
    public String  getEmail()      { return email; }
    public String  getRole()       { return role; }
    public double  getBaseSalary() { return baseSalary; }
    public int     getScore()      { return score; }

    public void setId(Integer id)               { this.id = id; }
    public void setFullName(String fullName)     { this.fullName = fullName; }
    public void setEmail(String email)           { this.email = email; }
    public void setRole(String role)             { this.role = role; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public void setScore(int score)              { this.score = score; }

    @Override
    public String toString() {
        return String.format("[%d] %-20s | %-30s | %-22s | $%,.0f | Score: %d",
                id, fullName, email, role, baseSalary, score);
    }
}
