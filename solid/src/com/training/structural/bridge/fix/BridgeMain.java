package com.training.structural.bridge.fix;

// 1. Implementation Layer (The underlying systems)
interface Device {
    void turnOn();
    void setChannel(int channel);
}

class TV implements Device {
    public void turnOn() { System.out.println("TV turning on."); }
    public void setChannel(int ch) { System.out.println("TV channel set to " + ch); }
}

// 2. Abstraction Layer (The controllers that hold a reference to Implementation)
abstract class RemoteControl {
    protected Device device; // The Bridge

    public RemoteControl(Device device) { this.device = device; }
    public abstract void powerButton();
}

// Concrete Abstraction: Smart Remote adds extra capabilities cleanly
class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) { super(device); }

    @Override
    public void powerButton() {
        System.out.print("[Advanced Remote] ");
        device.turnOn();
    }

    public void voiceCommand(String query) {
        System.out.println("Processing voice command: " + query);
        device.setChannel(5);
    }
}

// Sample Main Execution
public class BridgeMain {
    public static void main(String[] args) {
        Device myTv = new TV();
        AdvancedRemote remote = new AdvancedRemote(myTv); // Bridge connected at runtime

        remote.powerButton();
        remote.voiceCommand("Switch to news channel");
    }
}