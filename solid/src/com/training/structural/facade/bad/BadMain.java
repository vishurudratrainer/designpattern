package com.training.structural.facade.bad;

import com.training.structural.facade.Projector;
import com.training.structural.facade.SmartLights;
import com.training.structural.facade.SoundSystem;
import com.training.structural.facade.StreamingService;

// Client Application Code
public class BadMain {
    public static void main(String[] args) {
        // The client must instantiate and remember all subsystems
        SmartLights lights = new SmartLights();
        Projector projector = new Projector();
        SoundSystem sound = new SoundSystem();
        StreamingService netflix = new StreamingService();

        System.out.println("=== 🎬 CLIENT STARTING MOVIE (THE HARD WAY) ===");
        lights.dim(15);
        projector.turnOn();
        projector.setInputSource("HDMI 1");
        sound.turnOn();
        sound.setVolume(25);
        netflix.login();
        netflix.playMovie("Inception");

        System.out.println("\n=== 🛑 CLIENT ENDING MOVIE (THE HARD WAY) ===");
        netflix.stop();
        sound.turnOff();
        projector.turnOff();
        lights.turnOn();
    }
}