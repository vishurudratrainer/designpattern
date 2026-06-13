package com.training.creational.singleton;

public class EagerSingleton {
    // Instance is created as soon as the class is loaded
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}