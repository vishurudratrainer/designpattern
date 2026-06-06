package com.training.dip.good;
interface Engine {
    void start();
}

class V8Engine implements Engine {
    public void start() { System.out.println("Vroom!"); }
}

class ElectricEngine implements Engine {
    public void start() { System.out.println("Silent hum..."); }
}

public class Car {
    private final Engine engine; // Depends on abstraction

    // Constructor Injection: The engine is supplied from the outside
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() { engine.start(); }
}