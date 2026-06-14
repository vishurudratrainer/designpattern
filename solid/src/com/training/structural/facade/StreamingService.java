package com.training.structural.facade;

public class StreamingService {
    public void login() {
        System.out.println("Logged into Netflix account.");
    }

    public void playMovie(String title) {
        System.out.println("Now streaming: \"" + title + "\"");
    }

    public void stop() {
        System.out.println("Stopped streaming movie.");
    }
}
