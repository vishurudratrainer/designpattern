package com.training.creational.factory.intermediate;
// 1. Common Interface
interface Notification { void send(); }

// 2. Concrete Products
class SMSNotification implements Notification { public void send() { System.out.println("SMS Sent"); } }
class EmailNotification implements Notification { public void send() { System.out.println("Email Sent"); } }

// 3. Factory Class
class NotificationFactory {
    public Notification createNotification(String type) {
        return switch (type.toUpperCase()) {
            case "SMS" -> new SMSNotification();
            case "EMAIL" -> new EmailNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}