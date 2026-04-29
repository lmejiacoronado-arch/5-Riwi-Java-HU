package com.riwi.talent.model.collections;

/**
 * Simple Employee model used by HU3 collection classes.
 * Contains id, name, salary and score for filtering and reporting.
 */
public class Employee {

    private String id;
    private String name;
    private double salary;
    private int score;

    public Employee(String id, String name, double salary, int score) {
        this.id     = id;
        this.name   = name;
        this.salary = salary;
        this.score  = score;
    }

    public String getId()     { return id; }
    public String getName()   { return name; }
    public double getSalary() { return salary; }
    public int getScore()     { return score; }

    @Override
    public String toString() {
        return String.format("%s | %s | $%.0f | Score: %d", id, name, salary, score);
    }
}
