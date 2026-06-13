package com.training.creational.factory.advanced1;

public class Main {
    public static void main(String[] args) {
        // Example A: Setting up the app to use SMS
        NotificationFactory smsFactory = new SMSNotificationFactory();
        CommunicationManager smsManager = new CommunicationManager(smsFactory);
        smsManager.alertUser(); // Output: Sending SMS text message...

        // Example B: Setting up the app to use Email
        NotificationFactory emailFactory = new EmailNotificationFactory();
        CommunicationManager emailManager = new CommunicationManager(emailFactory);
        emailManager.alertUser(); // Output: Sending rich HTML Email...
    }
}