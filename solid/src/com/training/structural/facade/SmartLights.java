package com.training.structural.facade;

public class SmartLights {
    public void dim(int percentage) { System.out.println("Dimming lights to " + percentage + "%."); }
    public void turnOn() { System.out.println("Lights turned on fully."); }
}

