package com.training.creational.factory.advanced2;

public class Main {
    public static void main(String[] args) {
        // Initialization/Configuration phase (can be automated via reflection/Spring)
        DynamicNotificationFactory.registerProduct("SMS", SMSNotification::new);
        DynamicNotificationFactory.registerProduct("EMAIL", EmailNotification::new);

        // Look! We can add a brand new type dynamically right here!
       // DynamicNotificationFactory.registerProduct("PUSH", PushNotification::new);

        // Core App Execution
        Notification myNotice = DynamicNotificationFactory.createNotification("SMS");
        myNotice.send();
    }
}