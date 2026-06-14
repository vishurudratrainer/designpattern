package com.training.structural.flyweight.fix;

// This class is completely immutable (final fields, no setters)
public final class ProductSpecification {
    private final String productId;
    private final String title;
    private final String description;
    private final byte[] heavyImage; // Loaded ONCE in memory

    public ProductSpecification(String id, String title, String desc, byte[] img) {
        this.productId = id;
        this.title = title;
        this.description = desc;
        this.heavyImage = img;
    }

    public void displayItemDetails(int quantity, double price) {
        System.out.println("Item: " + title + " | Qty: " + quantity + " | Price per unit: $" + price);
    }
}