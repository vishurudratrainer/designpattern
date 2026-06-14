package com.training.structural.facade.fix;

import com.training.structural.facade.Projector;
import com.training.structural.facade.SmartLights;
import com.training.structural.facade.SoundSystem;
import com.training.structural.facade.StreamingService;

public class CorrectMain {
    public static void main(String[] args) {
        // Initialize components (This could be handled by a dependency injection tool)
        SmartLights lights = new SmartLights();
        Projector projector = new Projector();
        SoundSystem sound = new SoundSystem();
        StreamingService netflix = new StreamingService();

        // Instantiate our clean Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(lights, projector, sound, netflix);

        // The client interacts with ONE simple class!
        System.out.println("=== 🎬 CLIENT STARTING MOVIE (THE CLEAN WAY) ===");
        homeTheater.watchMovie("Inception");

        System.out.println("=== 🛑 CLIENT ENDING MOVIE (THE CLEAN WAY) ===");
        homeTheater.endMovie();
    }
}