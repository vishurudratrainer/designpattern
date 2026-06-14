package com.training.structural.facade.fix;

import com.training.structural.facade.Projector;
import com.training.structural.facade.SmartLights;
import com.training.structural.facade.SoundSystem;
import com.training.structural.facade.StreamingService;

class HomeTheaterFacade {
    // Hold references to all subsystem classes
    private final SmartLights lights;
    private final Projector projector;
    private final SoundSystem sound;
    private final StreamingService streamingService;

    // Pass the components into the Facade constructor
    public HomeTheaterFacade(SmartLights lights, Projector projector, SoundSystem sound, StreamingService streamingService) {
        this.lights = lights;
        this.projector = projector;
        this.sound = sound;
        this.streamingService = streamingService;
    }

    // Single interface method to watch a movie
    public void watchMovie(String movieTitle) {
        System.out.println("\n[Facade] Preparing the room for a movie night...");
        lights.dim(15);
        projector.turnOn();
        projector.setInputSource("HDMI 1");
        sound.turnOn();
        sound.setVolume(25);
        streamingService.login();
        streamingService.playMovie(movieTitle);
        System.out.println("[Facade] Enjoy your movie!\n");
    }

    // Single interface method to turn everything off cleanly
    public void endMovie() {
        System.out.println("\n[Facade] Shutting down home theater...");
        streamingService.stop();
        sound.turnOff();
        projector.turnOff();
        lights.turnOn();
        System.out.println("[Facade] Room returned to normal state.\n");
    }
}