package com.training.creational.singleton;

public class DoubleCheckedSingleton {
    // The 'volatile' keyword is critical here to prevent thread-safety bugs
    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        // First check (no locking): If the instance exists, return it immediately
        if (instance == null) {
            // Lock the class block to ensure only one thread creates the instance
            synchronized (DoubleCheckedSingleton.class) {
                // Second check (with locking): Inside the block, verify it wasn't just created
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}