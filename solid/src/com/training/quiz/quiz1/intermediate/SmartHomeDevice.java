package com.training.ocp.quiz.quiz1.intermediate;
// Modifying the existing interface to adapt to new features
public interface SmartHomeDevice {
    void turnOn();
    void turnOff();
    void setTemperature(int temp); // For Thermostat
    void startVideoStreaming();    // For Camera
}
