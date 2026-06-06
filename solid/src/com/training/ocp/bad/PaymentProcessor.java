package com.training.ocp.bad;

/**
 * The Bad Way: Hardcoded If-Else Blocks
 * If we want to add a third payment method (e.g., PayPal),
 * we have to modify the processPayment method by adding another if-else branch
 */
public class PaymentProcessor {
    public void processPayment(String type, double amount) {
        if (type.equals("CreditCard")) {
            // Process credit card
        } else if (type.equals("DebitCard")) {
            // Process debit card
        }
    }
}