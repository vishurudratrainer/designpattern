package com.training.srp.bad;

/**
 * The Bad Way: The "Do-It-All" Class
 * This class calculates salary, updates the database, and prints a report.
 * If the database schema changes or the report formatting changes, this single class must be modified.
 */
public class Employee {
    public double calculateSalary() { /* Business Logic */ return 5000.0; }
    public void saveToDatabase() { /* Persistence Logic */ }
    public void printPerformanceReport() { /* Presentation Logic */ }
}
