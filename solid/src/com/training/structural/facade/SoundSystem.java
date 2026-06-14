package com.training.structural.facade;

public class SoundSystem {
    public void turnOn() {
        System.out.println("Surround sound system turned on.");
    }

    public void setVolume(int level) {
        System.out.println("Volume set to " + level);
    }

    public void turnOff() {
        System.out.println("Sound system muted and turned off.");
    }
}
