package com.training.structural.facade;

public class Projector {
    public void turnOn() {
        System.out.println("Projector is booting up...");
    }

    public void setInputSource(String source) {
        System.out.println("Projector input set to: " + source);
    }

    public void turnOff() {
        System.out.println("Projector shutting down.");
    }
}
