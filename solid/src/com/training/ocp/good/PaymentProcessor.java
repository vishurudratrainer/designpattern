package com.training.ocp.good;

/**
 * The SOLID Way: Use Polymorphism (Interfaces)
 * Instead of modifying existing code, we extend the system by creating a new class that
 * implements a common interface.
 */
interface PaymentMethod {
    void pay(double amount);
}

// Existing code untouched
class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) { /* ... */ }
}

// New feature added completely in a new file
class PayPalPayment implements PaymentMethod {
    public void pay(double amount) { /* ... */ }
}
class GPayPayment implements  PaymentMethod{

    @Override
    public void pay(double amount) {

    }
}

// This class is closed for modification; it handles any new payment method seamlessly
public class PaymentProcessor {
    public void processPayment(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}