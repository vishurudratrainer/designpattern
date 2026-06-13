package com.training.creational.singleton;

public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Enum Singleton is working!");
    }
}