package com.training.creational.factory.advanced1;
// The Application Logic Class (The Client)
class CommunicationManager {
    private final NotificationFactory factory;

    // Dependency Injection: Pass the factory interface through the constructor
    public CommunicationManager(NotificationFactory factory) {
        this.factory = factory;
    }

    public void alertUser() {
        // The client code does not use the 'new' keyword for products!
        Notification notification = factory.createNotification();
        notification.send();
    }
}

