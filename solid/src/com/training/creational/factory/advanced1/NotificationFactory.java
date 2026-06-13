package com.training.creational.factory.advanced1;
// 1. The common Product interface
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

// 3. The Creator/Factory Interface
interface NotificationFactory {
    Notification createNotification(); // This is the "Template" method for creation
}

// 4. Dedicated concrete factories for each product type
class SMSNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}