package com.training.srp.good;

/**
 * The SOLID Way: Separate Responsibilities
 * Split the responsibilities into distinct classes.
 * Now, changing how an employee is saved won't risk breaking salary calculations.
 */
public class Employee {
    public double calculateSalary() { return 5000.0; }
}