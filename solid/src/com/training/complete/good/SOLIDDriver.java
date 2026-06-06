package com.training.complete.good;

import java.util.ArrayList;
import java.util.List;

// --- Domain Models (Clean and Encapsulated) ---
class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() { return price; }
}

class Order {
    private final List<Item> items = new ArrayList<>();
    private final String customerEmail;

    public Order(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void addItem(Item item) { this.items.add(item); }
    public List<Item> getItems() { return items; }
    public String getCustomerEmail() { return customerEmail; }

    // Information Expert Pattern: Object calculates its own state
    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}

// --- Abstractions / Ports (DIP) ---
interface PaymentStrategy {
    void processPayment(double amount);
}

interface NotificationService {
    void sendNotification(String recipient, String message);
}

// --- Concrete Strategies (OCP - Easily add new ones without modifying core logic) ---
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("[Payment] Successfully processed $" + amount + " via Credit Card Gateway.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("[Payment] Successfully processed $" + amount + " via PayPal API Gateway.");
    }
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("[Notification] Email sent to " + recipient + " with message: " + message);
    }
}

class SmsNotifictionService implements  NotificationService{

    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("[Notification] SMS sent to " + recipient + " with message: " + message);

    }
}

class GPayement implements  PaymentStrategy{

    @Override
    public void processPayment(double amount) {
        System.out.println("[Payment] Successfully processed $" + amount + " via GPay API Gateway.");

    }
}

// --- Orchestrator (SRP Compliant) ---
class OrderProcessor {
    private final PaymentStrategy paymentStrategy;
    private final NotificationService notificationService;

    // Dependency Injection via Constructor Injection
    public OrderProcessor(PaymentStrategy paymentStrategy, NotificationService notificationService) {
        this.paymentStrategy = paymentStrategy;
        this.notificationService = notificationService;
    }

    public void process(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cannot process an empty order.");
        }

        double totalAmount = order.calculateTotal();

        // Polymorphic calls abstract away execution details
        paymentStrategy.processPayment(totalAmount);
        notificationService.sendNotification(order.getCustomerEmail(), "Order processed successfully totaling $" + totalAmount);
    }
}

// --- Execution Harness ---
public class SOLIDDriver {
    public static void main(String[] args) {
        Order order = new Order("vishwa@example.com");
        order.addItem(new Item("Microservices Architecture Book", 55.00));
        order.addItem(new Item("Mechanical Keyboard", 120.00));

        // Injecting Credit Card and Email Engine
        OrderProcessor processor1 = new OrderProcessor(new CreditCardPayment(), new EmailNotificationService());
        System.out.println("--- Running Order 1 (Credit Card) ---");
        processor1.process(order);

        // Instantly switching execution engine to PayPal without updating the processor code
        OrderProcessor processor2 = new OrderProcessor(new PayPalPayment(), new EmailNotificationService());
        System.out.println("\n--- Running Order 2 (PayPal Transfer) ---");
        processor2.process(order);
    }
}