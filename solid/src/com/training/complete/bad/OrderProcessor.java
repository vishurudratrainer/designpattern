package com.training.complete.bad;
/**
 * Why This Fails in Production
 * Single Responsibility Principle (SRP) Violation: If the email server configurations change or the tax calculation rules change, the OrderProcessor class must be modified.
 *
 * Open/Closed Principle (OCP) Violation: Adding a third payment option (like UPI or Apple Pay) requires editing the process method directly and appending another if-else block, which risks breaking existing payment flows.
 *
 * Dependency Inversion Principle (DIP) Violation: High-level policy (OrderProcessor) directly relies on low-level details (SMTP logs and concrete gateway logic).
 */


import java.util.ArrayList;
import java.util.List;

// Supporting fragile models inside the same package
class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

class Order {
    private List<Item> items = new ArrayList();
    private String customerEmail;

    public Order(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void addItem(Item item) { this.items.add(item); }
    public List<Item> getItems() { return items; }
    public String getCustomerEmail() { return customerEmail; }
}

// THE MONOLITH: Violates SRP and OCP
public class OrderProcessor {
    public void process(Order order, String paymentType) {
        // 1. Validation Logic
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order cannot be empty.");
        }

        // 2. Calculation Logic
        double total = 0;
        for (Item item : order.getItems()) {
            total += item.getPrice();
        }

        // 3. Payment Processing Logic (Hardcoded conditional logic - Violates OCP)
        if (paymentType.equalsIgnoreCase("CREDIT_CARD")) {
            System.out.println("Charging $" + total + " using Credit Card Gateway.");
        } else if (paymentType.equalsIgnoreCase("PAYPAL")) {
            System.out.println("Charging $" + total + " using PayPal API.");
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }

        // 4. Notification Logic (Hardcoded implementation)
        System.out.println("Connecting to SMTP Server...");
        System.out.println("Sending confirmation email to " + order.getCustomerEmail());
    }
}