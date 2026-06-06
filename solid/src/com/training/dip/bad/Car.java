package com.training.dip.bad;

/**
 * The high-level Car class is tightly coupled to a specific low-level V8Engine.
 * If we want to change it to an ElectricEngine, we have to completely
 * rewrite the internals of the Car class.
 */

class V8Engine {
    void start() { System.out.println("Vroom!"); }
}

public class Car {
    private V8Engine engine; // Direct coupling to low-level detail

    public Car() {
        this.engine = new V8Engine(); // Hardcoded instantiation
    }

    public void drive() { engine.start(); }
}