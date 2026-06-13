package com.training.creational.factory.bad;

public class NotificationService {
    public void sendNotification(String type) {
        // If we want to add a new "Push" notification, we must modify
        // this existing class!
        if (type.equals("SMS")) {
            SMSNotification sms = new SMSNotification();
            sms.send();
        } else if (type.equals("EMAIL")) {
            EmailNotification email = new EmailNotification();
            email.send();
        }
    }
}

/**
 * The Flaw: Every time the business wants to support a new notification type,
 * you have to open this class, add an else-if, recompile, and re-test it.
 * This violates the Open/Closed Principle (Open for extension, closed for modification).
 */