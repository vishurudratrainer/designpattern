package com.training.creational.factory.advanced2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
interface Notification {
    void send();
}

// 2. Concrete Product implementations
class SMSNotification implements Notification {
    public void send() { System.out.println("Sending SMS text message..."); }
}

class EmailNotification implements Notification {
    public void send() { System.out.println("Sending rich HTML Email..."); }
}
class DynamicNotificationFactory {
    // A map that stores instantiation logic (Suppliers) dynamically
    private static final Map<String, Supplier<Notification>> registry = new HashMap<>();

    // 1. Register new types dynamically without touching this class's core code
    public static void registerProduct(String type, Supplier<Notification> supplier) {
        registry.put(type.toUpperCase(), supplier);
    }

    // 2. The creation method has NO switch or if-else statements!
    public static Notification createNotification(String type) {
        Supplier<Notification> supplier = registry.get(type.toUpperCase());
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown notification type: " + type);
        }
        return supplier.get();
    }
}
